package com.ihridoydas.simpleapp.util.responsiveUI.component.animations.explodingAnimationTransition

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// Using Jetpack Compose Transition v2

fun <S> Transition.Segment<S>.transitioning(pair: Pair<S, S>): Boolean {
    return this.initialState == pair.first && this.targetState == pair.second
}

data class FabState(val fabColor: Color, val fabSize: Dp, val fabIconAlpha: Float)

@Composable
fun ExplodingFab(
    onClick: () -> Unit,
    icon: @Composable () -> Unit
) {
    val primaryColor = MaterialTheme.colors.primary
    val explodedColor = Color.White

    val initialState = FabState(fabColor = primaryColor, fabSize = 20.dp, fabIconAlpha = 0f)
    val normalState = FabState(fabColor = primaryColor, fabSize = 56.dp, fabIconAlpha = 1f)
    val explodedState = FabState(fabColor = explodedColor, fabSize = 1500.dp, fabIconAlpha = 0f)

    var fabState by remember { mutableStateOf(initialState) }

    val transition = updateTransition(
        targetState = fabState,
        label = "",
    )

    val fabColor by transition.animateColor(
        transitionSpec = {
            if (this.transitioning(initialState to normalState)) {
                tween(durationMillis = 500)
            } else {
                tween(durationMillis = 500, delayMillis = 300)
            }
        }, label = ""
    ) { it.fabColor }

    val fabSize by transition.animateDp(
        transitionSpec = {
            if (this.transitioning(initialState to normalState)) {
                tween(durationMillis = 100)
            } else {
                keyframes {
                    durationMillis = 500
                    58.dp at 0
                    48.dp at 200
                    1500.dp at 500
                }
            }
        }, label = ""
    ) { it.fabSize }

    val fabIconAlpha by transition.animateFloat(label = "") { it.fabIconAlpha }

    Fab(
        backgroundColor = fabColor,
        alpha = fabIconAlpha,
        fabSize = fabSize,
        icon = icon,
        onClick = {
            fabState = if (fabState == normalState) {
                explodedState
            } else {
                normalState
            }
        }
    )
}

@Composable
fun Fab(
    onClick: () -> Unit,
    backgroundColor: Color,
    fabSize: Dp,
    alpha: Float,
    icon: @Composable () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        backgroundColor = backgroundColor,
        modifier = Modifier.size(fabSize)
    ) {
        Box(
            modifier = Modifier.alpha(alpha)
        ) {
            icon()
        }
    }
}

@Preview
@Composable
fun CheckIt() {
    ExplodingFab(
        onClick = {},
        icon = {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = "Add FAB",
                tint = Color.White,
            )
        }
    )
}