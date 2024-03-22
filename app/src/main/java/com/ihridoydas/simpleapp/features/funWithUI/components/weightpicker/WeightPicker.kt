package com.ihridoydas.simpleapp.features.funWithUI.components.weightpicker

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.Style
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.core.graphics.withRotation
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

@Composable
fun WeightPicker(
    modifier: Modifier = Modifier,
    style: ScaleStyle,
    minWeight: Int = 50,
    maxWeight: Int = 200,
    initialWeight: Int = 100,
    onWeightChanged: (Int) -> Unit
) {

    val radius = style.radius
    val scaleWidth = style.scaleWidth

    var center by remember { mutableStateOf(Offset.Zero) }
    var circleCenter by remember { mutableStateOf(Offset.Zero) }

    var angel by remember { mutableStateOf(0f) }
    var dragStartedAngel by remember { mutableStateOf(0f) }
    var oldAngel by remember { mutableStateOf(angel) }

    Canvas(modifier = modifier
        .pointerInput(true) {
            detectDragGestures(
                onDragStart = { offset ->
                    dragStartedAngel = -atan2(
                        circleCenter.x - offset.x,
                        circleCenter.y - offset.y,
                    ) * (180f / PI.toFloat())
                },
                onDragEnd = {
                    oldAngel = angel
                }
            ) { change, _ ->
                val touchAngel = -atan2(
                    circleCenter.x - change.position.x,
                    circleCenter.y - change.position.y,
                ) * (180f / PI.toFloat())

                val newAngel = oldAngel + (touchAngel - dragStartedAngel)
                angel = newAngel.coerceIn(
                    minimumValue = initialWeight - maxWeight.toFloat(),
                    maximumValue = initialWeight - minWeight.toFloat()
                )
                onWeightChanged((initialWeight - angel).roundToInt())
            }
        }) {
        center = this.center
        circleCenter = Offset(center.x, scaleWidth.toPx() / 2f + radius.toPx())

        val outerRadius = radius.toPx() + scaleWidth.toPx() / 2f
        val innerRadius = radius.toPx() - scaleWidth.toPx() / 2f

        drawContext.canvas.nativeCanvas.apply {
            drawCircle(
                circleCenter.x,
                circleCenter.y,
                radius.toPx(),
                Paint().apply {
                    strokeWidth = scaleWidth.toPx()
                    color = Color.WHITE
                    setStyle(Style.STROKE)
                    setShadowLayer(
                        60f,
                        0f,
                        0f,
                        Color.argb(50, 0, 0, 0)
                    )
                }
            )

            for (i in minWeight..maxWeight) {
                val angelInRad = (i - initialWeight + angel - 90) * (PI / 180).toFloat()
                val lineType = when {
                    i % 10 == 0 -> LineType.TenStep
                    i % 5 == 0 -> LineType.FiveStep
                    else -> LineType.Normal
                }
                val lineLength = when (lineType) {
                    LineType.Normal -> style.normalLineLength.toPx()
                    LineType.FiveStep -> style.fiveStepLineLength.toPx()
                    LineType.TenStep -> style.tenStepLineLength.toPx()
                }
                val lineColor = when (lineType) {
                    LineType.Normal -> style.normalLineColor
                    LineType.FiveStep -> style.fiveStepLineColor
                    LineType.TenStep -> style.tenStepLineColor
                }
                val lineStart = Offset(
                    x = (outerRadius - lineLength) * cos(angelInRad) + circleCenter.x,
                    y = (outerRadius - lineLength) * sin(angelInRad) + circleCenter.y
                )
                val lineEnd = Offset(
                    x = outerRadius * cos(angelInRad) + circleCenter.x,
                    y = outerRadius * sin(angelInRad) + circleCenter.y
                )

                drawLine(
                    lineColor,
                    lineStart,
                    lineEnd,
                    strokeWidth = 1.dp.toPx()
                )

                drawContext.canvas.nativeCanvas.apply {
                    if (lineType is LineType.TenStep) {
                        val textRadius =
                            outerRadius - lineLength - 5.dp.toPx() - style.textSize.toPx()
                        val x = textRadius * cos(angelInRad) + circleCenter.x
                        val y = textRadius * sin(angelInRad) + circleCenter.y
                        withRotation(
                            degrees = angelInRad * (180f / PI.toFloat()) + 90f,
                            pivotX = x,
                            pivotY = y
                        ) {
                            drawText(
                                abs(i).toString(),
                                x,
                                y,
                                Paint().apply {
                                    textSize = style.textSize.toPx()
                                    textAlign = Paint.Align.CENTER
                                }
                            )
                        }
                    }
                }
            }
            val middleTop = Offset(
                x = circleCenter.x,
                y = circleCenter.y - innerRadius - style.scaleIndicatorLength.toPx()
            )
            val bottomLeft = Offset(
                x = circleCenter.x - 4f,
                y = circleCenter.y - innerRadius
            )
            val bottomRight = Offset(
                x = circleCenter.x + 4f,
                y = circleCenter.y - innerRadius
            )

            val indicator = Path().apply {
                moveTo(bottomLeft.x, bottomLeft.y)
                lineTo(middleTop.x, middleTop.y)
                lineTo(bottomRight.x, bottomRight.y)
                lineTo(bottomLeft.x, bottomLeft.y)
            }
            drawPath(
                indicator,
                style.scaleIndicatorColor
            )
        }
    }
}