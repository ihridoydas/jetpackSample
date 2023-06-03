package com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.ihridoydas.simpleapp.features.composibleSheep.sheep.ComposableSheep
import com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.model.SheepUiState
import com.ihridoydas.simpleapp.ui.theme.TextUnit

@Composable
fun SimpleVisibilityScreen(
    modifier: Modifier = Modifier,
) {
    var sheepUiState by remember { mutableStateOf(SheepUiState()) }

    // This declaration can be contained in the UiState, added here for clarity
    var isSheepVisible by remember { mutableStateOf(true) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
    ) {

        AnimatedVisibility(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            visible = isSheepVisible,
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
                modifier = Modifier
                    .size(sheepUiState.sheepSize)
                    .align(Alignment.CenterHorizontally)
            )
        }

        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            isSheepVisible = !isSheepVisible
            sheepUiState = sheepUiState.copy(isBlinking = !sheepUiState.isBlinking)
        }) {
            Text(text = "Sheep it!", fontWeight = FontWeight.Bold, fontSize = TextUnit.Twenty)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSheepAnimation() {
    SimpleVisibilityScreen()
}
