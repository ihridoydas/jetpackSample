package com.ihridoydas.simpleapp.features.koreography.useCases

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.features.koreography.util.rememberKoreography

@Composable
fun RangoliAnimation() {
    val rotationLayer1 = remember {
        Animatable(0f)
    }
    val rotationLayer2 = remember {
        Animatable(0f)
    }
    val rotationLayer3 = remember {
        Animatable(0f)
    }

    val koreography = rememberKoreography {
        move(rotationLayer3, 90f, animationSpec = tween(durationMillis = 500))
        move(rotationLayer2, 90f, animationSpec = tween(durationMillis = 500))
        move(rotationLayer1, 90f, animationSpec = tween(durationMillis = 500))
        move(rotationLayer3, 180f, animationSpec = tween(durationMillis = 500))
        move(rotationLayer2, 180f, animationSpec = tween(durationMillis = 500))
        move(rotationLayer1, 180f, animationSpec = tween(durationMillis = 500))
        move(rotationLayer3, 90f, animationSpec = tween(durationMillis = 200))
        parallelMoves {
            move(rotationLayer2, 90f, animationSpec = tween(durationMillis = 700))
            move(rotationLayer1, 0f, animationSpec = tween(durationMillis = 1000))
        }
        parallelMoves {
            move(rotationLayer2, 0f, animationSpec = tween(durationMillis = 500))
            move(rotationLayer3, -90f, animationSpec = tween(durationMillis = 500))
            move(rotationLayer1, -90f, animationSpec = tween(durationMillis = 500))
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = 16.dp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            ) {
                Image(
                    modifier = Modifier.align(Alignment.Center),
                    painter = painterResource(id = R.drawable.rangoli_base),
                    contentDescription = null
                )
                Image(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .graphicsLayer { rotationZ = rotationLayer1.value },
                    painter = painterResource(id = R.drawable.rangoli_layer_1),
                    contentDescription = null
                )
                Image(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .graphicsLayer { rotationZ = rotationLayer2.value },
                    painter = painterResource(id = R.drawable.rangoli_layer_2),
                    contentDescription = null
                )
                Image(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .graphicsLayer { rotationZ = rotationLayer3.value },
                    painter = painterResource(id = R.drawable.rangoli_layer_3),
                    contentDescription = null
                )
            }
        }
    }

    LaunchedEffect(true) {
        koreography.danceForever(this)
    }
}
