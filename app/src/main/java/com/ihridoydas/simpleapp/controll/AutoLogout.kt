

package com.ihridoydas.simpleapp.controll

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.ProcessLifecycleOwner
import java.util.*
import kotlin.concurrent.timer

/** Logoout time in seconds */
private const val LogoutTime = 60 * 30
/** Test logoout time in seconds */
private const val TestLogoutTime = 5
const val AUTO_LOGGED_OUT_EXTRA = "AUTO_LOGGED_OUT"
var logoutTime = if (!AutoLogout.testing) LogoutTime else TestLogoutTime

class AutoLogout(val loggedOut: () -> Unit) {

    companion object {
        /** Pause the autologout, paused by default, unpaused in home screen */
        private var paused = true

        private var lastTouch = Date().time

        var JustLoggedOut = false

        // 生体認証ダイアログをキャンセルした直後か
        var isJustBioAuthCancel: Boolean = false

        // LoginViewになるとtrue
        // / `LoginView`から遷移すると、`false`になる。
        var isJustTransitionedToLoginView: Boolean = false

        private var JustCameFromBackground = false

        var permissionStateChangeInApp: Boolean = false

        /** When testing is true use shorter test logout time */
        var testing = false

        /** Reset last touch and unpause */
        fun reset() {
            resetTime()
            paused = false
            JustLoggedOut = false
            isJustTransitionedToLoginView = false
        }

        /** Pause logout and reset time used by manual logout */
        fun pause() {
            paused = true
            resetTime()
        }

        fun resetTime() {
            lastTouch = Date().time
        }
    }

    var logoutTimer : Timer? = null

    // Get lifecycle events
    var lifecycleEventObserver = LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_RESUME) {
            JustCameFromBackground = true
            checkTime()
        }
        if (event == Lifecycle.Event.ON_DESTROY) {
            cleanUp()
        }
    }

    init {
        logoutTimer = timer(initialDelay = 0, period = 1000L) {
            checkTime()
        }
        ProcessLifecycleOwner.get().lifecycle.addObserver(lifecycleEventObserver)
    }

    /** Clean up resources (timer, lifecycle)*/
    fun cleanUp() {
        logoutTimer?.cancel()
        logoutTimer = null
        Log.d("AutoLogout", "CleanUp")
        ProcessLifecycleOwner.get().lifecycle.removeObserver(lifecycleEventObserver)
    }



    private fun checkTime() {
        if (paused) return
        val now = Date().time
        val timeDiff = (now - lastTouch) / 1000

        synchronized(LogoutTime) { // since multiple autologout instances can be running, lock the logout procedure
            if (timeDiff > logoutTime) {
                Log.d("AutoLogout", "Send to login screen")
                logout()
            } else {
                // if just came from background but not logged out reset time
                if (JustCameFromBackground) {
                    resetTime()
                }
            }
            JustCameFromBackground = false
        }
    }

    /** update last touch to current time */
    fun updateTouchTime(time: Long) {
        if (paused) { return }
        lastTouch = time
    }

    /** Close current activity, pop stack to login, start main activity */
    fun logout() {
        if ( paused) {
            return
        }
        paused = true
        JustLoggedOut = true
        loggedOut()
    }

}
