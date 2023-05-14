package com.ihridoydas.simpleapp.util.responsiveUI.component.animations.animatedFloatingActionMenu

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

private const val duration: Int = 1000
private val intOffsetTweenSpec: TweenSpec<IntOffset> = tween(durationMillis = duration)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BoxScope.PFloatingBottomActionMenu(isVisible: Boolean) {

  val density = LocalDensity.current
  val context = LocalContext.current

  AnimatedVisibility(
    visible = isVisible,
    enter = slideInVertically(
      initialOffsetY = { with(density) { 250.dp.roundToPx() } },
      animationSpec = intOffsetTweenSpec,
    ), exit = slideOutVertically(
      targetOffsetY = { with(density) { 250.dp.roundToPx() } },
      animationSpec = intOffsetTweenSpec,
    ),
    modifier = Modifier
      .align(Alignment.BottomCenter)
      .height(100.dp)
  ) {

    Box(
      modifier = Modifier
        .fillMaxWidth()
        .background(
          brush = Brush.verticalGradient(
            0.5f to Color.White,
            0.9f to Color.White.copy(alpha = 0.3f),
            0.99f to Color.White.copy(alpha = 0.005f),
            startY = Float.POSITIVE_INFINITY,
            endY = 0.0f,
          )
        )
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .align(Alignment.Center)
      ) {
        PIconButton(
          text = "Add Place",
          imageVector = Icons.Default.Add,
          modifier = Modifier
            .clickable {
              Toast.makeText(context,"Clicked",Toast.LENGTH_LONG).show()
            }
            .weight(1f)
            .animateEnterExit(
              enter = slideInVertically(initialOffsetY = { 50 }, animationSpec = intOffsetTweenSpec),
              exit = slideOutVertically(targetOffsetY = { 50 }, animationSpec = intOffsetTweenSpec),
            )
        )
        PIconButton(
          text = "Create List",
          imageVector = Icons.Default.List,
          modifier = Modifier
            .clickable {
              Toast.makeText(context,"Clicked",Toast.LENGTH_LONG).show()
            }
            .weight(1f)
            .animateEnterExit(
              enter = slideInVertically(initialOffsetY = { 250 }, animationSpec = intOffsetTweenSpec),
              exit = slideOutVertically(targetOffsetY = { 250 }, animationSpec = intOffsetTweenSpec),
            )
        )
        PIconButton(
          text = "Add Friend",
          imageVector = Icons.Default.AccountBox,
          modifier = Modifier
            .clickable {
              Toast.makeText(context,"Clicked",Toast.LENGTH_LONG).show()
            }
            .weight(1f)
            .animateEnterExit(
              enter = slideInVertically(initialOffsetY = { 450 }, animationSpec = intOffsetTweenSpec),
              exit = slideOutVertically(targetOffsetY = { 450 }, animationSpec = intOffsetTweenSpec),
            )
        )
      }
    }
  }

}

@Preview
@Composable
fun Preview_FloatingBottomActionMenu() {
  BasePreview {
    Box {
      PFloatingBottomActionMenu(true)
    }
  }
}