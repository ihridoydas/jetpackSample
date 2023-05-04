
package com.ihridoydas.simpleapp.features.newTonsTimer.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Colors.androidGreen,
    primaryVariant = Colors.androidGreen,
    secondary = Colors.androidGreen,
    background = Colors.navy
)

private val LightColorPalette = lightColors(
    primary = Colors.purplish,
    primaryVariant = Colors.purplish,
    secondary = Colors.purplish,
    background = Colors.lightBlue
)

@Composable
fun MyTheme(darkMode: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        colors = if (darkMode) DarkColorPalette else LightColorPalette,
        content = content
    )
}
