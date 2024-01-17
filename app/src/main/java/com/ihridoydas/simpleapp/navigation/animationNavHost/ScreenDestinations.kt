package com.ihridoydas.simpleapp.navigation.animationNavHost

sealed class ScreenDestinations(val route: String) {

    //AR start
    data object AugmentedImageARScreen : ScreenDestinations("augmented_image_ar_screen")
    data object ARMenuScreen : ScreenDestinations("ar_menu")
    data object AugmentedModelView : ScreenDestinations("augmented_model_view_screen")
    data object AugmentedPlacementView : ScreenDestinations("augmented_placement_view_screen")
    data object ArEcommerceHome : ScreenDestinations("Ar_ecommerce_home")
    data object ArEcommerceProductId : ScreenDestinations("virtual_try_on/{productId}")
    //AR End


    data object StartShowCaseScreen : ScreenDestinations("start_showCase_screen")
    data object HomeScreen : ScreenDestinations("home_screen")
    data object ViewScreen : ScreenDestinations("view_screen")
    data object BoardingScreen : ScreenDestinations("boarding_screen")
    data object WebViewScreen : ScreenDestinations("web_view")
    data object BarCodeViewScreen : ScreenDestinations("bar_code_view")
    data object CameraViewScreen : ScreenDestinations("camera_view")
    data object CounterViewScreen : ScreenDestinations("counter_view")
    data object MultiLanguageScreen : ScreenDestinations("multi_language_view")
    data object OCRScreen : ScreenDestinations("ocr_view")
    data object NewTonTimerScreen : ScreenDestinations("newton_timer_view")

    //Animations
    data object FloatingActionMenuScreen : ScreenDestinations("floating_action_menu_view")
    data object AutoSlidingCarouselScreen : ScreenDestinations("auto_sliding_carousel_view")
    data object DragAndDropScreen : ScreenDestinations("drag_and_drop_view")
    data object DownloadableCircleScreen : ScreenDestinations("downloadable_circle_view")
    data object DynamicIslandScreen : ScreenDestinations("dynamic_island_view")
    data object ExplodingAnimationTransition : ScreenDestinations("exploding_animation_transition_view")
    data object FlippableAnimationTransition : ScreenDestinations("flippable_animation_transition_view")
    data object MapAnimation : ScreenDestinations("map_animation_view")
    data object MetaBallAnimation : ScreenDestinations("meta_ball_animation_view")
    data object CreativeAnimation : ScreenDestinations("creative_animation_view")
    data object PullToRefreshAnimation : ScreenDestinations("pull_to_refresh_animation_view")
    data object ScratchCardEffectAnimation : ScreenDestinations("scratch_card_effect_animation_view")
    data object ThemePickerAnimation : ScreenDestinations("theme_picker_animation_view")
    data object TypeWriterAnimation : ScreenDestinations("type_writer_animation_view")
    data object GalleryTransitionAnimation : ScreenDestinations("gallery_transition_animation_view")
    data object MainSheepAnimation : ScreenDestinations("main_Sheep_animation_view")
    data object SheepCanvasAnimation : ScreenDestinations("sheep_canvas_animation_view")


    //Others
    object ComposeRiveAnimation : ScreenDestinations("compose_rive_animation")
    data object TabLayoutScreen : ScreenDestinations("tab_layout_view")
    data object PickImageScreen : ScreenDestinations("pick_Image_view")
    data object SortingVisualizerScreen : ScreenDestinations("sorting_visual_view")
    data object LocationTrackerScreen : ScreenDestinations("location_tracker_view")
    data object LayoutDevicesScreen : ScreenDestinations("two_pane_view")
    data object IlluminationInteractionsScreen : ScreenDestinations("illumination_interactions_view")
    data object BottomSheetsAndPager : ScreenDestinations("bottom_sheets_and_pager")
    data object ComposeImpressionTracker : ScreenDestinations("compose_impression_tracker")
    data object RichEditor : ScreenDestinations("rich_editor_compose")
    data object StepperComposable : ScreenDestinations("stepper_compose")
    data object QuizApp : ScreenDestinations("quiz_app")
    data object Koreography : ScreenDestinations("koreography")
    data object ScreenShotCapture : ScreenDestinations("ScreenShot_capture")
    data object ScrollBars : ScreenDestinations("scroll_bars")
    data object Widget : ScreenDestinations("widget_glance")
    data object MovingAnimationText : ScreenDestinations("moving_animation_text")


}