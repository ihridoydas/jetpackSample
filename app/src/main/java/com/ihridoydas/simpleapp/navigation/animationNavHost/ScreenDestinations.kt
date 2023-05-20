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

    //Animations
    object FloatingActionMenuScreen : ScreenDestinations("floating_action_menu_view")
    object AutoSlidingCarouselScreen : ScreenDestinations("auto_sliding_carousel_view")
    object DragAndDropScreen : ScreenDestinations("drag_and_drop_view")
    object DownloadableCircleScreen : ScreenDestinations("downloadable_circle_view")
    object DynamicIslandScreen : ScreenDestinations("dynamic_island_view")
    object ExplodingAnimationTransition : ScreenDestinations("exploding_animation_transition_view")
    object FlippableAnimationTransition : ScreenDestinations("flippable_animation_transition_view")
    object MapAnimation : ScreenDestinations("map_animation_view")

    //Others
    object TabLayoutScreen : ScreenDestinations("tab_layout_view")
    object PickImageScreen : ScreenDestinations("pick_Image_view")


}