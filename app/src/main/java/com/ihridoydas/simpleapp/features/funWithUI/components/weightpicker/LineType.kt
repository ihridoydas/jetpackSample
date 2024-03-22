package com.ihridoydas.simpleapp.features.funWithUI.components.weightpicker

sealed interface LineType {
    object Normal : LineType
    object FiveStep : LineType
    object TenStep : LineType
}