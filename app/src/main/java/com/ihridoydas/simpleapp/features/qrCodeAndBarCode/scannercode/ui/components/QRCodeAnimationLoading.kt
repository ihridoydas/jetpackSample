package com.ihridoydas.simpleapp.features.qrCodeAndBarCode.scannercode.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.qrCodeScanningLine(): Modifier = composed {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val offsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    val screenWidth = LocalConfiguration.current.screenWidthDp
    size(width = (screenWidth * 0.8f).dp, height = (screenWidth * 0.8f).dp)

    val density = LocalDensity.current.density
    val size = ((screenWidth * 0.8f).dp * density).value.toInt() // Adjust the size as needed

    drawWithContent {
        // Draw the QR code scanning line
        drawLine(
            color = Color.Blue,
            start = Offset(center.x - size / 2, center.y - size / 2 + size * offsetY),
            end = Offset(center.x + size / 2, center.y - size / 2 + size * offsetY),
            strokeWidth = 5f,
        )
    }
}
@Preview
@Composable
fun QRCodeAnimationLoadingExample() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .qrCodeScanningLine()
    ) {
        // Your content goes here
    }
}

fun Modifier.qrShimmerLoadingAnimation(
    isLoadingCompleted: Boolean = true,
    isLightModeActive: Boolean = true,
    widthOfShadowBrush: Int = 1000,
    angleOfAxisY: Float = 270f,
    durationMillis: Int = 5000,
): Modifier {
    if (isLoadingCompleted) {
        return this
    }
    else {
        return composed {
            val shimmerColors = ShimmerAnimationData(isLightMode = isLightModeActive).getColours()

            val transition = rememberInfiniteTransition(label = "")

            val translateAnimation = transition.animateFloat(
                initialValue = 0f,
                targetValue = (durationMillis + widthOfShadowBrush).toFloat(),
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = durationMillis,
                        easing = LinearEasing,
                    ),
                    repeatMode = RepeatMode.Restart,
                ),
                label = "Shimmer loading animation",
            )

            this.background(
                brush = Brush.linearGradient(
                    colors = shimmerColors,
                    start = Offset(x = translateAnimation.value - widthOfShadowBrush, y = 0.0f),
                    end = Offset(x = translateAnimation.value, y = angleOfAxisY),
                ),
            )
        }
    }
}

data class ShimmerAnimationData(
    private val isLightMode: Boolean
) {
    fun getColours(): List<Color> {
        return if (isLightMode) {
            val color = Color.White

            listOf(
                color.copy(alpha = 0.1f),
                color.copy(alpha = 0.2f),
            )
        } else {
            val color = Color.Black

            listOf(
                color.copy(alpha = 0.0f),
                color.copy(alpha = 0.2f),
            )
        }
    }
}
