package com.ihridoydas.simpleapp.util.responsiveUI.component.coolBttonEffect

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow


/*========================================*/
@Composable
fun NoRippleEffect1() {
    Button(
        onClick = {
        },
        interactionSource = remember { NoRippleInteractionSource() },
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        Text(text = "Button without Ripple Effect")
    }
}

@Composable
fun NoRippleEffect2() {
    Box(
        modifier = Modifier
            .height(height = 38.dp)
            .background(
                color = Color.Red,
                shape = RoundedCornerShape(percent = 12)
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
            }
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Click me",
            color = Color.White
        )
    }

}

@Composable
fun NoRippleEffect3() {
    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        Button(
            onClick = {
                // Click
            }, shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            Text(text = "Click me")
        }
    }
}

// Way 3
private object NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = Color.Unspecified

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(
        draggedAlpha = 0.0f,
        focusedAlpha = 0.0f,
        hoveredAlpha = 0.0f,
        pressedAlpha = 0.0f
    )
}

// Way 1
class NoRippleInteractionSource : MutableInteractionSource {

    override val interactions: Flow<Interaction> = emptyFlow()

    override suspend fun emit(interaction: Interaction) {}

    override fun tryEmit(interaction: Interaction) = true
}

/*================= 2. BOUNCE TOUCH EFFECT =======================*/

@Composable
fun PulsateEffect() {
    Button(
        onClick = {
        }, shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.bounceClick()
    ) {
        Text(text = "Click me")
    }
}

fun Modifier.bounceClick() = composed {
    var buttonState by remember { mutableStateOf(ButtonState.Idle) }
    val scale by animateFloatAsState(if (buttonState == ButtonState.Pressed) 0.70f else 1f)

    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = { }
        )
        .pointerInput(buttonState) {
            awaitPointerEventScope {
                buttonState = if (buttonState == ButtonState.Pressed) {
                    waitForUpOrCancellation()
                    ButtonState.Idle
                } else {
                    awaitFirstDown(false)
                    ButtonState.Pressed
                }
            }
        }
}

enum class ButtonState { Pressed, Idle }

/*====================== 3. AnimatedShapeTouch ============================*/

@Composable
fun AnimatedShapeTouch() {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()
    val cornerRadius by animateDpAsState(targetValue = if (isPressed.value) 10.dp else 50.dp)

    Box(
        modifier = Modifier
            .background(color = Color.Red, RoundedCornerShape(cornerRadius))
            .size(100.dp)
            .clip(RoundedCornerShape(cornerRadius))
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple()
            ) {
            }
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Click!",
            color = Color.White
        )
    }


}

/*====================== 4. Jump on Touch ============================*/
@Composable
fun PressEffect() {
    Button(
        onClick = {
        }, shape = RoundedCornerShape(12.dp), contentPadding = PaddingValues(16.dp),
        modifier = Modifier.pressClickEffect()
    ) {
        Text(text = "Click me")
    }
}

fun Modifier.pressClickEffect() = composed {
    var buttonState by remember { mutableStateOf(ButtonState.Idle) }
    val ty by animateFloatAsState(if (buttonState == ButtonState.Pressed) 0f else -20f)

    this
        .graphicsLayer {
            translationY = ty
        }
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = { }
        )
        .pointerInput(buttonState) {
            awaitPointerEventScope {
                buttonState = if (buttonState == ButtonState.Pressed) {
                    waitForUpOrCancellation()
                    ButtonState.Idle
                } else {
                    awaitFirstDown(false)
                    ButtonState.Pressed
                }
            }
        }
}


/*====================== 5. Shake Touch ============================*/

@Composable
fun ShakeEffect() {
    Button(
        onClick = {
        }, shape = RoundedCornerShape(12.dp), contentPadding = PaddingValues(16.dp),
        modifier = Modifier.shakeClickEffect()
    ) {
        Text(text = "Click me")
    }
}

fun Modifier.shakeClickEffect() = composed {
    var buttonState by remember { mutableStateOf(ButtonState.Idle) }
    val tx by animateFloatAsState(
        targetValue = if (buttonState == ButtonState.Pressed) 0f else -50f,
        animationSpec = repeatable(
            iterations = 2,
            animation = tween(durationMillis = 50, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    this
        .graphicsLayer {
            translationX = tx
        }
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = { }
        )
        .pointerInput(buttonState) {
            awaitPointerEventScope {
                buttonState = if (buttonState == ButtonState.Pressed) {
                    waitForUpOrCancellation()
                    ButtonState.Idle
                } else {
                    awaitFirstDown(false)
                    ButtonState.Pressed
                }
            }
        }
}