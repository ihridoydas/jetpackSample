package com.ihridoydas.simpleapp.navigation

import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.ihridoydas.simpleapp.ui.screens.homeScreen.HomeScreen
import com.ihridoydas.simpleapp.ui.screens.mainScreen.MainScreen
import com.ihridoydas.simpleapp.ui.screens.profileScreen.ProfileScreen
import com.ihridoydas.simpleapp.util.responsiveUI.component.webView.WebBrowser

sealed interface MainNavScreenSpec {

    companion object {
        fun getAllMainNavScreenSpec() = listOf(
            MainScreenSpec,
            HomeScreenSpec,
            ProfileScreenSpec,
            WebViewSpec
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
 * Main画面ナビゲーション仕様
 */
object MainScreenSpec : MainNavScreenSpec {

    override val route = "main_screen"

    fun requestNavigationRoute() = route

    @RequiresApi(33)
    @Composable
    override fun Content(
        windowSizeClass: WindowSizeClass,
        navController: NavController,
        navBackStackEntry: NavBackStackEntry,
        systemUiController: SystemUiController
    ) {
        val state = rememberScaffoldState()
        val coroutineScope = rememberCoroutineScope()
        MainScreen(windowSizeClass = windowSizeClass, navController = navController, state = state, coroutineScope = coroutineScope)
    }
}


/**
 * ホーム画面ナビゲーション仕様
 */
object HomeScreenSpec : MainNavScreenSpec {

    override val route = "home_screen"

   // fun requestNavigationRoute() = route

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
        val context = LocalContext.current

        ProfileScreen(
            windowSizeClass = windowSizeClass,
            navController = navController,
            onBackPress = {
                Toast.makeText(context,"Hello",Toast.LENGTH_LONG).show()
                navController?.navigate(HomeScreenSpec.route) {
                    popUpTo(ProfileScreenSpec.route) {
                        inclusive = true
                    }
                }
            }
        )
    }
}

/**
 * WebView画面ナビゲーション仕様
 */
object WebViewSpec : MainNavScreenSpec {

    override val route = "web_view"

    fun requestNavigationRoute() = route

    @RequiresApi(33)
    @Composable
    override fun Content(
        windowSizeClass: WindowSizeClass,
        navController: NavController,
        navBackStackEntry: NavBackStackEntry,
        systemUiController: SystemUiController
    ) {
        val context = LocalContext.current

        WebBrowser(
            windowSizeClass = windowSizeClass,
            navController = navController,
        )
    }
}