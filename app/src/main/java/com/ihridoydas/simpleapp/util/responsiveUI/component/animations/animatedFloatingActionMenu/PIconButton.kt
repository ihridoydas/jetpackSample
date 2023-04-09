package com.ihridoydas.simpleapp.util.responsiveUI.component.animations.animatedFloatingActionMenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

@Composable
fun RowScope.PIconButton(
  text: String,
  imageVector: ImageVector,
  modifier: Modifier = Modifier
) {
  Column(modifier = modifier) {
    Box(
      modifier = Modifier
        .clip(CircleShape)
        .size(40.dp)
        .background(Color.Black)
        .align(Alignment.CenterHorizontally)
    ) {
      Icon(
        imageVector,
        contentDescription = text,
        tint = Color.White,
        modifier = Modifier
          .align(Alignment.Center)
          .size(30.dp)
      )
    }
    Spacer(modifier = Modifier.height(4.dp))
    Text(
      text = text.uppercase(Locale.getDefault()),
      modifier = Modifier.fillMaxWidth(),
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.caption.copy(
        letterSpacing = 1.1.sp,
        fontWeight = FontWeight.Medium,
      )
    )
  }
}

@Preview
@Composable
fun Preview_PIconButton() {
  BasePreview {
    Row {
      PIconButton("Add Place", Icons.Default.Add)
    }
  }
}