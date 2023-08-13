package com.ihridoydas.simpleapp.features.chipTextField.chip

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ihridoydas.simpleapp.features.chipTextField.chipStyle.ChipTextField
import com.ihridoydas.simpleapp.features.chipTextField.chipStyle.ChipTextFieldDefaults
import com.ihridoydas.simpleapp.features.chipTextField.chipStyle.rememberChipTextFieldState
import com.ihridoydas.simpleapp.features.chipTextField.data.AvatarChip
import com.ihridoydas.simpleapp.features.chipTextField.data.SampleChips

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
internal fun AvatarChips(
    chipFieldStyle: ChipFieldStyle
) {
    val state = rememberChipTextFieldState(
        chips = remember { SampleChips.getAvatarChips() },
    )

    ChipsHeader("Avatar chips")

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
        chipLeadingIcon = { Avatar(it) },
        contentPadding = PaddingValues(bottom = 8.dp),
    )
}

@Composable
private fun Avatar(
    chip: AvatarChip,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = chip.avatarUrl,
        contentDescription = null,
        modifier = modifier
            .size(32.dp)
            .clip(shape = CircleShape)
            .background(MaterialTheme.colors.onBackground.copy(alpha = 0.2f)),
    )
}
