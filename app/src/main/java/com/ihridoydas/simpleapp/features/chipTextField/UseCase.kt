package com.ihridoydas.simpleapp.features.chipTextField

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.features.chipTextField.chip.AvatarChips
import com.ihridoydas.simpleapp.features.chipTextField.chip.CHIP_TEXT_FILED_STYLES
import com.ihridoydas.simpleapp.features.chipTextField.chip.CheckableChips
import com.ihridoydas.simpleapp.features.chipTextField.chip.ChipFieldStyle
import com.ihridoydas.simpleapp.features.chipTextField.chip.ManualFocusChips
import com.ihridoydas.simpleapp.features.chipTextField.chip.TextChips
import com.ihridoydas.simpleapp.features.chipTextField.chip.ThemeColorSelector
import com.ihridoydas.simpleapp.features.chipTextField.chip.getDefaultChipFieldStyle
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme

@OptIn(
    ExperimentalComposeUiApi::class,
    androidx.compose.foundation.ExperimentalFoundationApi::class
)
@Composable
fun ChipTextFieldScreen() {
    val selectedColorPosition = remember { mutableStateOf(0) }

    val chipFieldStyle = getChipFieldStyle(selectedColorPosition.value)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        ThemeColorSelector(
            selectedPosition = selectedColorPosition,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        TextChips(chipFieldStyle = chipFieldStyle)

        Spacer(modifier = Modifier.height(32.dp))

        CheckableChips(chipFieldStyle = chipFieldStyle)

        Spacer(modifier = Modifier.height(32.dp))

        AvatarChips(chipFieldStyle = chipFieldStyle)

        Spacer(modifier = Modifier.height(32.dp))

        ManualFocusChips(chipFieldStyle = chipFieldStyle)

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun getChipFieldStyle(selectedPos: Int): ChipFieldStyle {
    return when (selectedPos) {
        0 -> {
            getDefaultChipFieldStyle()
        }
        else -> {
            CHIP_TEXT_FILED_STYLES[selectedPos]
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleAppTheme {
        ChipTextFieldScreen()
    }
}