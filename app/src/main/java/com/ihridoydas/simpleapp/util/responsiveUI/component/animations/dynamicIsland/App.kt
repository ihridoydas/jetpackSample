package com.ihridoydas.simpleapp.util.responsiveUI.component.animations.dynamicIsland

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.dynamicIsland.RadioButtonRow
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.dynamicIsland.island.DynamicIsland
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.dynamicIsland.island.IslandState

@Composable
fun DynamicIslandApp() {
    Column {
        var islandState: IslandState by remember { mutableStateOf(IslandState.DefaultState()) }

        DynamicIsland(islandState = islandState)

        RadioButtonRow(
            text = "Default",
            selected = islandState is IslandState.DefaultState
        ) {
            islandState = IslandState.DefaultState()
        }

        RadioButtonRow(
            text = "Call state",
            selected = islandState is IslandState.CallState
        ) {
            islandState = IslandState.CallState()
        }

        RadioButtonRow(
            text = "Call Timer state",
            selected = islandState is IslandState.CallTimerState
        ) {
            islandState = IslandState.CallTimerState()
        }

        RadioButtonRow(
            text = "Face unlock state",
            selected = islandState is IslandState.FaceUnlockState
        ) {
            islandState = IslandState.FaceUnlockState()
        }
    }
}

@Preview
@Composable
fun DynamicIsland() {
    SimpleAppTheme {
        DynamicIslandApp()
    }
}
