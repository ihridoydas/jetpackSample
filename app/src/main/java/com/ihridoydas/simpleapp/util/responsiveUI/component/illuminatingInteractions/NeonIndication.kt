package com.ihridoydas.simpleapp.util.responsiveUI.component.illuminatingInteractions

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationInstance
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.sign


@Composable
fun NeanIndication() {
    NeonButton(
        onClick = {},
        modifier = Modifier,
        enabled = true,
        content = {
            Text(text = "Hello, I am Nean Indication")
        }
    )
}







class NeonIndication(private val shape: RoundedCornerShape, private val borderWidth: Dp) : Indication {

    @Composable
    override fun rememberUpdatedInstance(interactionSource: InteractionSource): IndicationInstance {
        // key the remember against interactionSource, so if it changes we create a new instance
        val instance = remember(interactionSource) {
            NeonIndicationInstance(
                shape,
                // Double the border size for a stronger press effect
                borderWidth * 2
            )
        }

        LaunchedEffect(interactionSource) {
            interactionSource.interactions.collect { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> instance.animateToPressed(interaction.pressPosition, this)
                    is PressInteraction.Release -> instance.animateToResting(this)
                    is PressInteraction.Cancel -> instance.animateToResting(this)
                }
            }
        }

        return instance
    }

    private class NeonIndicationInstance(
        private val shape: RoundedCornerShape,
        private val borderWidth: Dp
    ) : IndicationInstance {
        var currentPressPosition: Offset = Offset.Zero
        val animatedProgress = Animatable(1f)
        val animatedPressAlpha = Animatable(1f)

        var pressedAnimation: Job? = null
        var restingAnimation: Job? = null

        fun animateToPressed(pressPosition: Offset, scope: CoroutineScope) {
            val currentPressedAnimation = pressedAnimation
            pressedAnimation = scope.launch {
                // Finish any existing animations, in case of a new press while we are still showing
                // an animation for a previous one
                restingAnimation?.cancelAndJoin()
                currentPressedAnimation?.cancelAndJoin()
                currentPressPosition = pressPosition
                animatedPressAlpha.snapTo(1f)
                animatedProgress.snapTo(0f)
                animatedProgress.animateTo(1f, tween(450))
            }
        }

        fun animateToResting(scope: CoroutineScope) {
            restingAnimation = scope.launch {
                // Wait for the existing press animation to finish if it is still ongoing
                pressedAnimation?.join()
                animatedPressAlpha.animateTo(0f, tween(250))
                animatedProgress.snapTo(0f)
            }
        }


        override fun ContentDrawScope.drawIndication() {
            val (startPosition, endPosition) = calculateGradientStartAndEndFromPressPosition(
                currentPressPosition, size
            )
            val brush = animateBrush(
                startPosition = startPosition,
                endPosition = endPosition,
                progress = animatedProgress.value
            )
            val alpha = animatedPressAlpha.value

            drawContent()

            val outline = shape.createOutline(size, layoutDirection, this)
            // Draw overlay on top of content
            drawOutline(
                outline = outline,
                brush = brush,
                alpha = alpha * 0.1f
            )
            // Draw border on top of overlay
            drawOutline(
                outline = outline,
                brush = brush,
                alpha = alpha,
                style = Stroke(width = borderWidth.toPx())
            )
        }

        /**
         * Calculates a gradient start / end where start is the point on the bounding rectangle of
         * size [size] that intercepts with the line drawn from the center to [pressPosition],
         * and end is the intercept on the opposite end of that line.
         */
        private fun calculateGradientStartAndEndFromPressPosition(
            pressPosition: Offset,
            size: Size
        ): Pair<Offset, Offset> {
            // Convert to offset from the center
            val offset = pressPosition - size.center
            // y = mx + c, c is 0, so just test for x and y to see where the intercept is
            val gradient = offset.y / offset.x
            // We are starting from the center, so halve the width and height - convert the sign
            // to match the offset
            val width = (size.width / 2f) * sign(offset.x)
            val height = (size.height  / 2f) * sign(offset.y)
            val x = height / gradient
            val y = gradient * width

            // Figure out which intercept lies within bounds
            val intercept = if (abs(y) <= abs(height)) {
                Offset(width, y)
            } else {
                Offset(x, height)
            }

            // Convert back to offsets from 0,0
            val start = intercept + size.center
            val end = Offset(size.width - start.x, size.height - start.y)
            return start to end
        }

        private fun animateBrush(
            startPosition: Offset,
            endPosition: Offset,
            progress: Float
        ): Brush {
            if (progress == 0f) return TransparentBrush

            // This is *expensive* - we are doing a lot of allocations on each animation frame. To
            // recreate a similar effect in a performant way, it would be better to create one large
            // gradient and translate it on each frame, instead of creating a whole new gradient
            // and shader. The current approach will be janky!
            val colorStops = buildList {
                when {
                    progress < 1/6f -> {
                        val adjustedProgress = progress * 6f
                        add(0f to Blue)
                        add(adjustedProgress to Color.Transparent)
                    }
                    progress < 2/6f -> {
                        val adjustedProgress = (progress - 1/6f) * 6f
                        add(0f to Purple)
                        add(adjustedProgress * MaxBlueStop to Blue)
                        add(adjustedProgress to Blue)
                        add(1f to Color.Transparent)
                    }
                    progress < 3/6f -> {
                        val adjustedProgress = (progress - 2/6f) * 6f
                        add(0f to Pink)
                        add(adjustedProgress * MaxPurpleStop to Purple)
                        add(MaxBlueStop to Blue)
                        add(1f to Blue)
                    }
                    progress < 4/6f -> {
                        val adjustedProgress = (progress - 3/6f) * 6f
                        add(0f to Orange)
                        add(adjustedProgress * MaxPinkStop to Pink)
                        add(MaxPurpleStop to Purple)
                        add(MaxBlueStop to Blue)
                        add(1f to Blue)
                    }
                    progress < 5/6f -> {
                        val adjustedProgress = (progress - 4/6f) * 6f
                        add(0f to Yellow)
                        add(adjustedProgress * MaxOrangeStop to Orange)
                        add(MaxPinkStop to Pink)
                        add(MaxPurpleStop to Purple)
                        add(MaxBlueStop to Blue)
                        add(1f to Blue)
                    }
                    else -> {
                        val adjustedProgress = (progress - 5/6f) * 6f
                        add(0f to Yellow)
                        add(adjustedProgress * MaxYellowStop to Yellow)
                        add(MaxOrangeStop to Orange)
                        add(MaxPinkStop to Pink)
                        add(MaxPurpleStop to Purple)
                        add(MaxBlueStop to Blue)
                        add(1f to Blue)
                    }
                }
            }

            return linearGradient(
                colorStops = colorStops.toTypedArray(),
                start = startPosition,
                end = endPosition
            )
        }

        companion object {
            val TransparentBrush = SolidColor(Color.Transparent)
            val Blue = Color(0xFF30C0D8)
            val Purple = Color(0xFF7848A8)
            val Pink = Color(0xFFF03078)
            val Orange = Color(0xFFF07800)
            val Yellow = Color(0xFFF0D800)
            const val MaxYellowStop = 0.16f
            const val MaxOrangeStop = 0.33f
            const val MaxPinkStop = 0.5f
            const val MaxPurpleStop = 0.67f
            const val MaxBlueStop = 0.83f
        }
    }
}

@Composable
fun NeonButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: RoundedCornerShape = CircleShape,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier
            .defaultMinSize(72.dp, 48.dp)
            .clickable(
                enabled = enabled,
                indication = remember { NeonIndication(shape, 2.dp) },
                interactionSource = interactionSource,
                onClick = onClick
            )
            .border(width = 2.dp, color = Color.Gray, shape = shape)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}