package com.ihridoydas.simpleapp.util.responsiveUI.component.illuminatingInteractions

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest


private class ScaleIndicationInstance : IndicationInstance {
    var currentPressPosition: Offset = Offset.Zero
    val animatedScalePercent = Animatable(1f)

    suspend fun animateToPressed(pressPosition: Offset) {
        currentPressPosition = pressPosition
        animatedScalePercent.animateTo(0.9f, spring())
    }

    suspend fun animateToResting() {
        animatedScalePercent.animateTo(1f, spring())
    }


    override fun ContentDrawScope.drawIndication() {
        scale(
            scale = animatedScalePercent.value,
            pivot = currentPressPosition
        ) {
            this@drawIndication.drawContent()
        }
    }
}

// Singleton that can be reused
object ScaleIndication : Indication {
    @Composable
    override fun rememberUpdatedInstance(interactionSource: InteractionSource): IndicationInstance {
        // key the remember against interactionSource, so if it changes we create a new instance
        val instance = remember(interactionSource) { ScaleIndicationInstance() }

        LaunchedEffect(interactionSource) {
            interactionSource.interactions.collectLatest { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> instance.animateToPressed(interaction.pressPosition)
                    is PressInteraction.Release -> instance.animateToResting()
                    is PressInteraction.Cancel -> instance.animateToResting()
                }
            }
        }

        return instance
    }
}


@Composable
fun ScaleButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: RoundedCornerShape = CircleShape,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier
            .defaultMinSize(minWidth = 76.dp, minHeight = 48.dp)
            .clickable(
                enabled = enabled,
                indication = ScaleIndication,
                interactionSource = interactionSource,
                onClick = onClick
            )
            .border(width = 2.dp, color = Color.Blue, shape = shape)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}