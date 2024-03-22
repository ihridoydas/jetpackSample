package com.ihridoydas.simpleapp.features.stepperCompose

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.features.funWithUI.components.baseclock.Clock
import com.ihridoydas.simpleapp.features.funWithUI.components.genderpicker.GenderPicker
import com.ihridoydas.simpleapp.features.funWithUI.components.tictactoe.TicTacToeGame
import com.ihridoydas.simpleapp.features.funWithUI.components.weightpicker.ScaleStyle
import com.ihridoydas.simpleapp.features.funWithUI.components.weightpicker.WeightPicker
import com.ihridoydas.simpleapp.features.globeAnimation.GloveAnimation
import com.ihridoydas.simpleapp.features.globeAnimation.GlovoLikeAnimation
import com.ihridoydas.simpleapp.features.stepperCompose.screens.MaterialStepperScreen
import com.ihridoydas.simpleapp.features.stepperCompose.screens.StateStepperScreen
import com.ihridoydas.simpleapp.features.ticTokToeGame.TicTacToeApp

@Preview(showSystemUi = true)
@Composable
fun StepperComposablePreview() {
    var tabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf(
        "Material",
        "State",
        "TicTokToe",
        "GlovoLike"
    )
    Column {
        TabRow(selectedTabIndex = tabIndex) {
            tabTitles.forEachIndexed { index, title ->
                Tab(selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    text = { Text(text = title) })
            }
        }
        when (tabIndex) {
            0 -> MaterialStepperScreen()
            1 -> StateStepperScreen()
            2 -> TicTacToeApp()
            3 -> GloveAnimation()
        }
    }
}