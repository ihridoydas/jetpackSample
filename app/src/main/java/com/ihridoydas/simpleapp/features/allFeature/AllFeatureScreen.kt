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
                inclusive = true
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
                inclusive = true
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
                inclusive = true
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
                inclusive = true
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
                inclusive = true
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
                inclusive = true
            }
        }
    }

}


