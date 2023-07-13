package com.ihridoydas.simpleapp.util.responsiveUI.component.shape.ticketShape

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.CoilImage
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.util.responsiveUI.component.shape.polyWaveShape.PolyShape
import kotlin.math.ceil

@Composable
fun TicketWaveComposable(modifier: Modifier) {
    val deltaXAnim = rememberInfiniteTransition()
    val dx by deltaXAnim.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(animation = tween(500, easing = LinearEasing))
    )

    Image(painter = painterResource(id = R.drawable.base_image),
        contentDescription = "Awesome Image",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(width = 300.dp, height = 200.dp)
            .graphicsLayer {
                val animDx = dx
                shadowElevation = 0.dp.toPx()
                shape = WavyShape(100.dp,50.dp)
                clip = true
            }
    )
}


class WavyShape(
    private val period: Dp,
    private val amplitude: Dp,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ) = Outline.Generic(Path().apply {
        val wavyPath = Path().apply {
            val halfPeriod = with(density) { period.toPx() } / 2
            val amplitude = with(density) { amplitude.toPx() }
            moveTo(x = -halfPeriod / 2, y = amplitude)
            repeat(ceil(size.width / halfPeriod + 1).toInt()) { i ->
                relativeQuadraticBezierTo(
                    dx1 = halfPeriod / 2,
                    dy1 = 2 * amplitude * (if (i % 2 == 0) 1 else -1),
                    dx2 = halfPeriod,
                    dy2 = 0f,
                )
            }
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
        }
        val boundsPath = Path().apply {
            addRect(Rect(offset = Offset.Zero, size = size))
        }
        op(wavyPath, boundsPath, PathOperation.Intersect)
    })
}