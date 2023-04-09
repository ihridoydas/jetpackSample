package com.ihridoydas.simpleapp.util.responsiveUI.component.animations.animatedFloatingActionMenu

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@ExperimentalFoundationApi
@Composable
fun PLazyGrid() {

  val randomColors = listOf(
    Color(0xFFFF5252),
    Color(0xFF448AFF),
    Color(0xFFFF9800),
    Color(0x42000000),
    Color(0xFF69F0AE),
    Color(0xFFFF4081),
    Color(0xFF607D8B),
    Color(0xFF536DFE),
    Color(0xFF64FFDA),
    Color(0x1FFFFFFF),
    Color(0xFF4CAF50),
    Color(0xFFF44336),
  )

  LazyVerticalGrid(
    columns = GridCells.Fixed(2),
    contentPadding = PaddingValues(8.dp),
  ) {
    itemsIndexed(randomColors) { index, item ->
      Box(
        modifier = Modifier
          .padding(all = 4.dp)
          .background(color = item)
          .padding(vertical = 32.dp)
      ) {
        Text(
          text = index.toString(),
          modifier = Modifier.fillMaxSize(),
          textAlign = TextAlign.Center,
          style = MaterialTheme.typography.h2.copy(fontWeight = FontWeight.W100),
        )
      }
    }
  }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewPLazyGrid() {
  BasePreview {
    PLazyGrid()
  }
}