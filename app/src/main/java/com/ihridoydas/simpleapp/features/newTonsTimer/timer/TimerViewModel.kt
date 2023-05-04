
package com.ihridoydas.simpleapp.features.newTonsTimer.timer

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ihridoydas.simpleapp.features.newTonsTimer.setupAngles
import com.ihridoydas.simpleapp.features.newTonsTimer.timer.TimerState.Configured
import com.ihridoydas.simpleapp.features.newTonsTimer.timer.TimerState.Configured.Paused
import com.ihridoydas.simpleapp.features.newTonsTimer.timer.TimerState.Configured.Running
import com.ihridoydas.simpleapp.features.newTonsTimer.timer.TimerState.NotConfigured
import java.util.concurrent.TimeUnit

class TimerViewModel(application: Application) : AndroidViewModel(application) {

    private val hitSoundPlayer = HitSoundPlayer(application)
    private val ticker = TimerTicker(
        viewModelScope = viewModelScope,
        timerStateProvider = ::state,
        onTick = ::refreshRemainingMillis,
        onBallHit = hitSoundPlayer::playHitSound,
        onTimerFinished = { setNewState(NotConfigured()) }
    )
    private var _state: TimerState by mutableStateOf(NotConfigured())

    var darkMode by mutableStateOf(true)
    var displayedMillis by mutableStateOf(_state.remainingMillis)
    val state get() = _state
    val angles = mutableStateListOf(*Array(BALLS_COUNT) { 0f })
    val isConfigured get() = state is Configured

    fun configureStartAngle(startAngle: Float) {
        if (state is Configured) return
        setNewState(NotConfigured(startAngle.coerceAtLeast(0f).coerceAtMost(MAX_ANGLE)))
    }

    fun play() {
        val state = state
        when {
            !state.canBeStarted() -> return
            state is NotConfigured -> setNewState(state.started())
            state is Paused -> setNewState(state.resumed())
        }
    }

    fun pause() {
        val runningState = state as? Running ?: return
        setNewState(runningState.paused())
    }

    fun reset() {
        setNewState(NotConfigured())
    }

    fun refreshAngles() {
        when (val state = _state) {
            is Configured -> with(state.swingAnimation) { angles.setupAngles(state) }
            else -> angles.setupAngles(firstBallAngle = state.startAngle)
        }
    }

    override fun onCleared() {
        super.onCleared()
        ticker.cancel()
        hitSoundPlayer.release()
    }

    private fun refreshRemainingMillis() {
        displayedMillis = _state.remainingMillis
    }

    private fun setNewState(newState: TimerState) {
        ticker.onStateChange(newState)
        _state = newState
        refreshRemainingMillis()
    }

    companion object {
        const val MAX_ANGLE = 50f
        val MAX_DURATION_MILLIS = TimeUnit.SECONDS.toMillis(60)
        private const val BALLS_COUNT = 5
    }
}
