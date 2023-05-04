

package com.ihridoydas.simpleapp.features.newTonsTimer

import androidx.compose.runtime.snapshots.SnapshotStateList
import java.lang.Math.toDegrees
import java.lang.Math.toRadians
import kotlin.math.atan
import kotlin.math.sin

fun sinDegree(angle: Float): Float = sin(toRadians(angle.toDouble())).toFloat()

fun atanDegree(x: Float): Float = toDegrees(atan(x).toDouble()).toFloat()

fun SnapshotStateList<Float>.setupAngles(
    firstBallAngle: Float = 0f,
    middleBallsAngle: (index: Int) -> Float = { 0f },
    lastBallAngle: Float = 0f
) = apply {
    indices.forEach { index ->
        this[index] = when (index) {
            0 -> firstBallAngle
            lastIndex -> lastBallAngle
            else -> middleBallsAngle(index)
        }
    }
}
