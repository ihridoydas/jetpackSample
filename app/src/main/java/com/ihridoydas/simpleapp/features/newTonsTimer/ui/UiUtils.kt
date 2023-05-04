
package com.ihridoydas.simpleapp.features.newTonsTimer.ui

import android.app.Activity
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

val BoxWithConstraintsScope.isLandscape get() = maxWidth > maxHeight

fun Activity.setSystemBarsColor(color: Color) {
    window.statusBarColor = color.toArgb()
    window.navigationBarColor = color.toArgb()
}
