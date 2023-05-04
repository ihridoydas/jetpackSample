
package com.ihridoydas.simpleapp.features.newTonsTimer.balls

import androidx.compose.animation.core.Animation
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.TargetBasedAnimation
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.ihridoydas.simpleapp.features.newTonsTimer.setupAngles
import com.ihridoydas.simpleapp.features.newTonsTimer.timer.TimerState
import com.ihridoydas.simpleapp.features.newTonsTimer.timer.relativeRemainingEnergy
import java.util.concurrent.TimeUnit

class SwingAnimation(maxAngle: Float) {

    private val animation: Animation<Float, AnimationVector1D>

    init {
        animation = TargetBasedAnimation(
            animationSpec = infiniteRepeatable(
                repeatMode = RepeatMode.Reverse,
                animation = tween(
                    durationMillis = CYCLE_DURATION_MILLIS.toInt(),
                    easing = SWING_EASING
                )
            ),
            typeConverter = Float.VectorConverter,
            initialValue = maxAngle,
            targetValue = -maxAngle,
        )
    }

    fun isBallHit(previousElapsedMillis: Long, elapsedMillis: Long): Boolean {
        val previousCycleIndex = (previousElapsedMillis + MILLIS_UNTIL_HIT) / CYCLE_DURATION_MILLIS
        val cycleIndex = (elapsedMillis + MILLIS_UNTIL_HIT) / CYCLE_DURATION_MILLIS
        return previousCycleIndex != cycleIndex
    }

    fun SnapshotStateList<Float>.setupAngles(state: TimerState.Configured) {
        val angle = state.rawAngle() * state.relativeRemainingEnergy
        val angleAfterBeingHit = angle * SWING_ANGLE_AFTER_BEING_HIT_MULTIPLIER
        val reboundAngleAfterHit = angle * SWING_REBOUND_ANGLE_AFTER_HIT_MULTIPLIER
        val isFirstBallSwinging = angle > 0
        val isFirstSwing = state.elapsedMillis <= MILLIS_UNTIL_HIT

        fun getFirstBallAngle() = if (isFirstBallSwinging) angle else reboundAngleAfterHit
        fun getLastBallAngle() = if (!isFirstBallSwinging) angle else reboundAngleAfterHit
        fun getMiddleBallAngle(index: Int): Float {
            val indexDistanceFromTheSwingingBall = (if (isFirstBallSwinging) size - index else index) - 1
            return angleAfterBeingHit * (1 + indexDistanceFromTheSwingingBall * SWING_ADDITIONAL_BOUNCE_BETWEEN_BALLS_MULTIPLIER)
        }

        when (isFirstSwing) {
            true -> setupAngles(firstBallAngle = getFirstBallAngle())
            else -> setupAngles(
                firstBallAngle = getFirstBallAngle(),
                middleBallsAngle = ::getMiddleBallAngle,
                lastBallAngle = getLastBallAngle()
            )
        }
    }

    private fun TimerState.Configured.rawAngle(): Float {
        val animationProgressMillis = elapsedMillis
        val elapsedNanos = TimeUnit.MILLISECONDS.toNanos(animationProgressMillis)
        return animation.getValueFromNanos(elapsedNanos)
    }

    private companion object {
        const val CYCLE_DURATION_MILLIS = 500L
        const val SWING_ANGLE_AFTER_BEING_HIT_MULTIPLIER = 0.05f
        const val SWING_REBOUND_ANGLE_AFTER_HIT_MULTIPLIER = -SWING_ANGLE_AFTER_BEING_HIT_MULTIPLIER / 4f
        const val SWING_ADDITIONAL_BOUNCE_BETWEEN_BALLS_MULTIPLIER = 0.3f
        const val MILLIS_UNTIL_HIT = CYCLE_DURATION_MILLIS / 2
        val SWING_EASING = CubicBezierEasing(0.7f, 0f, 0.3f, 1f)
    }
}
