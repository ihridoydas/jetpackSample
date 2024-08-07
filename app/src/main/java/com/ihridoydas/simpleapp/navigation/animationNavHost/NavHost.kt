package com.ihridoydas.simpleapp.navigation.animationNavHost

import android.content.Context
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ScaffoldState
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.systemuicontroller.SystemUiController
import com.ihridoydas.simpleapp.ar.arEcommerce.productdescription.presentation.ProductDescriptionScreen
import com.ihridoydas.simpleapp.ar.arEcommerce.productdescription.presentation.ProductDescriptionViewModel
import com.ihridoydas.simpleapp.ar.arEcommerce.virtualtryon.presentation.VirtualTryOnScreen
import com.ihridoydas.simpleapp.ar.arEcommerce.virtualtryon.presentation.VirtualTryOnViewModel
import com.ihridoydas.simpleapp.ar.augmentedImage.AugmentedImageARScreen
import com.ihridoydas.simpleapp.ar.augmentedModelView.ARModelViewer
import com.ihridoydas.simpleapp.ar.augmentedPlacement.PlacementView
import com.ihridoydas.simpleapp.ar.arMenu.ARMenuUseCase
import com.ihridoydas.simpleapp.features.bottomSheets.pager.MainPagerWithBottomSheets
import com.ihridoydas.simpleapp.features.cameraScreen.CameraScreen
import com.ihridoydas.simpleapp.features.composeImpressionTracker.demo.ComposeImpressionScreen
import com.ihridoydas.simpleapp.features.composibleSheep.MainSheepAnimation
import com.ihridoydas.simpleapp.features.composibleSheep.MainSheepCanvas
import com.ihridoydas.simpleapp.features.koreography.KoreoraphyScreen
import com.ihridoydas.simpleapp.features.layoutScaffold.LayoutScaffoldScreen
import com.ihridoydas.simpleapp.features.locationTracker.LocationTracker
import com.ihridoydas.simpleapp.features.movingLetter.usecase.MovingTextScreen
import com.ihridoydas.simpleapp.features.multiLanguage.MultiLanguage
import com.ihridoydas.simpleapp.features.newTonsTimer.timer.NewtonsTimerScreen
import com.ihridoydas.simpleapp.features.ocr.OCRScreen
import com.ihridoydas.simpleapp.features.riveAnimation.RiveAnimationCompose
import com.ihridoydas.simpleapp.features.qrCodeAndBarCode.useCases.ScannerUIScreen
import com.ihridoydas.simpleapp.features.quiz.QuizScreen
import com.ihridoydas.simpleapp.features.richEditor.RichEditorComposableScreen
import com.ihridoydas.simpleapp.features.screenShotCapture.ScreenCaptureScreen
import com.ihridoydas.simpleapp.features.sortingVisualizer.SortingVisualizer
import com.ihridoydas.simpleapp.features.stepperLibrary.StepperScreen
import com.ihridoydas.simpleapp.features.webView.WebBrowser
import com.ihridoydas.simpleapp.ui.MainActivity
import com.ihridoydas.simpleapp.ui.demo.handling_events_with_sealed_classes.ui.CounterScreen
import com.ihridoydas.simpleapp.ui.screens.boardingScreen.OnBoardingScreen
import com.ihridoydas.simpleapp.ui.screens.homeScreen.HomeScreen
import com.ihridoydas.simpleapp.ui.screens.startScreen.StartShowCaseScreen
import com.ihridoydas.simpleapp.ui.screens.viewScreen.ViewScreen
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.animatedFloatingActionMenu.FloatingActionMenu
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.autoSlidingCarousel.AutoSlidingCarousel
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.downloadableAnimationCircle.DownLoadableAnimation
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.dragAndDrop.DragAndDropUseCase
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
import com.ihridoydas.simpleapp.util.responsiveUI.component.galleryTransitionHorizontalPager.GalleryTransition
import com.ihridoydas.simpleapp.util.responsiveUI.component.illuminatingInteractions.IlluminatingInteractions
import com.ihridoydas.simpleapp.util.responsiveUI.component.lazyColumnWithScrollbar.ScrollBarScreen
import com.ihridoydas.simpleapp.util.responsiveUI.component.pickImageFromMobileCamera.PickImageFromMobile
import com.ihridoydas.simpleapp.util.responsiveUI.component.tabLayout.TabBarsScreen
import com.ihridoydas.simpleapp.widget.WidgetPager
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.node.VideoNode
import kotlinx.coroutines.CoroutineScope

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(
    ExperimentalAnimationApi::class, ExperimentalFoundationApi::class,
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
    activity: MainActivity,
    context: Context,
    videoNode: VideoNode,
    lifecycleScope: LifecycleCoroutineScope,
    sceneView: ArSceneView,
    productId: Int,
    productViewModel: ProductDescriptionViewModel,
    virtualTryOnViewModel: VirtualTryOnViewModel
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
        screen(ScreenDestinations.ARMenuScreen.route) {
            ARMenuUseCase(
                onBackPress = {
                    navController.navigateTo(ScreenDestinations.ViewScreen.route)
                },
                activity = activity,
                navController = navController
            )
        }
        screen(ScreenDestinations.AugmentedImageARScreen.route) {
            AugmentedImageARScreen(
                videoNode,
                lifecycleScope,
                sceneView,
                onBackPress = {
                    navController.navigateTo(ScreenDestinations.ViewScreen.route)
                }
            )
        }
        screen(ScreenDestinations.AugmentedModelView.route) {
            ARModelViewer(
                onBackPress = {
                    navController.navigateTo(ScreenDestinations.ViewScreen.route)
                },
                activity = activity,
                navController = navController
            )
        }
        screen(ScreenDestinations.AugmentedPlacementView.route) {
            PlacementView(
                onBackPress = {
                    navController.navigateTo(ScreenDestinations.ViewScreen.route)
                },
                activity = activity,
                navController = navController
            )
        }
        screen(ScreenDestinations.ArEcommerceHome.route) {
            ProductDescriptionScreen(
                productId = productId,
                navController = navController,
                productViewModel = productViewModel,
                onBackPress = {
                    navController.navigateTo(ScreenDestinations.ViewScreen.route)
                }
            )
        }
        screen(
            ScreenDestinations.ArEcommerceProductId.route,
            arguments = listOf(navArgument("productId") {
                type = NavType.StringType
            })
        ) {
            val productIdArg = it.arguments?.getString("productId")
            VirtualTryOnScreen(productIdArg?.toInt() ?: 0, virtualTryOnViewModel,
                onBackPress = {
                    navController.navigateTo(ScreenDestinations.ArEcommerceHome.route)
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
            ScannerUIScreen(
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                DynamicIslandApp(onBackPress = {
                    navController.navigateTo(ScreenDestinations.ViewScreen.route)
                })
            } else {
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                MetaBallAnimationWithViewPager(onBackPress = {
                    navController.navigateTo(ScreenDestinations.ViewScreen.route)
                })
            } else {
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

        screen(ScreenDestinations.GalleryTransitionAnimation.route) {
            GalleryTransition(onBackPress = {
                navController.navigateTo(ScreenDestinations.ViewScreen.route)
            })
        }
        screen(ScreenDestinations.MainSheepAnimation.route) {
            MainSheepAnimation(onBackPress = {
                navController.navigateTo(ScreenDestinations.ViewScreen.route)
            })
        }

        screen(ScreenDestinations.SheepCanvasAnimation.route) {
            MainSheepCanvas(onBackPress = {
                navController.navigateTo(ScreenDestinations.ViewScreen.route)
            })
        }

        //Others
        screen(ScreenDestinations.TabLayoutScreen.route) {
            TabBarsScreen(onBackPress = {
                navController.navigateTo(ScreenDestinations.ViewScreen.route)
            })
        }
        screen(ScreenDestinations.PickImageScreen.route) {
            PickImageFromMobile(onBackPress = {
                navController.navigateTo(ScreenDestinations.ViewScreen.route)
            })
        }
        screen(ScreenDestinations.SortingVisualizerScreen.route) {
            SortingVisualizer(onBackPress = {
                navController.navigateTo(ScreenDestinations.ViewScreen.route)
            })
        }

        screen(ScreenDestinations.LocationTrackerScreen.route) {
            LocationTracker(
                onBackPress = {
                    navController.navigateTo(ScreenDestinations.ViewScreen.route)
                },
                activity = activity,
                context = context
            )
        }

        screen(ScreenDestinations.LayoutDevicesScreen.route) {
            LayoutScaffoldScreen(
                onBackPress = {
                    navController.navigateTo(ScreenDestinations.ViewScreen.route)
                },
                activity = activity
            )
        }

        screen(ScreenDestinations.IlluminationInteractionsScreen.route) {
            IlluminatingInteractions(onBackPress = {
                navController.navigateTo(ScreenDestinations.ViewScreen.route)
            })
        }
        screen(ScreenDestinations.BottomSheetsAndPager.route) {
            MainPagerWithBottomSheets(onBackPress = {
                navController.navigateTo(ScreenDestinations.ViewScreen.route)
            })
        }
        screen(ScreenDestinations.ComposeImpressionTracker.route) {
            ComposeImpressionScreen(
                onBackPress = {
                    navController.navigateTo(ScreenDestinations.ViewScreen.route)
                },
                activity,
                navController
            )
        }
        screen(ScreenDestinations.ComposeRiveAnimation.route) {
            RiveAnimationCompose(onBackPress = {
                navController.navigateTo(ScreenDestinations.ViewScreen.route)
            })
        }
        screen(ScreenDestinations.RichEditor.route) {
            RichEditorComposableScreen(
                onBackPress = {
                    navController.navigateTo(ScreenDestinations.ViewScreen.route)
                },
                activity,
                navController
            )
        }
        screen(ScreenDestinations.StepperComposable.route) {
            StepperScreen(
                onBackPress = {
                    navController.navigateTo(ScreenDestinations.ViewScreen.route)
                }
            )
        }
        screen(ScreenDestinations.QuizApp.route) {
            QuizScreen(
                onBackPress = {
                    navController.navigateTo(ScreenDestinations.ViewScreen.route)
                }
            )
        }
        screen(ScreenDestinations.Koreography.route) {
            KoreoraphyScreen(
                onBackPress = {
                    navController.navigateTo(ScreenDestinations.ViewScreen.route)
                }
            )
        }
        screen(ScreenDestinations.ScreenShotCapture.route) {
            ScreenCaptureScreen(
                onBackPress = {
                    navController.navigateTo(ScreenDestinations.ViewScreen.route)
                },
                context
            )
        }

        screen(ScreenDestinations.ScrollBars.route) {
            ScrollBarScreen(
                onBackPress = {
                    navController.navigateTo(ScreenDestinations.ViewScreen.route)
                }
            )
        }
        screen(ScreenDestinations.Widget.route) {
                WidgetPager(
                    onBackPress = {
                        navController.navigateTo(ScreenDestinations.ViewScreen.route)
                    }
                )
            }
        screen(ScreenDestinations.MovingAnimationText.route) {
            MovingTextScreen(
                onBackPress = {
                    navController.navigateTo(ScreenDestinations.ViewScreen.route)
                }
            )
        }
    }



    BackHandler {
        navController.popBackStack()
    }

}