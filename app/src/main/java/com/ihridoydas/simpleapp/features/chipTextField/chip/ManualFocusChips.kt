package com.ihridoydas.simpleapp.features.chipTextField.chip

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.features.chipTextField.chipStyle.ChipTextField
import com.ihridoydas.simpleapp.features.chipTextField.chipStyle.ChipTextFieldDefaults
import com.ihridoydas.simpleapp.features.chipTextField.chipStyle.rememberChipTextFieldState
import com.ihridoydas.simpleapp.features.chipTextField.data.AvatarChip
import com.ihridoydas.simpleapp.features.chipTextField.data.SampleChips

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
internal fun ManualFocusChips(
    chipFieldStyle: ChipFieldStyle,
) {
    val state = rememberChipTextFieldState(
        chips = remember { SampleChips.getTextChips() },
    )

    ChipsHeader("Request focus")

    Row(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Button(
            onClick = {
                if (state.focusedChipIndex > 0) {
                    state.focusChip(state.focusedChipIndex - 1)
                } else {
                    state.clearChipFocus(0)
                }
            }
        ) {
            Text("Prev chip")
        }

        Button(
            onClick = {
                if (state.focusedChipIndex < state.chips.size - 1) {
                    state.focusChip(state.focusedChipIndex + 1)
                }
            },
        ) {
            Text("Next chip")
        }

        Button(
            onClick = {
                if (state.isTextFieldFocused) {
                    state.clearTextFieldFocus()
                } else {
                    state.focusTextField()
                }
            },
        ) {
            Text("Text field")
        }
    }

    ChipTextField(
        state = state,
        onSubmit = { AvatarChip(it, SampleChips.randomAvatarUrl()) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = chipFieldStyle.cursorColor,
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = chipFieldStyle.cursorColor,
        ),
        chipStyle = ChipTextFieldDefaults.chipStyle(
            focusedTextColor = chipFieldStyle.textColor,
            focusedBorderColor = chipFieldStyle.borderColor,
            focusedBackgroundColor = chipFieldStyle.backgroundColor,
        ),
        contentPadding = PaddingValues(bottom = 8.dp),
    )
}
