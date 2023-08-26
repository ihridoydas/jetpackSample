package com.ihridoydas.simpleapp.util.responsiveUI.component.appIconChanger.utils

import androidx.annotation.DrawableRes

data class AppIcon(
    val component: String,

    @DrawableRes
    val foregroundResource: Int,
)
