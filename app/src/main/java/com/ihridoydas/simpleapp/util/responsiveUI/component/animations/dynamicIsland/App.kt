package com.ihridoydas.simpleapp.util.responsiveUI.component.animations.dynamicIsland

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.dynamicIsland.RadioButtonRow
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.dynamicIsland.island.DynamicIsland
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.dynamicIsland.island.IslandState

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicIslandApp(onBackPress: ()-> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Dynamic Island") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackPress()
                        },
                        modifier = Modifier
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
        drawerShape = RoundedCornerShape(topEnd = 23.dp, bottomEnd = 23.dp),
        content = {

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
    )
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview
@Composable
fun DynamicIsland() {
    SimpleAppTheme {
        DynamicIslandApp({})
    }
}
