package com.ihridoydas.simpleapp.util.responsiveUI.component.animations.animatedFloatingActionMenu

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PTopAppBar(title: String) {
  TopAppBar(backgroundColor = MaterialTheme.colors.primarySurface, elevation = 0.dp) {
    Text(
      modifier = Modifier.fillMaxWidth(),
      text = title,
      color = MaterialTheme.colors.onPrimary,
      style = MaterialTheme.typography.h5,
      textAlign = TextAlign.Center,
      fontStyle = FontStyle.Italic
    )
  }
}

@Preview
@Composable
fun Preview_PTopAppBar() {
  BasePreview {
    PTopAppBar(title = "Title")
  }
}