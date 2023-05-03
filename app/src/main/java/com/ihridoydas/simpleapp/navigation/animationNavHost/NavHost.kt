package com.ihridoydas.simpleapp.navigation.animationNavHost

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ScaffoldState
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.systemuicontroller.SystemUiController
import com.ihridoydas.simpleapp.ui.screens.homeScreen.HomeScreen
import com.ihridoydas.simpleapp.ui.screens.boardingScreen.OnBoardingScreen
import com.ihridoydas.simpleapp.ui.screens.startScreen.StartShowCaseScreen
import com.ihridoydas.simpleapp.ui.screens.viewScreen.MainScreen
import com.ihridoydas.simpleapp.util.responsiveUI.component.webView.WebBrowser
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainAnimationNavHost(
    navController: NavHostController,
    windowSizeClass: WindowSizeClass,
    scaffoldState: ScaffoldState,
    coroutineScope : CoroutineScope,
    systemUiController: SystemUiController
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = ScreenDestinations.StartShowCaseScreen.route,
    ) {
        screen(ScreenDestinations.StartShowCaseScreen.route) {
            StartShowCaseScreen(windowSizeClass = windowSizeClass, navController = navController)
        }
        screen(ScreenDestinations.HomeScreen.route) {
            HomeScreen(windowSizeClass = windowSizeClass, navController = navController)
        }
        screen(ScreenDestinations.ViewScreen.route) {

            MainScreen(windowSizeClass = windowSizeClass, navController = navController, state = scaffoldState, coroutineScope = coroutineScope)
        }
        screen(ScreenDestinations.ProfileScreen.route) {
            OnBoardingScreen(
                windowSizeClass = windowSizeClass,
                navController = navController,
                onBackPress = {
                    navController.navigateTo(ScreenDestinations.ViewScreen.route)
                }
            )
        }
        screen(ScreenDestinations.WebViewScreen.route) {
            WebBrowser(
                windowSizeClass = windowSizeClass,
                navController = navController,
                onBackPress ={
                    navController.navigateTo(ScreenDestinations.ProfileScreen.route)
                }
            )
        }
    }

    //Back Handler
    BackHandler {
        navController.popBackStack()
    }

}