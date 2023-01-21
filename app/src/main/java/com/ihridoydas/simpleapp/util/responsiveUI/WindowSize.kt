/*
 * Created by hridoydas on 2022/12/06
 * Last modified 12/6/22, 5:41 PM
 */

package com.ihridoydas.simpleapp.util.responsiveUI

import android.content.res.Configuration
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalConfiguration


data class WindowSize(
    val width: WindowType,
    val height: WindowType
)

enum class WindowType { Normal, Small }


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
    width > 338 -> WindowType.Normal
    else -> WindowType.Small

}

fun getScreenHeight(height: Int): WindowType = when {
    height > 774 -> WindowType.Normal
    else -> WindowType.Small
}

// ORIENTATION LANDSCAPE のため
fun getScreenWidthLand(width: Int): WindowType = when {
    width >= 716 -> WindowType.Normal
    else -> WindowType.Small

}

fun getScreenHeightLand(height: Int): WindowType = when {
    height >= 320 -> WindowType.Normal
    else -> WindowType.Small
}
