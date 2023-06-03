package com.ihridoydas.simpleapp.features.composibleSheep.sheep.parts

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.features.composibleSheep.canvasExtensions.guidelines.GuidelineAlpha
import com.ihridoydas.simpleapp.features.composibleSheep.canvasExtensions.guidelines.GuidelineDashPattern
import com.ihridoydas.simpleapp.features.composibleSheep.canvasExtensions.guidelines.GuidelineStrokeWidth
import com.ihridoydas.simpleapp.features.composibleSheep.canvasExtensions.maths.getCircumferencePointForAngle
import com.ihridoydas.simpleapp.features.composibleSheep.sheep.model.Sheep

private const val OverlapPercentage = 0.5f

fun DrawScope.drawLegs(
    circleCenterOffset: Offset,
    circleRadius: Float,
    sheep: Sheep,
    legColor: Color,
    showGuidelines: Boolean = false,
) {
    val circleDiameter = circleRadius.times(2f)

    sheep.legs.forEach { leg ->

        val legPointInCircumference = getCircumferencePointForAngle(
            angleInRadians = Math.toRadians(leg.positionAngleInCircle.toDouble()),
            radius = circleRadius,
            circleCenter = circleCenterOffset
        )

        val legOverlapY = circleDiameter.times(leg.legBodyRatioHeight).times(OverlapPercentage)

        val legSize = Size(
            width = circleDiameter.times(leg.legBodyRatioWidth),
            height = circleDiameter.times(leg.legBodyRatioHeight) + legOverlapY
        )

        val topLeft = Offset(
            x = legPointInCircumference.x - legSize.width.div(2),
            y = legPointInCircumference.y - legOverlapY
        )

        rotate(
            degrees = leg.rotationDegrees,
            pivot = legPointInCircumference
        ) {
            drawRoundRect(
                color = legColor,
                topLeft = topLeft,
                size = legSize,
                cornerRadius = CornerRadius(20f)
            )
        }

        if (showGuidelines) {
            drawLegGuideline(
                legPointInCircumference = legPointInCircumference,
                legSize = legSize,
                rotation = leg.rotationDegrees
            )
        }
    }
}

private fun DrawScope.drawLegGuideline(
    legPointInCircumference: Offset,
    legSize: Size,
    rotation: Float
) {
    drawPoints(
        points = listOf(legPointInCircumference),
        color = Color.Magenta.copy(alpha = GuidelineAlpha.normal),
        pointMode = PointMode.Points,
        cap = StrokeCap.Round,
        strokeWidth = 2.dp.toPx()
    )

    rotate(degrees = rotation, pivot = legPointInCircumference) {
        drawLine(
            color = Color.Magenta.copy(alpha = GuidelineAlpha.normal),
            start = legPointInCircumference,
            end = legPointInCircumference.copy(y = legPointInCircumference.y + legSize.height),
            strokeWidth = GuidelineStrokeWidth,
            pathEffect = GuidelineDashPattern,
        )
    }
}
