
package com.ihridoydas.simpleapp.features.newTonsTimer.timer

import android.os.SystemClock

object Clock {

    var fakeNow: Long? = null

    fun now(): Long = fakeNow ?: SystemClock.uptimeMillis()
}
