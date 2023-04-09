package com.ihridoydas.simpleapp.util.responsiveUI.component.animations.animatedFloatingActionMenu

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme

@Composable
fun BasePreview(content: @Composable () -> Unit) {
  SimpleAppTheme() {
    Surface(color = MaterialTheme.colors.background) {
      content()
    }
  }
}