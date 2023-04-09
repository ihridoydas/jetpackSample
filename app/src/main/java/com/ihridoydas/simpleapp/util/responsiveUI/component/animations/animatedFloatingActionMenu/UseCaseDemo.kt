package com.ihridoydas.simpleapp.util.responsiveUI.component.animations.animatedFloatingActionMenu

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun MainContent() {
    Scaffold(
        topBar = { PTopAppBar(title = "Demo AppBar") }) {
        Box {
            var isVisible by remember { mutableStateOf(false) }
            PLazyGrid()
            PFloatingBottomActionMenu(isVisible)
            PBottomAppBar(isVisible) { isVisible = !isVisible }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
fun Preview_MainContent() {
    SimpleAppTheme {
        MainContent()
    }
}