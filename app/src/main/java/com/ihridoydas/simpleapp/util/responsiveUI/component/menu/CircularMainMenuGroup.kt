package com.ihridoydas.simpleapp.util.responsiveUI.component.menu

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.random.Random


@Composable
fun MainMenuCanvas() {
    var showSubMenu by remember { mutableStateOf(false) }
    val mainCircleRadius = 50.dp
    val subCircleRadius = 20.dp
    val distanceFromCenter = 100.dp
    val subCircles = remember { List(6) { mutableStateOf(Offset.Zero) } }
    val density = LocalDensity.current

    val animationProgress by animateFloatAsState(
        targetValue = if (showSubMenu) 1f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    val circleColors = remember {
        List(6) {
            Brush.linearGradient(
                colors = listOf(
                    Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat(), 1f),
                    Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat(), 1f)
                )
            )
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        val center = remember { mutableStateOf(Offset.Zero) }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            center.value = Offset(size.width / 2, size.height / 2)

            drawCircle(color = Color.Blue, radius = mainCircleRadius.toPx(), center = center.value)

            if (showSubMenu) {
                subCircles.forEachIndexed { index, offsetState ->
                    val current = offsetState.value
                    val next = subCircles[(index + 1) % subCircles.size].value

                    drawLine(
                        color = Color.Gray,
                        start = current,
                        end = next,
                        strokeWidth = 5f
                    )

                    val movingCirclePos = Offset(
                        lerpAction(current.x, next.x, animationProgress),
                        lerpAction(current.y, next.y, animationProgress)
                    )

                    drawCircle(
                        color = Color.Black,
                        radius = 10f,
                        center = movingCirclePos
                    )

                    drawCircle(
                        brush = circleColors[index],
                        radius = subCircleRadius.toPx(),
                        center = current
                    )
                }
            }
        }

        if (showSubMenu) {
            subCircles.forEachIndexed { index, offsetState ->
                val boxSizeDp = subCircleRadius * 2

                Box(
                    modifier = Modifier
                        .offset {
                            IntOffset(
                                (offsetState.value.x - with(density) { boxSizeDp.toPx() } * 5).roundToInt(),
                                (offsetState.value.y - with(density) { boxSizeDp.toPx() } * 10).roundToInt()
                            )
                        }
                        .size(boxSizeDp)
                        .pointerInput(index) {
                            detectDragGestures { _, dragAmount ->
                                val newX = (offsetState.value.x + dragAmount.x).coerceIn(
                                    0f,
                                    center.value.x * 2 - subCircleRadius.toPx()
                                )
                                val newY = (offsetState.value.y + dragAmount.y).coerceIn(
                                    0f,
                                    center.value.y * 2 - subCircleRadius.toPx()
                                )
                                offsetState.value = Offset(newX, newY)
                            }
                        }
                )
            }
        }

        Text("Main Menu", color = Color.White, modifier = Modifier.align(Alignment.Center))

        Box(
            modifier = Modifier
                .size(mainCircleRadius * 2)
                .align(Alignment.Center)
                .pointerInput(Unit) {
                    detectTapGestures {
                        showSubMenu = !showSubMenu
                        if (showSubMenu) {
                            for (i in 1..6) {
                                val angle = 60 * i
                                val x =
                                    center.value.x + cos(Math.toRadians(angle.toDouble())) * distanceFromCenter.toPx()
                                val y =
                                    center.value.y + sin(Math.toRadians(angle.toDouble())) * distanceFromCenter.toPx()
                                subCircles[i - 1].value = Offset(x.toFloat(), y.toFloat())
                            }
                        }
                    }
                }
        )
    }
}

fun lerpAction(start: Float, stop: Float, fraction: Float): Float = (1 - fraction) * start + fraction * stop


@Preview
@Composable
fun MainMenuCanvasPreview() {
    SimpleAppTheme {
        MainMenuCanvas()
    }

}