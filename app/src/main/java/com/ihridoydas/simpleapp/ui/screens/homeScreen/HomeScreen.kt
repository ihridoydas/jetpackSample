package com.ihridoydas.simpleapp.ui.screens.homeScreen

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ihridoydas.simpleapp.util.showcase.ShowcaseSample
import com.ihridoydas.simpleapp.util.showcase.onBoarding.OnBoardingViewModel

@Composable
fun HomeScreen(
    windowSizeClass: WindowSizeClass,
    navController: NavController,
) {
    //OnBoarding ViewModel
    val onBoardingViewModel : OnBoardingViewModel = hiltViewModel()

    //Show Case [Tap Target View]
    ShowcaseSample(windowSizeClass,navController,onBoardingViewModel)
    /*Column(
        modifier = Modifier
            .fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.clickable {
                navController?.navigate(ProfileScreenSpec.requestNavigationRoute()) {
                    popUpTo(HomeScreenSpec.route) {
                        inclusive = true
                    }
                }
            },
            text = "Click To Profile",
            textAlign = TextAlign.Center,
            fontSize = when (windowSizeClass.widthSizeClass) {
                        WindowWidthSizeClass.Compact -> dpToSp(16.dp)
                        WindowWidthSizeClass.Medium -> dpToSp(20.dp)
                        WindowWidthSizeClass.Expanded -> dpToSp(26.dp)
                        else -> dpToSp(40.dp)
                    } //MaterialTheme.typography.bodyMedium.fontSize
        )
    }*/

}