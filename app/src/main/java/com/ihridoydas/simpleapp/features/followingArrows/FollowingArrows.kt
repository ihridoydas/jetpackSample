package com.ihridoydas.simpleapp.features.followingArrows

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sqrt


private val background = Color(0xff_000000)
private val primary = Color(0xff_7F52FF)
private val secondary = Color(0xff_E24462)

@Composable
fun FollowingArrows() {
    var mousePosition by remember { mutableStateOf(Offset.Zero) }
    val animatedMousePosition by animateOffsetAsState(
        targetValue = mousePosition,
        animationSpec = spring(
            stiffness = Spring.StiffnessVeryLow,
            dampingRatio = Spring.DampingRatioMediumBouncy,
        ), label = ""
    )

    var width by remember { mutableStateOf(0f) }
    var height by remember { mutableStateOf(0f) }


    Canvas(
        Modifier
            .onSizeChanged {
                width = it.width.toFloat()
                height = it.height.toFloat()
            }
            .fillMaxSize()
            .background(background)
            .pointerInput(PointerEventType.Move){
                awaitEachGesture {
                    awaitFirstDown().let {
                        mousePosition = it.position
                    }
                }
            }
//          .onPointerEvent(PointerEventType.Move) {
//                mousePosition = it.changes.first().position
//            }

    ) {

        val xCells = 30
        val yCells = 24

        val cellWidth = this.size.width / xCells
        val cellHeight = this.size.height / yCells

        for (x in 0..xCells) {
            for (y in 0..yCells) {
                val center = Offset(
                    x = (x * cellWidth) + cellWidth / 2,
                    y = (y * cellHeight) + cellHeight / 2
                )
                val delta = center - animatedMousePosition
                val angle = (atan2(delta.y, delta.x) * 180 / PI).toFloat()

                val diagonal = sqrt(width.pow(2) + height.pow(2))
                val distance = sqrt(delta.x.pow(2) + delta.y.pow(2))

                val displacement = 200f

                val offset = Offset(
                    displacement * (delta.x / distance),
                    displacement * (delta.y / distance),
                )

                val scale = max(1f - (distance / (diagonal * .9f)) * 1f, .4f)

                val color = lerp(primary, secondary, 1f - scale)
                this.translate(
                    left = offset.x,
                    top = offset.y,
                ) {
                    this.rotate(
                        degrees = angle + 180f,
                        pivot = center,
                    ) {
                        this.scale(
                            scale = .7f * scale,
                            pivot = center,
                        ) {
                            drawArrow(
                                origin = Offset(x * cellWidth, y * cellHeight),
                                color = color,
                                cellWidth = cellWidth,
                                cellHeight = cellHeight,
                            )
                        }
                    }
                }
            }
        }
    }
}

fun DrawScope.drawArrow(
    origin: Offset,
    color: Color,
    cellWidth: Float,
    cellHeight: Float,
    strokeWidth: Float = cellWidth * .3f,
) {
    drawLine(
        color = color,
        start = origin + Offset(0f, cellHeight / 2),
        end = origin + Offset(cellWidth, cellHeight / 2),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round,
    )
    drawLine(
        color = color,
        start = origin + Offset(cellWidth / 2, 0f),
        end = origin + Offset(cellWidth, cellHeight / 2),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round,
    )
    drawLine(
        color = color,
        start = origin + Offset(cellWidth / 2, cellHeight),
        end = origin + Offset(cellWidth, cellHeight / 2),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round,
    )
}