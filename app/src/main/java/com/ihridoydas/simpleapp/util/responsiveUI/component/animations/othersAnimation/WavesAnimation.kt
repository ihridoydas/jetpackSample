package com.ihridoydas.simpleapp.util.responsiveUI.component.animations.othersAnimation


import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.ui.theme.ThemeColor
import kotlinx.coroutines.delay
import com.ihridoydas.simpleapp.R

@Preview
@Composable
fun PreviewWaveAnimation() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = ThemeColor
    ) {
        WavesAnimation()
    }
}

@Composable
fun WavesAnimation() {

    val waves = listOf(
        remember { Animatable(0f) },
        remember { Animatable(0f) },
        remember { Animatable(0f) },
        remember { Animatable(0f) },
    )

    val animationSpec = infiniteRepeatable<Float>(
        animation = tween(4000, easing = FastOutLinearInEasing),
        repeatMode = RepeatMode.Restart,
    )

    waves.forEachIndexed { index, animatable ->
        LaunchedEffect(animatable) {
            delay(index * 1000L)
            animatable.animateTo(
                targetValue = 1f, animationSpec = animationSpec
            )
        }
    }

    val dys = waves.map { it.value }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Center
    ) {
        // Waves
        dys.forEach { dy ->
            Box(
                Modifier
                    .size(50.dp)
                    .align(Alignment.Center)
                    .graphicsLayer {
                        scaleX = dy * 4 + 1
                        scaleY = dy * 4 + 1
                        alpha = 1 - dy
                    },
            ) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(color = Color.White, shape = CircleShape)
                )
            }
        }

        // Mic icon
        Box(
            Modifier
                .size(50.dp)
                .align(Alignment.Center)
                .background(color = Color.White, shape = CircleShape)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_mic_24),
                "",
                tint = Color.Black,
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.Center)
            )
        }

    }

}