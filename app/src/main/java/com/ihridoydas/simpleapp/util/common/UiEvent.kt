
package com.ihridoydas.simpleapp.util.common

sealed class UiEvent {

    // Pop Back Stack
    object PopBackStack : UiEvent()

    // Navigate to another screen
    data class Navigate(val route: String) : UiEvent()

    // Show Snack Bar
    data class ShowSnackBar(val message: String, val action: String? = null) : UiEvent()

    data class ScreenMode(val isSelect: Boolean) : UiEvent()

    data class SelectAllImages(val selectAllImages: Boolean) : UiEvent()

    data class AllImageSelectMode(val isSelectAll: Boolean) : UiEvent()
}
