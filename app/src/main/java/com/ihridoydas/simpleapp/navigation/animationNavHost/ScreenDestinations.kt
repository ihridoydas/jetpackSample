package com.ihridoydas.simpleapp.navigation.animationNavHost

sealed class ScreenDestinations(val route: String) {
    object StartShowCaseScreen : ScreenDestinations("start_showCase_screen")
    object HomeScreen : ScreenDestinations("home_screen")
    object ViewScreen : ScreenDestinations("view_screen")
    object BoardingScreen : ScreenDestinations("boarding_screen")
    object WebViewScreen : ScreenDestinations("web_view")
    object BarCodeViewScreen : ScreenDestinations("bar_code_view")
    object CameraViewScreen : ScreenDestinations("camera_view")
    object CounterViewScreen : ScreenDestinations("counter_view")
    object MultiLanguageScreen : ScreenDestinations("multi_language_view")
    object OCRScreen : ScreenDestinations("ocr_view")
    object NewTonTimerScreen : ScreenDestinations("newton_timer_view")

}