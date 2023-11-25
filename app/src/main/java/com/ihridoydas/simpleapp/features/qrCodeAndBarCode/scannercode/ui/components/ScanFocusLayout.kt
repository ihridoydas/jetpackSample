package com.ihridoydas.simpleapp.features.qrCodeAndBarCode.scannercode.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import com.ihridoydas.simpleapp.util.responsiveUI.WindowType
import com.ihridoydas.simpleapp.util.responsiveUI.dpToSp
import com.ihridoydas.simpleapp.util.responsiveUI.rememberWindowSize

@Composable
fun CameraPreviewLayout(
    scanValue : Int?
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    // QRコード読み込み枠表示
    QrCodeLoadingFrame()

    // Window Size
    val window = rememberWindowSize()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
//            text = stringResource(id = R.string.scan_message),
            text = "Scan ${scanValue} QR or bar code" ,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding((screenWidth * 0.05f).dp)
                .width((screenWidth * 0.65f).dp),
            color = Color.White,
            fontSize = when (window.width) {
                WindowType.Normal -> dpToSp(15.dp)
                else -> dpToSp(12.dp)
            },
            textAlign = TextAlign.Center,
        )
    }
}


/**
 * QRコード読み込み枠
 */
@Composable
private fun QrCodeLoadingFrame() {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Black.copy(
                alpha = 0.6f
            ),
            shape = object : Shape {
                override fun createOutline(
                    size: Size,
                    layoutDirection: LayoutDirection,
                    density: Density
                ): Outline {
                    val left = size.width * 0.1f
                    val right = size.width - 1 - left
                    val top = (size.height - (size.width * 0.8f)) / 2
                    val bottom = top + (size.width * 0.8f)
                    val p1 = Path().apply {
                        addRect(Rect(0f, 0f, size.width - 1, size.height - 1))
                    }
                    val p2 = Path().apply {
                        addRect(Rect(left, top, right, bottom))
                    }
                    val p3 = Path()
                    p3.op(p1, p2, PathOperation.Difference)
                    return Outline.Generic(p3)
                }
            }
        ) {}
        Image(
            modifier = Modifier
                .size(width = (screenWidth * 0.8f).dp, height = (screenWidth * 0.8f).dp)
                .align(Alignment.Center),
            painter = painterResource(id = R.drawable.qr_mark),
            contentDescription = "qr mark"
        )
    }
}


@Preview
@Composable
fun QRScanPreview() {
    SimpleAppTheme {
       // CameraPreviewLayout()
    }

}