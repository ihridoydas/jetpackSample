package com.ihridoydas.simpleapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavDeepLink
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.navigation.animation.composable as animationComposable

@OptIn(ExperimentalAnimationApi::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MainNavHost(
    windowSizeClass: WindowSizeClass,
    startDestination: String = HomeScreenSpec.route,
    navController: NavHostController,
    systemUiController: SystemUiController
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination,

    ) {

        MainNavScreenSpec.getAllMainNavScreenSpec().forEach { spec ->

            animationComposable(
                route = spec.route,
                arguments = spec.arguments,
                deepLinks =  listOf(NavDeepLink("deeplink://${spec.route}")),
                enterTransition = {
                    slideIntoContainer(AnimatedContentScope.SlideDirection.Left,animationSpec = tween(700))
                },
                exitTransition = {
                    slideOutOfContainer(AnimatedContentScope.SlideDirection.Left,animationSpec = tween(700))
                },
                popEnterTransition = {
                    slideIntoContainer(AnimatedContentScope.SlideDirection.Right,animationSpec = tween(700))
                },
                popExitTransition = {
                    slideOutOfContainer(AnimatedContentScope.SlideDirection.Right,animationSpec = tween(700))
                }
            ) {
                spec.Content(
                    windowSizeClass,
                    navController = navController,
                    navBackStackEntry = it,
                    systemUiController = systemUiController
                )
            }
        }
    }
}
