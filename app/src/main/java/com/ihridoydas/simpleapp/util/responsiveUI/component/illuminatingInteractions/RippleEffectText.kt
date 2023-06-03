package com.ihridoydas.simpleapp.util.responsiveUI.component.illuminatingInteractions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun RippleEffect() {
    // This InteractionSource will emit hover interactions
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        Modifier
            .size(200.dp)
            .clickable(
                onClick = {},
                interactionSource = interactionSource,
                // Also show a ripple effect, this is covered later in ‘Indicating Indications’
                indication = rememberRipple()
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Hello I am Ripple!",
            textAlign = TextAlign.Center

        )
    }



}