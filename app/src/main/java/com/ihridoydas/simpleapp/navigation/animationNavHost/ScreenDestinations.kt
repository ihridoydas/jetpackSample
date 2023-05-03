package com.ihridoydas.simpleapp.navigation.animationNavHost

sealed class ScreenDestinations(val route: String) {
    object StartShowCaseScreen : ScreenDestinations("start_showCase_screen")
    object HomeScreen : ScreenDestinations("home_screen")
    object ViewScreen : ScreenDestinations("view_screen")
    object BoardingScreen : ScreenDestinations("boarding_screen")
    object WebViewScreen : ScreenDestinations("web_view")
}