package com.ihridoydas.simpleapp.util.responsiveUI.component.animations.animatedFloatingActionMenu

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private const val duration: Int = 1000
private val floatTweenSpec: TweenSpec<Float> = tween(durationMillis = duration)
private val colorTweenSpec: TweenSpec<Color> = tween(durationMillis = duration)

@Composable
fun BoxScope.PBottomAppBar(isVisible: Boolean, _onIconTap: () -> Unit) {

  val animatedIconFGColor by
  animateColorAsState(
    targetValue = if (isVisible) Color.Black else Color.White,
    animationSpec = colorTweenSpec
  )
  val animatedIconBGColor by
  animateColorAsState(
    targetValue = if (isVisible) Color(0xFFEAEAEA) else Color(0xFFFF3841),
    animationSpec = colorTweenSpec
  )
  val animatedRotateAngle by
  animateFloatAsState(
    targetValue = if (isVisible) 135.0f else 0.0f,
    animationSpec = floatTweenSpec
  )

  BottomAppBar(
    modifier = Modifier
      .fillMaxWidth()
      .align(Alignment.BottomCenter),
    elevation = 0.dp
  ) {
    Icon(
      Icons.Outlined.LocationOn,
      contentDescription = "Location",
      tint = Color.Black,
      modifier = Modifier.weight(1f)
    )
    Icon(
      Icons.Outlined.Email,
      contentDescription = "Email",
      tint = Color.Black,
      modifier = Modifier.weight(1f)
    )
    Box(
      modifier = Modifier
        .weight(1f)
    ) {
      Box(
        modifier = Modifier
          .padding(bottom = 5.dp)
          .size(40.dp)
          .align(Alignment.TopCenter)
          .clip(CircleShape)
          .background(animatedIconBGColor)
          .clickable {
            _onIconTap()
          }
      )
      Icon(
        Icons.Outlined.Add,
        contentDescription = "Add",
        tint = animatedIconFGColor,
        modifier = Modifier
          .size(35.dp)
          .padding(bottom = 5.dp)
          .align(Alignment.Center)
          .rotate(animatedRotateAngle)

      )
    }
    Icon(
      Icons.Outlined.Notifications,
      contentDescription = "Notifications",
      tint = Color.Black,
      modifier = Modifier.weight(1f)
    )
    Icon(
      Icons.Outlined.Person,
      contentDescription = "Person",
      tint = Color.Black,
      modifier = Modifier.weight(1f)
    )
  }
}

@Preview
@Composable
fun Preview_BottomAppBar() {

  BasePreview {
    Box {
      PBottomAppBar(isVisible = false) { }
    }
  }
}