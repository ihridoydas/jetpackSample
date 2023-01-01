package com.ihridoydas.simpleapp.navigation

import androidx.annotation.RequiresApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.ihridoydas.simpleapp.ui.screens.homeScreen.HomeScreen
import com.ihridoydas.simpleapp.ui.screens.profileScreen.ProfileScreen

sealed interface MainNavScreenSpec {

    companion object {
        fun getAllMainNavScreenSpec() = listOf(
            HomeScreenSpec,
            ProfileScreenSpec,
        )
    }

    val route: String

    val arguments: List<NamedNavArgument> get() = emptyList()

    @Composable
    fun Content(
        windowSizeClass: WindowSizeClass,
        navController: NavController,
        navBackStackEntry: NavBackStackEntry,
        systemUiController: SystemUiController
    )

}


/**
 * ホーム画面ナビゲーション仕様
 */
object HomeScreenSpec : MainNavScreenSpec {

    override val route = "home_screen"

    fun requestNavigationRoute() = route

    @RequiresApi(33)
    @Composable
    override fun Content(
        windowSizeClass: WindowSizeClass,
        navController: NavController,
        navBackStackEntry: NavBackStackEntry,
        systemUiController: SystemUiController
    ) {

        HomeScreen(windowSizeClass = windowSizeClass, navController = navController)
    }
}

/**
 * Profile画面ナビゲーション仕様
 */
object ProfileScreenSpec : MainNavScreenSpec {

    override val route = "profile_screen"

    fun requestNavigationRoute() = route

    @RequiresApi(33)
    @Composable
    override fun Content(
        windowSizeClass: WindowSizeClass,
        navController: NavController,
        navBackStackEntry: NavBackStackEntry,
        systemUiController: SystemUiController
    ) {

        ProfileScreen(windowSizeClass = windowSizeClass, navController = navController)
    }
}