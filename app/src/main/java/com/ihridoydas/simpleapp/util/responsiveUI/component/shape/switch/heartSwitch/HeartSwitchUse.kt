package com.ihridoydas.simpleapp.util.responsiveUI.component.shape.switch.heartSwitch

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme

@Composable
fun AdvancedUsage() {
    var state by remember { mutableStateOf(true) }
    HeartSwitch(
        checked = state,
        onCheckedChange = { state = it },
        modifier = Modifier,
        colors = HeartSwitchColors(
            checkedTrackColor = Color(0xFFE91E63),
            checkedTrackBorderColor = Color(0xFFC2185B),
            uncheckedTrackBorderColor = Color(0xFFBDBDBD)
        ),
        width = 34.dp,
        borderWidth = 2.1.dp,
        movementAnimSpec = spring(stiffness = Spring.StiffnessMediumLow),
        colorsAnimSpec = spring(stiffness = Spring.StiffnessMediumLow),
        thumb = { modifier, color ->
            Box(
                modifier = modifier
                    .shadow(12.dp, CircleShape)
                    .background(color.value, CircleShape)
            )
        },
        enabled = true,
        interactionSource = remember { MutableInteractionSource() },
    )
}

@Preview
@Composable
fun UseCase() {
    SimpleAppTheme {
        AdvancedUsage()
    }
}