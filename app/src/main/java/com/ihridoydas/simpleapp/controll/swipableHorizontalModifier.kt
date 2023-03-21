
package com.ihridoydas.simpleapp.controll

import androidx.compose.animation.core.snap
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.layout.onSizeChanged
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
fun Modifier.swipableHorizontal(onLeft: () -> Unit, onRight: () -> Unit): Modifier = composed {
    var width by rememberSaveable {
        mutableStateOf(0f)
    }
    val swipeableState = rememberSwipeableState(
        SwipeDirection.Initial,
        animationSpec = snap()
    )
    val anchorWidth = remember(width) {
        if (width == 0f) {
            1f
        } else {
            width
        }
    }
    val scope = rememberCoroutineScope()
    if (swipeableState.isAnimationRunning) {
        DisposableEffect(Unit) {
            onDispose {
                when (swipeableState.currentValue) {
                    SwipeDirection.Left -> {
                        onLeft()
                    }
                    SwipeDirection.Right -> {
                        onRight()
                    }
                    else -> {
                        return@onDispose
                    }
                }
                scope.launch {
                    swipeableState.snapTo(SwipeDirection.Initial)
                }
            }
        }
    }
    return@composed Modifier
        .onSizeChanged { width = it.width.toFloat() }
        .swipeable(
            state = swipeableState,
            anchors = mapOf(
                0f to SwipeDirection.Left,
                anchorWidth / 2 to SwipeDirection.Initial,
                anchorWidth to SwipeDirection.Right,
            ),
            thresholds = { _, _ -> FractionalThreshold(0.3f) },
            orientation = Orientation.Horizontal
        )
}

private enum class SwipeDirection(val raw: Int) {
    Left(0),
    Initial(1),
    Right(2),
}
