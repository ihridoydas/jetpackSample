package com.ihridoydas.simpleapp.ui.screens.startScreen


import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ihridoydas.simpleapp.util.showcase.ShowcaseSample
import com.ihridoydas.simpleapp.util.showcase.onBoarding.OnBoardingViewModel

@Composable
fun StartShowCaseScreen(
    windowSizeClass: WindowSizeClass,
    navController: NavController,
) {
    //OnBoarding ViewModel
    val onBoardingViewModel : OnBoardingViewModel = hiltViewModel()

    //Show Case [Tap Target View]
    ShowcaseSample(windowSizeClass,navController,onBoardingViewModel)

}