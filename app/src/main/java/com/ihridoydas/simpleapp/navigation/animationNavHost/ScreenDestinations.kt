package com.ihridoydas.simpleapp.navigation.animationNavHost

sealed class ScreenDestinations(val route: String) {

    //AR start
    object AugmentedImageARScreen : ScreenDestinations("augmented_image_ar_screen")
    object AugmentedModelView : ScreenDestinations("augmented_model_view_screen")
    object AugmentedPlacementView : ScreenDestinations("augmented_placement_view_screen")
    //AR End


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
    object MetaBallAnimation : ScreenDestinations("meta_ball_animation_view")
    object CreativeAnimation : ScreenDestinations("creative_animation_view")
    object PullToRefreshAnimation : ScreenDestinations("pull_to_refresh_animation_view")
    object ScratchCardEffectAnimation : ScreenDestinations("scratch_card_effect_animation_view")
    object ThemePickerAnimation : ScreenDestinations("theme_picker_animation_view")
    object TypeWriterAnimation : ScreenDestinations("type_writer_animation_view")
    object GalleryTransitionAnimation : ScreenDestinations("gallery_transition_animation_view")
    object MainSheepAnimation : ScreenDestinations("main_Sheep_animation_view")
    object SheepCanvasAnimation : ScreenDestinations("sheep_canvas_animation_view")


    //Others
    object TabLayoutScreen : ScreenDestinations("tab_layout_view")
    object PickImageScreen : ScreenDestinations("pick_Image_view")
    object SortingVisualizerScreen : ScreenDestinations("sorting_visual_view")
    object LocationTrackerScreen : ScreenDestinations("location_tracker_view")
    object TwoPaneScreen : ScreenDestinations("two_pane_view")
    object IlluminationInteractionsScreen : ScreenDestinations("illumination_interactions_view")


}