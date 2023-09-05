package com.ihridoydas.simpleapp.util.responsiveUI.component.tabLayout.view

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TabBar() {
    Scaffold(
        content = {
            TabScreen()
        }
    )
}