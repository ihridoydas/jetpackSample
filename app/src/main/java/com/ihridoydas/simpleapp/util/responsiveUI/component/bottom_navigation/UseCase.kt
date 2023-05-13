package com.ihridoydas.simpleapp.util.responsiveUI.component.bottom_navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@Composable
fun FluidBottomNavigationUseCase() {
    BottomNavigationFluid(navController = NavController(context = LocalContext.current))
}