package com.ihridoydas.simpleapp.features.funWithUI.components.baseclock

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import java.lang.Math.PI
import java.util.Calendar
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Clock(
    radius: Dp,
    modifier: Modifier = Modifier,

) {

    var currentMilliseconds by remember {
        mutableStateOf(Calendar.getInstance().timeInMillis)
    }

    LaunchedEffect(key1 = true) {
        while (isActive) {
            delay(1000)
            currentMilliseconds += 1000
        }
    }

    Canvas(modifier = modifier) {
        drawContext.canvas.nativeCanvas.drawCircle(
            center.x,
            center.y,
            radius.toPx(),
            Paint().apply {
                strokeWidth = 0.dp.toPx()
                color = android.graphics.Color.WHITE
                style = Paint.Style.FILL
                setShadowLayer(
                    60f,
                    0f,
                    0f,
                    android.graphics.Color.argb(50, 0,0,0)
                )
            }
        )
        for (i in 0 until 60) {
            val angleInRad = (i * 6 - 90) * ((PI / 180f).toFloat())

            val length = if (i % 5 == 0) 25.dp else 15.dp
            val stroke = if (i % 5 == 0) 2.dp else 1.dp

            val lineStart = Offset(
                x = (radius.toPx() - length.toPx()) * cos(angleInRad) + center.x,
                y = (radius.toPx() - length.toPx()) * sin(angleInRad) + center.y
            )
            val lineEnd = Offset(
                x = radius.toPx() * cos(angleInRad) + center.x,
                y = radius.toPx() * sin(angleInRad) + center.y
            )
                drawLine(
                    color = Color.Black,
                    start = lineStart,
                    end = lineEnd,
                    strokeWidth = stroke.toPx()
                )

        }

        val angleInRadSeconds = ((currentMilliseconds / 1000 % 60)  * 6 - 90) * ((PI / 180f).toFloat())
        val angleInRadMinutes = (currentMilliseconds / (1000 * 60) % 60 * 6 - 90) * ((PI / 180f).toFloat())
        val angleInRadHours = (currentMilliseconds / (1000 * 60 * 60 ) % 24 * 6 - 90) * ((PI / 180f).toFloat())

        val lineStart = Offset(
            x = center.x,
            y = center.y
        )
        val lineSecondsEnd = Offset(
            x = (radius.toPx() - 30.dp.toPx()) * cos(angleInRadSeconds) + center.x,
            y = (radius.toPx() - 30.dp.toPx()) * sin(angleInRadSeconds) + center.y
        )
        val lineMinutesEnd = Offset(
            x = (radius.toPx() - 80.dp.toPx()) * cos(angleInRadMinutes) + center.x,
            y = (radius.toPx() - 80.dp.toPx()) * sin(angleInRadMinutes) + center.y
        )
        val lineHoursEnd = Offset(
            x = (radius.toPx() - 100.dp.toPx()) * cos(angleInRadHours) + center.x,
            y = (radius.toPx() - 100.dp.toPx()) * sin(angleInRadHours) + center.y
        )

        drawLine(
            color = Color.Red,
            start = lineStart,
            end = lineSecondsEnd,
            strokeWidth = 3.dp.toPx()
        )

        drawLine(
            color = Color.Gray,
            start = lineStart,
            end = lineMinutesEnd,
            strokeWidth = 5.dp.toPx()
        )

        drawLine(
            color = Color.Black,
            start = lineStart,
            end = lineHoursEnd,
            strokeWidth = 7.dp.toPx()
        )
    }
}