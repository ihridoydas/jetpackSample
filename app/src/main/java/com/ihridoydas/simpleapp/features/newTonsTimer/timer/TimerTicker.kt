
package com.ihridoydas.simpleapp.features.newTonsTimer.timer

import com.ihridoydas.simpleapp.features.newTonsTimer.timer.TimerState.Configured.Running
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.launch

class TimerTicker(
    private val viewModelScope: CoroutineScope,
    private val timerStateProvider: () -> TimerState,
    private val onTick: () -> Unit,
    private val onBallHit: (volume: Float) -> Unit,
    private val onTimerFinished: () -> Unit
) {
    @OptIn(ObsoleteCoroutinesApi::class)
    private val tickerChannel = ticker(delayMillis = TICK_DURATION_MILLIS, initialDelayMillis = 0)
    private var timerJob: Job? = null

    fun onStateChange(newState: TimerState) {
        val oldState = timerStateProvider()
        if (oldState is Running && newState !is Running) {
            timerJob?.cancel()
        } else if (oldState !is Running && newState is Running) {
            timerJob = newTimerJob(newState)
        }
    }

    private fun onTick(previousElapsedMillis: Long): Long {
        val state = timerStateProvider()
        if (state !is Running) return 0
        if (state.isFinished) {
            onTimerFinished()
            return 0
        }

        val elapsedMillis = state.elapsedMillis
        onTick()
        if (state.swingAnimation.isBallHit(elapsedMillis, previousElapsedMillis)) {
            onBallHit(state.absoluteRemainingEnergy)
        }
        return elapsedMillis
    }

    private fun newTimerJob(state: Running): Job {
        var previousElapsedMillis = state.elapsedMillis
        return viewModelScope.launch(Dispatchers.Default) {
            for (tick in tickerChannel) {
                previousElapsedMillis = onTick(previousElapsedMillis)
            }
        }
    }

    fun cancel() {
        timerJob?.cancel()
        timerJob = null
        tickerChannel.cancel()
    }

    companion object {
        var TICK_DURATION_MILLIS = 100L
    }
}
