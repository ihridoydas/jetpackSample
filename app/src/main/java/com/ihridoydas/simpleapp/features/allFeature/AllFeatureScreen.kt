package com.ihridoydas.simpleapp.features.allFeature

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.ihridoydas.simpleapp.features.composePDF.pdfUseCase.PDFActivity
import com.ihridoydas.simpleapp.navigation.animationNavHost.ScreenDestinations

@Composable
fun AllFeatureScreen(navController: NavController) {

    val context = LocalContext.current

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
        ), name = "Drag and Drop"
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
        ), name = "Dynamic Circle(Require API 33)"
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
            Color(0xFF009688),
            Color(0xFFAD81B4)
        ), name = "Pick Image"
    ){
        navController?.navigate(ScreenDestinations.PickImageScreen.route) {
            popUpTo(ScreenDestinations.ViewScreen.route) {
                inclusive = false
            }
        }
    }

    //Others End

}


