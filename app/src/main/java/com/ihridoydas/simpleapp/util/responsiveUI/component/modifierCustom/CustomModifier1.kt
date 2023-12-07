package com.ihridoydas.simpleapp.util.responsiveUI.component.modifierCustom

//Unleashing Creativity with Custom Modifiers in Android Jetpack Compose

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme

/**
 * Created by Nirbhay Pherwani on 8/18/2023.
 * Linktree - https://linktree.com/nirbhaypherwani
 */
fun Modifier.gradientBackground(colors: List<Color>): Modifier = composed {
    drawWithContent {
        drawRect(
            brush = Brush.verticalGradient(colors),
            size = size
        )
        drawContent()
    }
}

fun Modifier.aspectRatio(ratio: Float): Modifier = composed {
    layout { measurable, constraints ->
        val width = constraints.maxWidth
        val height = (width / ratio).toInt()
        val placeable = measurable.measure(
            constraints.copy(minHeight = height, maxHeight = height)
        )
        layout(width, height) {
            placeable.place(0, 0)
        }
    }
}

fun Modifier.floatingActionButtonAnimator(
    animationDurationMillis: Int = 1500,
    rotationAngle: Float = 180f
): Modifier = composed {
    val transition = rememberInfiniteTransition(label = "")
    val animatedProgress = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = animationDurationMillis
                0f at 0 with LinearEasing
                1f at 0 with LinearEasing
                0f at 1 with LinearEasing
            },
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    val rotation = animatedProgress.value * rotationAngle

    this.then(
        Modifier
            .graphicsLayer(rotationZ = rotation)
    )
}


//Use Case

@Preview(showBackground = true)
@Composable
fun CustomModifier1Preview() {

    SimpleAppTheme {
        // Sample Usages (Imports not included)

        Column(modifier = Modifier
            .gradientBackground(listOf(Color.Green, Color.Red))
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
// Usage #1
            Column() {
                FloatingActionButton(
                    onClick = { /* Handle click here */ },
                    modifier = Modifier
                        .floatingActionButtonAnimator(
                            animationDurationMillis = 700,
                            rotationAngle = 360f
                        )
                        .size(108.dp),
                    contentColor = Color.Red

                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Add", tint = Color.White)
                }

                FloatingActionButton(
                    onClick = { /* Handle click here */ },
                    modifier = Modifier
                        .floatingActionButtonAnimator(
                            animationDurationMillis = 3000,
                            rotationAngle = 90f
                        )
                        .size(108.dp),
                    contentColor = Color.Blue

                ) {
                    Icon(Icons.Filled.Build, contentDescription = "Build", tint = Color.White)
                }

                FloatingActionButton(
                    onClick = { /* Handle click here */ },
                    modifier = Modifier
                        .floatingActionButtonAnimator(
                            animationDurationMillis = 5000,
                            rotationAngle = 270f
                        )
                        .size(108.dp),
                    contentColor = Color.Green

                ) {
                    Icon(Icons.Filled.Call, contentDescription = "Call", tint = Color.White)
                }
            }

// Usage #2
            Column(
                modifier = Modifier
                    .gradientBackground(listOf(Color.Blue, Color.Green, Color.White))
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text("Custom modifier was applied on me!")
            }

// Usage #3
            Column(
                modifier = Modifier
                    .gradientBackground(listOf(Color.Blue, Color.White))
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.base_image),
                    contentDescription = null,
                    modifier = Modifier
                        .aspectRatio(16 / 9f)
                        .fillMaxWidth(), // modify as needed
                    contentScale = ContentScale.Crop
                )
            }

        }

    }

}