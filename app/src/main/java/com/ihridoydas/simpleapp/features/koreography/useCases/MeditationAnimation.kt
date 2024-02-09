package com.ihridoydas.simpleapp.features.koreography.useCases

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
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
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.features.koreography.util.rememberKoreography

@Composable
fun MeditationAnimation() {
    val offsetYMeditation = remember { Animatable(0f) }
    val offsetYMeditationAura = remember { Animatable(0f) }
    val scaleAura = remember { Animatable(1f) }
    val scaleShadow = remember { Animatable(1f) }
    val koreography = rememberKoreography {
        parallelMoves {
            move(
                animatable = offsetYMeditation,
                targetValue = -160f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1500),
                    repeatMode = RepeatMode.Reverse
                )
            )
            move(
                animatable = offsetYMeditationAura,
                targetValue = -80f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1500),
                    repeatMode = RepeatMode.Reverse
                )
            )
            move(
                animatable = scaleAura,
                targetValue = 1.2f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1500),
                    repeatMode = RepeatMode.Reverse
                )
            )
            move(
                animatable = scaleShadow,
                targetValue = 0.5f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1500),
                    repeatMode = RepeatMode.Reverse
                )
            )
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
                    .padding(64.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.meditation_aura),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .graphicsLayer {
                            translationY = offsetYMeditationAura.value
                            scaleX = scaleAura.value
                            scaleY = scaleAura.value
                        },
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                Image(
                    painter = painterResource(R.drawable.meditation_shadow),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .graphicsLayer {
                            scaleX = scaleShadow.value
                            transformOrigin = TransformOrigin(0.5f, 0.8f)
                        },
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                Image(
                    painter = painterResource(R.drawable.meditation),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .graphicsLayer {
                            translationY = offsetYMeditation.value
                        },
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }
    }

    LaunchedEffect(true) {
        koreography.danceForever(this)
    }
}
