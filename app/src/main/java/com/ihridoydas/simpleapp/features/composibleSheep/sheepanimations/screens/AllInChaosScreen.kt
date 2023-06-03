package com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.screens

import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.features.composibleSheep.canvasExtensions.nextIndexLoop
import com.ihridoydas.simpleapp.features.composibleSheep.components.CheckBoxLabel
import com.ihridoydas.simpleapp.features.composibleSheep.components.SliderLabelValue
import com.ihridoydas.simpleapp.features.composibleSheep.components.StartStopBehaviorButton
import com.ihridoydas.simpleapp.features.composibleSheep.sheep.ComposableSheep
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.model.SheepCanvasHeight
import com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.model.SheepJumpSize
import com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.model.SheepJumpingOffset
import com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.model.SheepUiState
import com.ihridoydas.simpleapp.ui.theme.Grid
import com.ihridoydas.simpleapp.ui.theme.SheepColor
import com.ihridoydas.simpleapp.ui.theme.TextUnit

private val colors = listOf(
    SheepColor.Gray,
    SheepColor.Blue,
    SheepColor.Green,
    SheepColor.Purple,
    SheepColor.Magenta,
    SheepColor.Orange,
)

private val simpleColors = listOf(
    SheepColor.Gray,
    SheepColor.Green,
)

@Composable
fun AllInChaosScreen(
    modifier: Modifier = Modifier,
) {
    val verticalScroll = rememberScrollState()
    var sheepUiState by remember { mutableStateOf(SheepUiState()) }

    // This declarations can be contained in the UiState, added here for clarity

    // JUMP
    var offsetY by remember { mutableStateOf(0.dp) }

    var dampingRatio by remember { mutableStateOf(Spring.DampingRatioNoBouncy) }
    var stiffness by remember { mutableStateOf(Spring.StiffnessMedium) }

    // COLOR
    var colorIndex by remember { mutableStateOf(0) }
    val color = remember { Animatable(colors[0]) }
    var useSimpleColors by remember { mutableStateOf(false) }

    // VISIBILITY
    var alpha by remember { mutableStateOf(1f) }

    // SIZE
    var scale by remember { mutableStateOf(1f) }

    // APPEARING
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(sheepUiState) {
        verticalScroll.animateScrollTo(if (sheepUiState.isAnimating) 0 else verticalScroll.value)

        if (sheepUiState.animationsEnabled) {
            isVisible = true
            delay(500)
            // Color
            launch {
                while (sheepUiState.isGroovy) {
                    val colorsToUse = if (useSimpleColors) simpleColors else colors
                    colorIndex = colorsToUse.nextIndexLoop(colorIndex)
                    color.animateTo(
                        colorsToUse[colorIndex],
                        animationSpec = tween(durationMillis = 500, delayMillis = 200)
                    )
                }
            }
            // Jump
            launch {
                while (sheepUiState.isJumping) {
                    // Jump up
                    animate(
                        initialValue = 0f,
                        targetValue = SheepJumpingOffset,
                        animationSpec = spring(
                            dampingRatio = dampingRatio,
                            stiffness = stiffness,
                        )
                    ) { value, _ ->
                        offsetY = value.dp
                    }

                    // Jump down
                    animate(
                        initialValue = SheepJumpingOffset,
                        targetValue = 0f,
                        animationSpec = spring(
                            dampingRatio = dampingRatio,
                            stiffness = stiffness,
                        )
                    ) { value, _ ->
                        offsetY = value.dp
                    }
                }
            }
            // Alpha
            launch {
                while (sheepUiState.isBlinking) {
                    animate(
                        1f,
                        0f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(durationMillis = 500, delayMillis = 200),
                            repeatMode = RepeatMode.Reverse
                        )
                    ) { value, _ ->
                        alpha = value
                    }
                }
            }
            // Size
            launch {
                while (sheepUiState.isScaling) {
                    animate(
                        initialValue = 1f,
                        targetValue = 0f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(durationMillis = 500, delayMillis = 200),
                            repeatMode = RepeatMode.Reverse
                        )
                    ) { value, _ ->
                        scale = value
                    }
                }
            }
        } else {
            // Initial state
            animate(offsetY.value, 0f) { value, _ ->
                offsetY = value.dp
            }
            colorIndex = 0
            color.snapTo(colors[colorIndex])
            alpha = 1f
            scale = 1f
            isVisible = false
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(verticalScroll)
            .animateContentSize(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(SheepJumpSize + SheepCanvasHeight),
        ) {
            Spacer(modifier = Modifier.height(SheepJumpSize))
            AnimatedVisibility(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                visible = if (sheepUiState.isAppearing) isVisible else true,
                enter = scaleIn(
                    animationSpec = spring(
                        stiffness = Spring.StiffnessMediumLow,
                        dampingRatio = Spring.DampingRatioMediumBouncy
                    )
                ) + fadeIn(),
                exit = slideOutHorizontally { fullWidth -> -fullWidth.times(1.2).toInt() },
            ) {
                ComposableSheep(
                    sheep = sheepUiState.sheep,
                    fluffColor = color.value,
                    modifier = Modifier
                        .size(sheepUiState.sheepSize)
                        .align(Alignment.CenterHorizontally)
                        .offset(y = offsetY)
                        .scale(scale)
                        .alpha(alpha)
                )
            }
        }
        StartStopBehaviorButton(
            isBehaviorActive = sheepUiState.animationsEnabled,
            enabled = sheepUiState.hasAnimations || sheepUiState.animationsEnabled,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = MaterialTheme.colorScheme.onSecondaryContainer,
                disabledContentColor = MaterialTheme.colorScheme.onSecondary,
            ),
            onClick = {
                sheepUiState = sheepUiState.copy(
                    animationsEnabled = !sheepUiState.animationsEnabled
                )
            }
        ) {
            val text = if (sheepUiState.animationsEnabled) "Shtop it!" else "Sheep it!"
            Text(text = text, fontWeight = FontWeight.Bold, fontSize = TextUnit.Twenty)
        }

        // APPEARING
        CheckBoxLabel(text = "Appearing", checked = sheepUiState.isAppearing, onCheckedChange = {
            sheepUiState = sheepUiState.copy(isAppearing = !sheepUiState.isAppearing)
        })

        // SIZE
        CheckBoxLabel(text = "Scaling", checked = sheepUiState.isScaling, onCheckedChange = {
            sheepUiState = sheepUiState.copy(isScaling = !sheepUiState.isScaling)
        })

        // BLINKING
        CheckBoxLabel(text = "Blinking", checked = sheepUiState.isBlinking, onCheckedChange = {
            sheepUiState = sheepUiState.copy(isBlinking = !sheepUiState.isBlinking)
        })

        // COLORS
        CheckBoxLabel(text = "Groovy", checked = sheepUiState.isGroovy, onCheckedChange = {
            sheepUiState = sheepUiState.copy(isGroovy = !sheepUiState.isGroovy)
        })

        if (sheepUiState.isGroovy) {
            CheckBoxLabel(
                modifier = Modifier.padding(start = Grid.Two),
                text = "Use Simple Colors",
                checked = useSimpleColors,
                onCheckedChange = {
                    useSimpleColors = !useSimpleColors
                }
            )
        }

        // JUMP
        CheckBoxLabel(text = "Jumping", checked = sheepUiState.isJumping, onCheckedChange = {
            sheepUiState = sheepUiState.copy(isJumping = !sheepUiState.isJumping)
        })

        if (sheepUiState.isJumping) {
            SliderLabelValue(
                modifier = Modifier.fillMaxWidth(),
                text = "Damping Ratio",
                value = dampingRatio.times(100),
                onValueChange = { dampingRatio = it.div(100) },
                valueRange = 20f..200f
            )

            SliderLabelValue(
                modifier = Modifier.fillMaxWidth(),
                text = "Stiffness",
                value = stiffness,
                onValueChange = { stiffness = it },
                valueRange = Spring.StiffnessVeryLow..Spring.StiffnessHigh,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSheepAnimation() {
    AllInChaosScreen()
}
