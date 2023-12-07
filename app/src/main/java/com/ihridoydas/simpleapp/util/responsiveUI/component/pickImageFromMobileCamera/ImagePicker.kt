package com.ihridoydas.simpleapp.util.responsiveUI.component.pickImageFromMobileCamera

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ImagePicker() {
    Column(modifier = Modifier.fillMaxSize()) {
        PickImageFromGallery()
        PickImageFromCamera()
    }
}