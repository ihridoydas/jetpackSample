
package com.ihridoydas.simpleapp.features.newTonsTimer.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver

object Colors {
    val androidGreen = Color(0xFF3DDC84)
    val navy = Color(0xFF073042)
    val purplish = Color(0xFF880E4F)
    val lightBlue = Color(0xFFE0F7FA)

    private val ballShadowColorDark = Color.Black.copy(alpha = 0.3f)
    private val ballShadowColorLight = Color.Black.copy(alpha = 0.1f)
    private val systemBarsScrim = Color(0x80000000)

    val systemBars @Composable get() = systemBarsScrim.compositeOver(MaterialTheme.colors.background)

    val ballShadow @Composable get() = if (MaterialTheme.colors.isLight) ballShadowColorLight else ballShadowColorDark
}
