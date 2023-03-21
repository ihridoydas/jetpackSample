package com.ihridoydas.simpleapp.util.responsiveUI.component.casecade

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class CascadeMenuState<T : Any>(currentMenuItem: CascadeMenuItem<T>) {
    private var _currentMenu by mutableStateOf(currentMenuItem)

    var currentMenuItem: CascadeMenuItem<T>
        get() = _currentMenu
        set(value) {
            _currentMenu = value
        }
}