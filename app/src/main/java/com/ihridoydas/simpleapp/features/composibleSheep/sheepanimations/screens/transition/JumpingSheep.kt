package com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.screens.transition

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import com.ihridoydas.simpleapp.features.composibleSheep.canvasExtensions.nextItemLoop
import com.ihridoydas.simpleapp.features.composibleSheep.sheep.ComposableSheep
import com.ihridoydas.simpleapp.features.composibleSheep.sheep.model.DefaultHeadRotationAngle
import com.ihridoydas.simpleapp.features.composibleSheep.sheep.model.Sheep
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.model.SheepDefaultSize
import com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.model.SheepUiState
import com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.model.withGroovyJump
import com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.screens.transition.SheepJumpState.Start
import com.ihridoydas.simpleapp.ui.theme.SheepColor
import com.ihridoydas.simpleapp.ui.theme.ThemeShadow

@Composable
fun JumpingSheep(
    modifier: Modifier = Modifier,
    sheep: Sheep = Sheep(),
    size: DpSize = SheepDefaultSize,
    jumping: Boolean = true,
) {
    val sheepUiState = SheepUiState(sheep = sheep, sheepSize = size).withGroovyJump()
    JumpingSheep(sheepUiState = sheepUiState, modifier = modifier, jumping = jumping)
}

@Composable
fun JumpingSheep(
    sheepUiState: SheepUiState,
    modifier: Modifier = Modifier,
    jumping: Boolean = true,
) {
    var jumpState by remember { mutableStateOf(Start) }

    LaunchedEffect(jumping) {
        launch {
            while (jumping) {
                jumpState =
                    SheepJumpState.values().nextItemLoop(SheepJumpState.values().indexOf(jumpState))
                delay(jumpState.getDelayAfter())
            }
            jumpState = Start
        }
    }

    JumpingSheep(sheepUiState = sheepUiState, jumpState = jumpState, modifier = modifier)
}

@Composable
fun JumpingSheep(
    sheepUiState: SheepUiState,
    jumpState: SheepJumpState,
    modifier: Modifier = Modifier,
) {
    val jumpTransitionData = updateJumpTransitionData(sheepUiState, jumpState)
    val shadowColor = ThemeShadow().copy(0.5f)

    Box(
        modifier = modifier.height(sheepUiState.sheepJumpSize + sheepUiState.sheepSize.height)
    ) {

        if (sheepUiState.hasShadow) {
            Box(
                modifier = Modifier
                    .size(jumpTransitionData.shadowSize)
                    .align(Alignment.BottomCenter)
                    .drawBehind {
                        drawOval(color = shadowColor)
                    }
            )
        }

        ComposableSheep(
            sheep = sheepUiState.sheep.copy(headAngle = if (sheepUiState.isHeadBanging) jumpTransitionData.headAngle else DefaultHeadRotationAngle),
            fluffColor = if (sheepUiState.isGroovy) jumpTransitionData.color else SheepColor.Green,
            modifier = Modifier
                .size(sheepUiState.sheepSize)
                .scale(
                    scaleX = jumpTransitionData.scale.x,
                    scaleY = jumpTransitionData.scale.y
                )
                .align(Alignment.BottomCenter)
                .offset(y = jumpTransitionData.offsetY)
                .alpha(if (sheepUiState.isBlinking) jumpTransitionData.alpha else 1f),
            glassesTranslation = if (sheepUiState.movingGlasses) jumpTransitionData.glassesTranslation else 0f,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewJumpingSheep() {
    JumpingSheep(
        sheepUiState = SheepUiState(), jumpState = Start, modifier = Modifier.fillMaxSize()
    )
}
