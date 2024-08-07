package com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
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
import com.ihridoydas.simpleapp.ui.theme.SheepColor
import com.ihridoydas.simpleapp.ui.theme.TextUnit

private const val Animated = true

@Composable
fun SimpleColorScreen(
    modifier: Modifier = Modifier,
) {
    var sheepUiState by remember { mutableStateOf(SheepUiState()) }

    val color by animateColorAsState(
        targetValue = if (sheepUiState.isGroovy) SheepColor.Green else SheepColor.Gray,
        animationSpec = if (Animated) tween(durationMillis = 500) else snap()
    )

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
    ) {

        ComposableSheep(
            sheep = sheepUiState.sheep,
            fluffColor = color,
            modifier = Modifier
                .size(sheepUiState.sheepSize)
                .align(Alignment.CenterHorizontally)
        )

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                sheepUiState = sheepUiState.copy(isGroovy = !sheepUiState.isGroovy)
            }
        ) {
            Text(text = "Sheep it!", fontWeight = FontWeight.Bold, fontSize = TextUnit.Twenty)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSheepAnimation() {
    SimpleColorScreen()
}
