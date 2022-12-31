/*
 * Created by hridoydas on 2022/12/06
 * Last modified 12/6/22, 5:41 PM
 */

package com.ihridoydas.simpleapp.util.responsiveUI

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp


data class WindowSize(
    val width: WindowType,
    val height: WindowType
)

enum class WindowType { Compact, Small }


@Composable
fun rememberWindowSize(): WindowSize {
    val configuration = LocalConfiguration.current
    val screenWidth by remember(key1 = configuration) {
        mutableStateOf(configuration.screenWidthDp)
    }
    val screenHeight by remember(key1 = configuration) {
        mutableStateOf(configuration.screenHeightDp)
    }

    return WindowSize(
        width = when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                getScreenWidthLand(screenWidth)
            }
            Configuration.ORIENTATION_PORTRAIT -> {
                getScreenWidth(screenWidth)
            }
            else -> {
                getScreenWidth(screenWidth)
            }
        },
        height = when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                getScreenHeightLand(screenHeight)
            }
            Configuration.ORIENTATION_PORTRAIT -> {
                getScreenHeight(screenHeight)
            }
            else -> {
                getScreenHeight(screenHeight)
            }
        }
    )
}

// ORIENTATION PORTRAIT のため
fun getScreenWidth(width: Int): WindowType = when {
    width > 338 -> WindowType.Compact
    else -> WindowType.Small

}

fun getScreenHeight(height: Int): WindowType = when {
    height > 774 -> WindowType.Compact
    else -> WindowType.Small
}

// ORIENTATION LANDSCAPE のため
fun getScreenWidthLand(width: Int): WindowType = when {
    width >= 716 -> WindowType.Compact
    else -> WindowType.Small

}

fun getScreenHeightLand(height: Int): WindowType = when {
    height >= 320 -> WindowType.Compact
    else -> WindowType.Small
}
