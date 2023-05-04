
package com.ihridoydas.simpleapp.features.newTonsTimer.configuration

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import com.ihridoydas.simpleapp.features.newTonsTimer.atanDegree
import com.ihridoydas.simpleapp.features.newTonsTimer.balls.BallSize

fun Modifier.configurationDragModifier(ballSize: BallSize, onConfigurationAngleChanged: (Float) -> Unit, onDragEnd: () -> Unit) = composed {
    var draggedOffset by remember { mutableStateOf(Offset.Zero) }
    pointerInput(Unit) {
        detectDragGestures(
            onDragStart = { draggedOffset = Offset.Zero },
            onDragEnd = { onDragEnd() },
            onDrag = { change, dragOffsetDelta ->
                change.consumeAllChanges()
                draggedOffset += dragOffsetDelta
                val angle = atanDegree(-draggedOffset.x / (ballSize.stringLengthToBallCenter + draggedOffset.y))
                onConfigurationAngleChanged(angle)
            }
        )
    }
}
