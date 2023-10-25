package com.ihridoydas.simpleapp.features.allFeature

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ihridoydas.simpleapp.ar.viewXMLVideoModel.VideoARActivity
import com.ihridoydas.simpleapp.features.composePDF.pdfUseCase.PDFActivity
import com.ihridoydas.simpleapp.navigation.animationNavHost.ScreenDestinations

@Composable
fun AllFeatureScreen(navController: NavController) {

    Column(modifier = Modifier.fillMaxSize()){
        val context = LocalContext.current

        //AR Start
        MyCardTitle(
            color = listOf(
                Color(0xFF2196F3),
                Color(0xFF662DCC)
            ), name = "Augmented Reality",
            width = 150.dp
        )
        //Augmented Video
        MyCard(
            color = listOf(
                Color(0xFF9D17A3),
                Color(0xFF34B839)
            ), name = "AR Menu"
        ){
            navController?.navigate(ScreenDestinations.ARMenuScreen.route) {
                popUpTo(ScreenDestinations.HomeScreen.route) {
                    inclusive = true
                }
            }
        }
        MyCard(
            color = listOf(
                Color(0xFF673AB7),
                Color(0xFFCFC0B2)
            ), name = "Augmented Video"
        ){
            context.startActivity(Intent(context, VideoARActivity::class.java))
        }
        //Augmented Image
        MyCard(
            color = listOf(
                Color(0xFF4CAF50),
                Color(0xFF03A9F4)
            ), name = "Augmented Image"
        ){
            navController?.navigate(ScreenDestinations.AugmentedImageARScreen.route) {
                popUpTo(ScreenDestinations.HomeScreen.route) {
                    inclusive = true
                }
            }
        }
        //Augmented Placement
        MyCard(
            color = listOf(
                Color(0xFF1F746C),
                Color(0xFFCFC0B2)
            ), name = "Augmented Placement"
        ){
            navController?.navigate(ScreenDestinations.AugmentedPlacementView.route) {
                popUpTo(ScreenDestinations.HomeScreen.route) {
                    inclusive = true
                }
            }
        }

        //Augmented Model View
        MyCard(
            color = listOf(
                Color(0xFF2B4745),
                Color(0xFF3F51B5)
            ), name = "Augmented Model View"
        ){
            navController?.navigate(ScreenDestinations.AugmentedModelView.route) {
                popUpTo(ScreenDestinations.HomeScreen.route) {
                    inclusive = true
                }
            }
        }

        //Augmented ECommerce
        MyCard(
            color = listOf(
                Color(0xFFB82C00),
                Color(0xFF00BD08)
            ), name = "Augmented ECommerce"
        ){
            navController?.navigate(ScreenDestinations.ArEcommerceHome.route) {
                popUpTo(ScreenDestinations.HomeScreen.route) {
                    inclusive = true
                }
            }
        }

        //AR End

        //Feature Start
        MyCardTitle(
            color = listOf(
                Color(0xFF4CAF50),
                Color(0xFF009688)
            ), name = "Features"
        )
        //WebView
        MyCard(
            color = listOf(
                Color(0xFFFF5858),
                Color(0xFFFFC793)
            ), name = "Web View"
        ){
            navController?.navigate(ScreenDestinations.WebViewScreen.route) {
                popUpTo(ScreenDestinations.HomeScreen.route) {
                    inclusive = true
                }
            }
        }

        //PdfView
        MyCard(
            color = listOf(
                Color(0xFF3BFF95),
                Color(0xFF00A3FF)
            ), name = "PDF View"
        ){
            context.startActivity(Intent(context, PDFActivity::class.java))
        }

        //Bar Code View
        MyCard(
            color = listOf(
                Color(0xFF5A76D3),
                Color(0xFFFFC793)
            ), name = "Bar Code View"
        ){
            navController?.navigate(ScreenDestinations.BarCodeViewScreen.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }

        //Camera View
        MyCard(
            color = listOf(
                Color(0xFF00BCD4),
                Color(0xFFE2D3C4)
            ), name = "Camera View"
        ){
            navController?.navigate(ScreenDestinations.CameraViewScreen.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }

        //Counter View
        MyCard(
            color = listOf(
                Color(0xFF9EFF58),
                Color(0xFF5ED8D8)
            ), name = "Counter View"
        ){
            navController?.navigate(ScreenDestinations.CounterViewScreen.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }

        //Multi language View
        MyCard(
            color = listOf(
                Color(0xFF668153),
                Color(0xFFA5A17D)
            ), name = "Multi Language View"
        ){
            navController?.navigate(ScreenDestinations.MultiLanguageScreen.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }

        //OCR View
        MyCard(
            color = listOf(
                Color(0xFF3F51B5),
                Color(0xFFC8D3C8)
            ), name = "OCR"
        ){
            navController?.navigate(ScreenDestinations.OCRScreen.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }

        //NewTon Timer View
        MyCard(
            color = listOf(
                Color(0xFF009688),
                Color(0xFFCDDC39)
            ), name = "NewTon Timer"
        ){
            navController?.navigate(ScreenDestinations.NewTonTimerScreen.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }

        //Feature End

        //Animations Start
        MyCardTitle(
            color = listOf(
                Color(0xFF009688),
                Color(0xFF9C27B0)
            ), name = "Animations"
        )

        //Floating Action Menu
        MyCard(
            color = listOf(
                Color(0xFF0A4B80),
                Color(0xFF545C04)
            ), name = "Floating Action Menu"
        ){
            navController?.navigate(ScreenDestinations.FloatingActionMenuScreen.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }

        //Auto Sliding Carousel
        MyCard(
            color = listOf(
                Color(0xFF6BAFE7),
                Color(0xFF8BC34A)
            ), name = "Auto Sliding Carousel "
        ){
            navController?.navigate(ScreenDestinations.AutoSlidingCarouselScreen.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }

        //Drag and Drop
        MyCard(
            color = listOf(
                Color(0xFF8BC34A),
                Color(0xFFC54E46)
            ), name = "Drag and Drop and Chip"
        ){
            navController?.navigate(ScreenDestinations.DragAndDropScreen.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }

        //DownloadAble Circle
        MyCard(
            color = listOf(
                Color(0xFF009688),
                Color(0xFF3C2368)
            ), name = "Downloadable Circle"
        ){
            navController?.navigate(ScreenDestinations.DownloadableCircleScreen.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }

        //Dynamic Circle
        MyCard(
            color = listOf(
                Color(0xFF4D5E3A),
                Color(0xFFAA827F)
            ), name = "Dynamic Island (Require API 33)"
        ){
            navController?.navigate(ScreenDestinations.DynamicIslandScreen.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }

        //Exploding Animation Transitions
        MyCard(
            color = listOf(
                Color(0xFF4DA39B),
                Color(0xFF7B6B7E)
            ), name = "Exploding Animation with Pager"
        ){
            navController?.navigate(ScreenDestinations.ExplodingAnimationTransition.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }

        //Exploding Animation Transitions
        MyCard(
            color = listOf(
                Color(0xFF6D231E),
                Color(0xFF98C000)
            ), name = "Flippable Animation"
        ){
            navController?.navigate(ScreenDestinations.FlippableAnimationTransition.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }

        //Map App
        MyCard(
            color = listOf(
                Color(0xFF4CAF50),
                Color(0xFF1B1B19)
            ), name = "Map App"
        ){
            navController?.navigate(ScreenDestinations.MapAnimation.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }

        //Metaball Animation With ViewPager
        MyCard(
            color = listOf(
                Color(0xFFB3C999),
                Color(0xFFE91E63)
            ), name = "Metaball Animation With ViewPager"
        ){
            navController?.navigate(ScreenDestinations.MetaBallAnimation.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }

        //Creative Animations
        MyCard(
            color = listOf(
                Color(0xFF761486),
                Color(0xFFCECE73)
            ), name = "Creative Animations"
        ){
            navController?.navigate(ScreenDestinations.CreativeAnimation.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }
        //Pull To Refresh Animations
        MyCard(
            color = listOf(
                Color(0xFF4CAF50),
                Color(0xFFA0A05D)
            ), name = "Pull To Refresh(custom and Fancy)"
        ){
            navController?.navigate(ScreenDestinations.PullToRefreshAnimation.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }

        //Scratch card Effect Animations
        MyCard(
            color = listOf(
                Color(0xFF9486AC),
                Color(0xFF878CA8)
            ), name = "Scratch card Effect Animations"
        ){
            navController?.navigate(ScreenDestinations.ScratchCardEffectAnimation.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }

        //Theme Picker Animations
        MyCard(
            color = listOf(
                Color(0xFFE91E63),
                Color(0xFF8BC34A)
            ), name = "Theme Picker Animations"
        ){
            navController?.navigate(ScreenDestinations.ThemePickerAnimation.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }

        //Type Writer Animations
        MyCard(
            color = listOf(
                Color(0xFF673AB7),
                Color(0xFF273813)
            ), name = "Type Writer Animation"
        ){
            navController?.navigate(ScreenDestinations.TypeWriterAnimation.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }
        //Gallery Transition Animation
        MyCard(
            color = listOf(
                Color(0xFF65606F),
                Color(0xFF32638A)
            ), name = "Gallery Transition"
        ){
            navController?.navigate(ScreenDestinations.GalleryTransitionAnimation.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }

        //Simple Sheep animation
        MyCard(
            color = listOf(
                Color(0xFF17A598),
                Color(0xFF32638A)
            ), name = "Sheep Animations"
        ){
            navController?.navigate(ScreenDestinations.MainSheepAnimation.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }
        // Sheep Canvas
        MyCard(
            color = listOf(
                Color(0xFF459648),
                Color(0xFFEBEECD)
            ), name = "Sheep Canvas"
        ){
            navController?.navigate(ScreenDestinations.SheepCanvasAnimation.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }

        //Animations End

        //Others Start
        MyCardTitle(
            color = listOf(
                Color(0xFF2196F3),
                Color(0xFF673AB7)
            ), name = "Others"
        )

        //TabLayout View
        MyCard(
            color = listOf(
                Color(0xFF214642),
                Color(0x74646B20)
            ), name = "TabLayout with Cascade Menu"
        ){
            navController?.navigate(ScreenDestinations.TabLayoutScreen.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }

        //Pick Image View
        MyCard(
            color = listOf(
                Color(0xFFBFC4C3),
                Color(0xFF63EE69)
            ), name = "Pick Image and Country Picker"
        ){
            navController?.navigate(ScreenDestinations.PickImageScreen.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }

        MyCard(
            color = listOf(
                Color(0xFF3A9280),
                Color(0xFF773B4F)
            ), name = "Sorting Algorithm"
        ){
            navController?.navigate(ScreenDestinations.SortingVisualizerScreen.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }

        MyCard(
            color = listOf(
                Color(0xFFE91E63),
                Color(0xFF7C6B71)
            ), name = "Location Tracker"
        ){
            navController?.navigate(ScreenDestinations.LocationTrackerScreen.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }
        MyCard(
            color = listOf(
                Color(0xFF643747),
                Color(0xFF948E2C)
            ), name = "Two Pane layout"
        ){
            navController?.navigate(ScreenDestinations.TwoPaneScreen.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }

        MyCard(
            color = listOf(
                Color(0xFF275E29),
                Color(0xFF9C27B0)
            ), name = "Illumination Interactions"
        ){
            navController?.navigate(ScreenDestinations.IlluminationInteractionsScreen.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }

        MyCard(
            color = listOf(
                Color(0xFFD1B9C1),
                Color(0xFF2D1C30)
            ), name = "Bottom Sheets and Pager"
        ){
            navController?.navigate(ScreenDestinations.BottomSheetsAndPager.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }

        MyCard(
            color = listOf(
                Color(0xFF132069),
                Color(0xFFE5D5E7)
            ), name = "Compose Impression Tracker"
        ){
            navController?.navigate(ScreenDestinations.ComposeImpressionTracker.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }
        MyCard(
            color = listOf(
                Color(0xFF3BCE3F),
                Color(0xFF221D0D)
            ), name = "Rich Editor compose"
        ){
            navController?.navigate(ScreenDestinations.RichEditor.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }
        MyCard(
            color = listOf(
                Color(0xFF2196F3),
                Color(0xFFAD8503)
            ), name = "Stepper Composable"
        ){
            navController?.navigate(ScreenDestinations.StepperComposable.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }

        MyCard(
            color = listOf(
                Color(0xFF8BC34A),
                Color(0xFFE91E63)
            ), name = "Quiz App"
        ){
            navController?.navigate(ScreenDestinations.QuizApp.route) {
                popUpTo(ScreenDestinations.ViewScreen.route) {
                    inclusive = false
                }
            }
        }

        //Others End

    }
}


