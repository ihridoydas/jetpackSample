package com.ihridoydas.simpleapp.util.responsiveUI.component.draggableMenu.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.isSpecified

fun Offset.isSpecifiedAndValid(): Boolean {
    return isSpecified && isValid()
}