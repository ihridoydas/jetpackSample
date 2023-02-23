package com.ihridoydas.simpleapp.util.common

import android.content.Context
import android.graphics.BlurMaskFilter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * PixelUtils オブジェクト
 * PixelからDPへ変換
 * DPからPixelへ変換
 */
object PixelUtils {

    fun dpToPx(dp: Int, context: Context): Float {
        val metrics = context.resources.displayMetrics
        return dp * metrics.density
    }

    fun pxToDp(px: Int, context: Context): Float {
        val metrics = context.resources.displayMetrics
        return px / metrics.density
    }
}