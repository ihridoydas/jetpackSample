package com.ihridoydas.simpleapp.features.chipTextField.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.ihridoydas.simpleapp.features.chipTextField.chipStyle.Chip

class CheckableChip(text: String, isChecked: Boolean = false) : Chip(text) {
    var isChecked by mutableStateOf(isChecked)
}