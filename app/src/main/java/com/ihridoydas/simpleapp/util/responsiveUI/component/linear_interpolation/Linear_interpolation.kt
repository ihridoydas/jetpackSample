package com.ihridoydas.simpleapp.util.responsiveUI.component.linear_interpolation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.util.responsiveUI.component.compose_ProgressIndicator.util.lerp
import com.ihridoydas.simpleapp.util.responsiveUI.component.modifierCustom.aspectRatio
import kotlin.math.cos
import kotlin.math.sin

@Preview
@Composable
private fun RadiusChangeLerpAnimationTes() {

    val outerCircleRadius = 450f
    val innerCircleRadius = 250f

    var isOut by remember {
        mutableStateOf(false)
    }

    val transition = rememberInfiniteTransition(label = "")

    val angle by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 3000
                0.0f at 0 using LinearEasing
                360f at 3000 using LinearEasing
            },
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    val progress by animateFloatAsState(
        if (isOut) 1f else 0f,
        animationSpec = tween(durationMillis = 700, easing = LinearEasing),
        label = "distance"
    )

    val distance = remember(progress) {
        lerp(innerCircleRadius, outerCircleRadius, progress)
    }

    var position by remember {
        mutableStateOf(Offset.Unspecified)
    }

    Column {
        Canvas(
            modifier = Modifier.fillMaxWidth().aspectRatio(1f)
        ) {

            drawCircle(
                color = Color.Blue,
                radius = outerCircleRadius,
                style = Stroke(2.dp.toPx())
            )

            drawCircle(
                color = Color.Blue,
                radius = innerCircleRadius,
                style = Stroke(2.dp.toPx())
            )

            rotate(angle) {
                drawCircle(
                    color = Color.Red,
                    radius = 50f,
                    center = Offset(center.x + distance, center.y)

                )
            }

            val angleInRadians = angle * DEGREE_TO_RAD
            position = Offset(
                center.x + distance * cos(angleInRadians), center.y + distance * sin(angleInRadians)
            )
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                isOut = isOut.not()
            }
        ) {
            Text("Out $isOut")
        }

        if (position != Offset.Unspecified) {
            Text("Position: $position")
        }
    }
}

private const val DEGREE_TO_RAD = (Math.PI / 180f).toFloat()