package com.ihridoydas.simpleapp.util.responsiveUI.component.animations.flippable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlippableAnimationTransitions(onBackPress: ()-> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Flippable Animation") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackPress()
                        },
                        modifier = Modifier
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
        drawerShape = RoundedCornerShape(topEnd = 23.dp, bottomEnd = 23.dp),
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .padding(vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val controller = rememberFlipController()

                Flippable(
                    frontSide = {
                        // Composable content for the front side
                        EnglishWordFrontSide(flipController = controller)
                    },

                    backSide = {
                        // Composable content for the back side
                        EnglishWordBackSide(flipController = controller)
                    },

                    // To manually controll the flipping, you would need an instance of FlippableController.
                    // You can access it using rememberFlipController() method.
                    // This provides methods like controller.flip(), controller.flipToFront(), controller.flipToBack() etc.
                    flipController = controller,

                    // The obvious one - if you have done Jetpack Compose before.
                    modifier = Modifier,

                    // The duration it takes for the flip transition in Milliseconds. Default is 400
                    flipDurationMs = 400,

                    // If true, this will flip the view when touched.
                    // If you want to programatically flip the view without touching, use FlippableController.
                    flipOnTouch = true,

                    // If false, flipping will be disabled completely.
                    // The flipping will not be triggered with either touch or with controller methods.
                    flipEnabled = true,

                    // The Flippable is contained in a Box, so this tells
                    // the alignment to organize both Front and Back side composable.
                    contentAlignment = Alignment.TopCenter,

                    //If true, the Flippable will automatically flip back after
                    //duration defined in autoFlipDurationMs. By default, this is false..
                    autoFlip = false,

                    //The duration in Milliseconds after which auto-flip back animation will start. Default is 1 second.
                    autoFlipDurationMs = 1000,

                    // The animation type of flipping effect. Currently there are 4 animations.
                    // Horizontal Clockwise and Anti-Clockwise, Vertical Clockwise and Anti-Clockwise
                    // See animation types section below.
                    flipAnimationType = FlipAnimationType.HORIZONTAL_CLOCKWISE,

                    // The [GraphicsLayerScope.cameraDistance] for the flip animation.
                    // Sets the distance along the Z axis (orthogonal to the X/Y plane
                    // on which layers are drawn) from the camera to this layer.
                    cameraDistance = 30.0F,

                    // The listener which is triggered when flipping animation is finished.
                    onFlippedListener = { currentSide ->
                        // This is called when any flip animation is finished.
                        // This gives the current side which is visible now in Flippable.
                    }
                )

            }
        }
    )
}