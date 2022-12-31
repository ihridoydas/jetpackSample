/*
 * Created by yusuf on 2022/09/01
 * Last modified 9/1/22, 2:48 PM
 * Copyright © 2022 Cognivision Inc. All rights reserved.
 */

package com.ihridoydas.simpleapp.util.responsiveUI

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

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


//Font Scaling Extension
@Composable
fun Int.scaledSp(): TextUnit {
    val value: Int = this
    return with(LocalDensity.current) {
        val fontScale = this.fontScale
        val textSize = value / fontScale
        textSize.sp
    }
}

val Int.scaledSp: TextUnit
    @Composable get() =  scaledSp()



//Dp to sp
@Composable
fun dpToSp(dp: Dp) = with(LocalDensity.current) { dp.toSp() }