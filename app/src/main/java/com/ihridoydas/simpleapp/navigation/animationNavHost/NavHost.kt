package com.ihridoydas.simpleapp.navigation.animationNavHost

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ScaffoldState
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.systemuicontroller.SystemUiController
import com.ihridoydas.simpleapp.features.barCodeScanner.BarCodeScreen
import com.ihridoydas.simpleapp.features.cameraScreen.CameraScreen
import com.ihridoydas.simpleapp.features.multiLanguage.MultiLanguage
import com.ihridoydas.simpleapp.features.newTonsTimer.timer.NewtonsTimerScreen
import com.ihridoydas.simpleapp.features.ocr.OCRScreen
import com.ihridoydas.simpleapp.ui.MainActivity
import com.ihridoydas.simpleapp.ui.demo.handling_events_with_sealed_classes.ui.CounterScreen
import com.ihridoydas.simpleapp.ui.screens.boardingScreen.OnBoardingScreen
import com.ihridoydas.simpleapp.ui.screens.homeScreen.HomeScreen
import com.ihridoydas.simpleapp.ui.screens.startScreen.StartShowCaseScreen
import com.ihridoydas.simpleapp.ui.screens.viewScreen.ViewScreen
import com.ihridoydas.simpleapp.util.responsiveUI.component.webView.WebBrowser
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainAnimationNavHost(
    navController: NavHostController,
    windowSizeClass: WindowSizeClass,
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    systemUiController: SystemUiController,
    startDestination: String = ScreenDestinations.StartShowCaseScreen.route,
    activity: MainActivity
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        screen(ScreenDestinations.StartShowCaseScreen.route) {
            StartShowCaseScreen(windowSizeClass = windowSizeClass, navController = navController)
        }
        screen(ScreenDestinations.HomeScreen.route) {
            HomeScreen(windowSizeClass = windowSizeClass, navController = navController)
        }
        screen(ScreenDestinations.ViewScreen.route) {

            ViewScreen(
                windowSizeClass = windowSizeClass,
                navController = navController,
                state = scaffoldState,
                coroutineScope = coroutineScope,
                activity = activity,
                onBackPress = {
                    navController.navigateTo(ScreenDestinations.BoardingScreen.route)
                },
                onClick = {}
            )
        }
        screen(ScreenDestinations.BoardingScreen.route) {
            OnBoardingScreen(
                windowSizeClass = windowSizeClass,
                navController = navController,
                onBackPress = {
                    navController.navigateTo(ScreenDestinations.StartShowCaseScreen.route)
                }
            )
        }
        screen(ScreenDestinations.WebViewScreen.route) {
            WebBrowser(
                windowSizeClass = windowSizeClass,
                navController = navController,
                onBackPress = {
                    navController.navigateTo(ScreenDestinations.ViewScreen.route)
                }
            )
        }
        screen(ScreenDestinations.BarCodeViewScreen.route) {
            BarCodeScreen(
                onBackPress = {
                    navController.navigateTo(ScreenDestinations.ViewScreen.route)
                }
            )
        }
        screen(ScreenDestinations.CameraViewScreen.route) {
            CameraScreen(onBackPress = {
                navController.navigateTo(ScreenDestinations.ViewScreen.route)
            })
        }

        screen(ScreenDestinations.CounterViewScreen.route) {
            CounterScreen(onBackPress = {
                navController.navigateTo(ScreenDestinations.ViewScreen.route)
            })
        }
        screen(ScreenDestinations.MultiLanguageScreen.route) {
            MultiLanguage(onBackPress = {
                navController.navigateTo(ScreenDestinations.ViewScreen.route)
            })
        }

        screen(ScreenDestinations.OCRScreen.route) {
            OCRScreen(onBackPress = {
                navController.navigateTo(ScreenDestinations.ViewScreen.route)
            })
        }
        screen(ScreenDestinations.NewTonTimerScreen.route) {
            NewtonsTimerScreen(onBackPress = {
                navController.navigateTo(ScreenDestinations.ViewScreen.route)
            })
        }
    }

    //Back Handler
    BackHandler {
        navController.popBackStack()
    }

}