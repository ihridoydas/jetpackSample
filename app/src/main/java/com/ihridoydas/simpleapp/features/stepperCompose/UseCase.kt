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
import androidx.compose.ui.tooling.preview.Preview
import com.ihridoydas.simpleapp.features.stepperCompose.screens.MaterialStepperScreen
import com.ihridoydas.simpleapp.features.stepperCompose.screens.StateStepperScreen

@Preview(showSystemUi = true)
@Composable
fun StepperComposablePreview() {
    var tabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Material", "State")
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
        }
    }
}