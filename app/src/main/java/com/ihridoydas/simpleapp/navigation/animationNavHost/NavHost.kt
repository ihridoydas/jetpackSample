package com.ihridoydas.simpleapp.navigation.animationNavHost

import android.os.Build
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ScaffoldState
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.systemuicontroller.SystemUiController
import com.ihridoydas.simpleapp.ar.augmentedImage.AugmentedImageARScreen
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
import com.ihridoydas.simpleapp.util.responsiveUI.component.tabLayout.view.TabBarScreen
import com.ihridoydas.simpleapp.util.responsiveUI.component.tabLayout.view.TabLayoutActivityPreview
import com.ihridoydas.simpleapp.features.webView.WebBrowser
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.animatedFloatingActionMenu.FloatingActionMenu
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.autoSlidingCarousel.AutoSlidingCarousel
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.downloadableAnimationCircle.DownLoadableAnimation
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.dragAndDrop.DragAndDropUseCase
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.dynamicIsland.DynamicIsland
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.dynamicIsland.DynamicIslandApp
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.dynamicIsland.NotSupportScreen
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.explodingAnimationTransition.animation.demoUseCase.ExplodingAnimationTrasition
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.flippable.FlippableAnimationTransitions
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.mapAnimation.MapAnimationView
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.metaballAnimationsWithViewPager.MetaBallAnimationWithViewPager
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.othersAnimation.CreativeAnimations
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.pullToRefreshLoadingAnimation.PullRefreshAnimations
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.scratchCardEffect.ScratchCardScreen
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.themePickerAnimations.ThemePickerApp
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.typeWritter.TypeWriterApp
import com.ihridoydas.simpleapp.util.responsiveUI.component.pickImageFromMobileCamera.PickImageFromMobile
import kotlinx.coroutines.CoroutineScope

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class,
    ExperimentalComposeUiApi::class
)
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

        //AR Start
        screen(ScreenDestinations.AugmentedImageARScreen.route) {
            AugmentedImageARScreen(
                onBackPress = {
                    navController.navigateTo(ScreenDestinations.ViewScreen.route)
                }
            )
        }
        //AR End

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

        //Animations
        screen(ScreenDestinations.FloatingActionMenuScreen.route) {
            FloatingActionMenu(onBackPress = {
                navController.navigateTo(ScreenDestinations.ViewScreen.route)
            })
        }
        screen(ScreenDestinations.AutoSlidingCarouselScreen.route) {
            AutoSlidingCarousel(onBackPress = {
                navController.navigateTo(ScreenDestinations.ViewScreen.route)
            })
        }
        screen(ScreenDestinations.DragAndDropScreen.route) {
            DragAndDropUseCase(onBackPress = {
                navController.navigateTo(ScreenDestinations.ViewScreen.route)
            })
        }

        screen(ScreenDestinations.DownloadableCircleScreen.route) {
            DownLoadableAnimation(onBackPress = {
                navController.navigateTo(ScreenDestinations.ViewScreen.route)
            })
        }

        screen(ScreenDestinations.DynamicIslandScreen.route) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                DynamicIslandApp(onBackPress = {
                    navController.navigateTo(ScreenDestinations.ViewScreen.route)
                })
            }else{
                NotSupportScreen(onBackPress = {
                    navController.navigateTo(ScreenDestinations.ViewScreen.route)
                })
            }
        }

        screen(ScreenDestinations.ExplodingAnimationTransition.route) {
            ExplodingAnimationTrasition(onBackPress = {
                navController.navigateTo(ScreenDestinations.ViewScreen.route)
            })
        }

        screen(ScreenDestinations.FlippableAnimationTransition.route) {
            FlippableAnimationTransitions(onBackPress = {
                navController.navigateTo(ScreenDestinations.ViewScreen.route)
            })
        }
        screen(ScreenDestinations.MapAnimation.route) {
            MapAnimationView(onBackPress = {
                navController.navigateTo(ScreenDestinations.ViewScreen.route)
            })
        }
        screen(ScreenDestinations.MetaBallAnimation.route) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                MetaBallAnimationWithViewPager(onBackPress = {
                    navController.navigateTo(ScreenDestinations.ViewScreen.route)
                })
            }else{
                NotSupportScreen(onBackPress = {
                    navController.navigateTo(ScreenDestinations.ViewScreen.route)
                })
            }
        }

        screen(ScreenDestinations.CreativeAnimation.route) {
            CreativeAnimations(onBackPress = {
                navController.navigateTo(ScreenDestinations.ViewScreen.route)
            })
        }

        screen(ScreenDestinations.PullToRefreshAnimation.route) {
            PullRefreshAnimations(onBackPress = {
                navController.navigateTo(ScreenDestinations.ViewScreen.route)
            })
        }

        screen(ScreenDestinations.ScratchCardEffectAnimation.route) {
            ScratchCardScreen(onBackPress = {
                navController.navigateTo(ScreenDestinations.ViewScreen.route)
            })
        }

        screen(ScreenDestinations.ThemePickerAnimation.route) {
            ThemePickerApp(onBackPress = {
                navController.navigateTo(ScreenDestinations.ViewScreen.route)
            })
        }
        screen(ScreenDestinations.TypeWriterAnimation.route) {
            TypeWriterApp(onBackPress = {
                navController.navigateTo(ScreenDestinations.ViewScreen.route)
            })
        }

        //Others
        screen(ScreenDestinations.TabLayoutScreen.route) {
            TabBarScreen(onBackPress = {
                navController.navigateTo(ScreenDestinations.ViewScreen.route)
            })
        }
        screen(ScreenDestinations.PickImageScreen.route) {
            PickImageFromMobile(onBackPress = {
                navController.navigateTo(ScreenDestinations.ViewScreen.route)
            })
        }


    }

    //Back Handler
    BackHandler {
        navController.popBackStack()
    }

}