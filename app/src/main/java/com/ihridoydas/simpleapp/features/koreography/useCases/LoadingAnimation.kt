package com.ihridoydas.simpleapp.features.koreography.useCases

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.features.koreography.util.rememberKoreography

@Composable
fun LoadingAnimation() {
    val scaleXRect1 = remember { Animatable(1f) }

    val scaleXRect2 = remember { Animatable(1f) }

    val scaleYRect1 = remember { Animatable(1f) }

    val scaleYRect2 = remember { Animatable(1f) }

    val rect1HorizontalAlignment = remember { Animatable(-1f) }

    val rect1VerticalAlignment = remember { Animatable(-1f) }

    val rect2HorizontalAlignment = remember { Animatable(1f) }

    val rect2VerticalAlignment = remember { Animatable(1f) }

    val koreography = rememberKoreography {
        parallelMoves {
            move(animatable = scaleXRect1, targetValue = 2f, animationSpec = tween(200))
            move(animatable = scaleXRect2, targetValue = 2f, animationSpec = tween(200))
            move(
                animatable = rect2HorizontalAlignment,
                targetValue = -1f,
                animationSpec = tween(200)
            )
        }
        parallelMoves {
            move(animatable = scaleXRect1, targetValue = 1f, animationSpec = tween(200))
            move(animatable = scaleXRect2, targetValue = 1f, animationSpec = tween(200))
            move(
                animatable = rect1HorizontalAlignment,
                targetValue = 1f,
                animationSpec = tween(200)
            )
        }
        parallelMoves {
            move(animatable = scaleYRect1, targetValue = 2f, animationSpec = tween(200))
            move(animatable = scaleYRect2, targetValue = 2f, animationSpec = tween(200))
            move(
                animatable = rect2VerticalAlignment,
                targetValue = -1f,
                animationSpec = tween(200)
            )
        }
        parallelMoves {
            move(animatable = scaleYRect1, targetValue = 1f, animationSpec = tween(200))
            move(animatable = scaleYRect2, targetValue = 1f, animationSpec = tween(200))
            move(
                animatable = rect1VerticalAlignment,
                targetValue = 1f,
                animationSpec = tween(200)
            )
        }
        parallelMoves {
            move(animatable = scaleXRect1, targetValue = 2f, animationSpec = tween(200))
            move(animatable = scaleXRect2, targetValue = 2f, animationSpec = tween(200))
            move(
                animatable = rect1HorizontalAlignment,
                targetValue = -1f,
                animationSpec = tween(200)
            )
        }
        parallelMoves {
            move(animatable = scaleXRect1, targetValue = 1f, animationSpec = tween(200))
            move(animatable = scaleXRect2, targetValue = 1f, animationSpec = tween(200))
            move(
                animatable = rect2HorizontalAlignment,
                targetValue = 1f,
                animationSpec = tween(200)
            )
        }
        parallelMoves {
            move(animatable = scaleYRect1, targetValue = 2f, animationSpec = tween(200))
            move(animatable = scaleYRect2, targetValue = 2f, animationSpec = tween(200))
            move(
                animatable = rect1VerticalAlignment,
                targetValue = -1f,
                animationSpec = tween(200)
            )
        }
        parallelMoves {
            move(animatable = scaleYRect1, targetValue = 1f, animationSpec = tween(200))
            move(animatable = scaleYRect2, targetValue = 1f, animationSpec = tween(200))
            move(
                animatable = rect2VerticalAlignment,
                targetValue = 1f,
                animationSpec = tween(200)
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Card(modifier = Modifier.fillMaxWidth().align(Alignment.Center), shape = RoundedCornerShape(16.dp),
            elevation = 16.dp) {
            Box(
                modifier = Modifier.requiredWidth(232.dp).requiredHeight(240.dp).padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.vector_1),
                    contentDescription = null,
                    modifier = Modifier
                        .align(
                            BiasAlignment(
                                horizontalBias = rect1HorizontalAlignment.value,
                                verticalBias = rect1VerticalAlignment.value
                            )
                        )
                        .graphicsLayer {
                            scaleX = scaleXRect1.value
                            scaleY = scaleYRect1.value
                            transformOrigin = TransformOrigin(0f, 0f)
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.vector_2),
                    contentDescription = null,
                    modifier = Modifier
                        .align(
                            BiasAlignment(
                                horizontalBias = rect2HorizontalAlignment.value,
                                verticalBias = rect2VerticalAlignment.value
                            )
                        )
                        .graphicsLayer {
                            scaleX = scaleXRect2.value
                            scaleY = scaleYRect2.value
                            transformOrigin = TransformOrigin(0f, 0f)
                        }
                )
            }
        }
    }


    LaunchedEffect(true) {
        koreography.danceForever(this)
    }
}
