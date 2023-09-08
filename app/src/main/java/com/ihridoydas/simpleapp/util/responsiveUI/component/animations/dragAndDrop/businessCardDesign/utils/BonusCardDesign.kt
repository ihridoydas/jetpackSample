package com.ihridoydas.simpleapp.util.responsiveUI.component.animations.dragAndDrop.businessCardDesign.utils

import androidx.annotation.DrawableRes
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

class BonusCardDesign {
    val background = mutableStateOf(Color.Gray)
    val decorations = mutableStateListOf<CardDecoration>()
}

class CardDecoration(
    @DrawableRes
    val drawableRes: Int,
    val position: Offset
)