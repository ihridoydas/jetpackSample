package com.ihridoydas.simpleapp.util.responsiveUI.component.card

import androidx.compose.ui.graphics.Color

data class CardData(
    val text: String,
    val color1: Color,
    val color2: Color,
    val feature: () -> Unit
)