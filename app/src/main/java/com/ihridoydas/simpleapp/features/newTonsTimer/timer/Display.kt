
package com.ihridoydas.simpleapp.features.newTonsTimer.timer

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import java.util.concurrent.TimeUnit

@Composable
fun Display(viewModel: TimerViewModel, modifier: Modifier = Modifier) {
    BoxWithConstraints(modifier, Alignment.Center) {
        val digitsColor by animateColorAsState(MaterialTheme.colors.primaryVariant)
        val separatorsColor by animateColorAsState(MaterialTheme.colors.onBackground)
        val fontSize = LocalDensity.current.calculateTimerFontSizeIn(constraints)

        Text(
            text = formatTime(viewModel.displayedMillis, viewModel.isConfigured, separatorsColor, fontSize),
            color = digitsColor,
            fontWeight = FontWeight.Thin,
            fontSize = fontSize,
            textAlign = TextAlign.Center
        )
    }
}

private fun formatTime(millis: Long, isConfigured: Boolean, separatorsColor: Color, fontSize: TextUnit) = AnnotatedString.Builder("").apply {
    val minutes = TimeUnit.MILLISECONDS.toMinutes(millis) % 60
    val seconds = TimeUnit.MILLISECONDS.toSeconds(millis) % 60
    val tenthsOfSecond = millis % 1000 / 100
    when (isConfigured) {
        true -> appendConfiguredTime(minutes, seconds, tenthsOfSecond, separatorsColor)
        false -> appendNotConfiguredTime(minutes, seconds, separatorsColor, fontSize)
    }
}.toAnnotatedString()

private fun AnnotatedString.Builder.appendConfiguredTime(minutes: Long, seconds: Long, tenthsOfSecond: Long, separatorsColor: Color) {
    val separatorsStyle = SpanStyle(separatorsColor)
    if (minutes > 0) {
        append(String.format("%02d", minutes))
        withStyle(separatorsStyle) { append(":") }
    }
    append(String.format("%02d", seconds))
    withStyle(separatorsStyle) { append(".") }
    append(String.format("%d", tenthsOfSecond))
}

private fun AnnotatedString.Builder.appendNotConfiguredTime(minutes: Long, seconds: Long, separatorsColor: Color, fontSize: TextUnit) {
    val separatorsStyle = SpanStyle(separatorsColor, fontSize = fontSize / 3)
    append(String.format("%02d", minutes))
    withStyle(separatorsStyle) { append("m ") }
    append(String.format("%02d", seconds))
    withStyle(separatorsStyle) { append("s") }
}

@Composable
private fun Density.calculateTimerFontSizeIn(constraints: Constraints): TextUnit {
    val bounds = IntSize(constraints.maxWidth, constraints.maxHeight)
    return remember(this, bounds) {
        (bounds.width * TIMER_FONT_SIZE_HORIZONTAL_FRACTION)
            .coerceAtMost(bounds.height * TIMER_FONT_SIZE_VERTICAL_FRACTION)
            .toSp()
    }
}

private const val TIMER_FONT_SIZE_HORIZONTAL_FRACTION = 0.24f
private const val TIMER_FONT_SIZE_VERTICAL_FRACTION = 0.9f
