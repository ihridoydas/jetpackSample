package com.ihridoydas.simpleapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavDeepLink
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.systemuicontroller.SystemUiController

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MainNavHost(
    windowSizeClass: WindowSizeClass,
    startDestination: String = HomeScreenSpec.route,
    navController: NavHostController,
    systemUiController: SystemUiController
) {
    NavHost(navController = navController, startDestination = startDestination) {
        MainNavScreenSpec.getAllMainNavScreenSpec().forEach { spec ->
            composable(
                route = spec.route,
                arguments = spec.arguments,
                deepLinks =  listOf(NavDeepLink("deeplink://${spec.route}"))
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
