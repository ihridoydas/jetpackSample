
package com.ihridoydas.simpleapp.util.extensions

import androidx.compose.foundation.gestures.*
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.unit.Density
import androidx.compose.ui.util.fastAll
import androidx.compose.ui.util.fastAny
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.sync.Mutex

/**
 * Detects taps but overrides default of consuming tap
 */
suspend fun PointerInputScope.detectTaps(
    onTap: () -> Unit
) {
    val pressScope = PressGestureScopeImpl(this)
    coroutineScope{
        forEachGesture {
            awaitPointerEventScope {
                do {
                    val event = awaitPointerEvent()
                    onTap()
                } while (event.changes.any{ it.pressed })
            }
        }
    }
}

/**
 * Wait for up event or cancel
 */
private suspend fun AwaitPointerEventScope.waitForUpOrCancellationInitial(): PointerInputChange? {
    while (true) {
        val event = awaitPointerEvent(PointerEventPass.Initial)
        if (event.changes.fastAll { it.changedToUp() }) {
            // All pointers are up
            return event.changes[0]
        }

        if (event.changes.fastAny { it.consumed.downChange || it.isOutOfBounds(size) }) {
            return null // Canceled
        }

        // Check for cancel by position consumption. We can look on the Final pass of the
        // existing pointer event because it comes after the Main pass we checked above.
        val consumeCheck = awaitPointerEvent(PointerEventPass.Final)
        if (consumeCheck.changes.fastAny { it.positionChangeConsumed() }) {
            return null
        }
    }
}

//Taken from Android source: https://github.com/androidx/androidx/blob/da0944806932662865db1602128f5be25f81a5fa/compose/foundation/foundation/src/commonMain/kotlin/androidx/compose/foundation/gestures/TapGestureDetector.kt#L201
private class PressGestureScopeImpl(
    density: Density
) : PressGestureScope, Density by density {
    private var isReleased = false
    private var isCanceled = false
    private val mutex = Mutex(locked = false)

    /**
     * Called when a gesture has been canceled.
     */
    fun cancel() {
        isCanceled = true
        mutex.unlock()
    }

    /**
     * Called when all pointers are up.
     */
    fun release() {
        isReleased = true
        mutex.unlock()
    }

    /**
     * Called when a new gesture has started.
     */
    fun reset() {
        mutex.tryLock() // If tryAwaitRelease wasn't called, this will be unlocked.
        isReleased = false
        isCanceled = false
    }

    override suspend fun awaitRelease() {
        if (!tryAwaitRelease()) {
            throw GestureCancellationException("The press gesture was canceled.")
        }
    }

    override suspend fun tryAwaitRelease(): Boolean {
        if (!isReleased && !isCanceled) {
            mutex.lock()
        }
        return isReleased
    }
}
