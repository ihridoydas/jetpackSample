package com.ihridoydas.simpleapp.util.responsiveUI.component.shape.polyWaveShape

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.CoilImage
import com.ihridoydas.simpleapp.R

@Composable
fun PolygonImageComposable(modifier: Modifier) {
    val deltaXAnim = rememberInfiniteTransition()
    val dx by deltaXAnim.animateFloat(
        initialValue = 3f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )


    Image(painter = painterResource(id = R.drawable.base_image),
        contentDescription = "Awesome Image",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(width = 300.dp, height = 200.dp)
            .graphicsLayer {
                shadowElevation = 4.dp.toPx()
                shape = PolyShape(dx.toInt(), 100.dp.toPx())
                clip = true
            })

}