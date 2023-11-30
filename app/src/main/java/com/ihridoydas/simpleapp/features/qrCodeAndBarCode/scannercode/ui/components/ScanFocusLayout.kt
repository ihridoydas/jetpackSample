package com.ihridoydas.simpleapp.features.qrCodeAndBarCode.scannercode.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.features.qrCodeAndBarCode.scannercode.scanner.ScannerUiState
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import com.ihridoydas.simpleapp.util.responsiveUI.WindowType
import com.ihridoydas.simpleapp.util.responsiveUI.dpToSp
import com.ihridoydas.simpleapp.util.responsiveUI.rememberWindowSize

@Composable
fun CameraPreviewLayout(
    scanValue : Int?,
    scanState: Map<Int, String>,
    predefinedKeys: List<Int>
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp

    var isLoadingCompleted by remember { mutableStateOf(true) }
    val isLightModeActive by remember { mutableStateOf(true) }
    val isScanDone by remember { mutableStateOf(false) }

    // QRコード読み込み枠表示
    QrCodeLoadingFrame(
        isLoadingCompleted = isLoadingCompleted,
        isLightModeActive = isLightModeActive,
    )

    // Window Size
    val window = rememberWindowSize()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            LaunchedEffect(key1 = Unit){
                    isLoadingCompleted = !isLoadingCompleted
            }

            Box(modifier = Modifier
                .padding(bottom = 10.dp)
                .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ){

                //==================================
                var dotColor :Int = 1
                if(scanValue == null){}
                else if(scanValue == 1){}
                else if(scanValue == 5){
                    if(scanState.keys.indices.contains(1)  == ((predefinedKeys[1] == 1))){
                        println("Check: 1 QR Scanned")
                        dotColor = 1
                        // vmState.update { it.copy( scan = scan, showBottomSheet = false ) }
                    }else  if(scanState.keys.indices.contains(2)  == ((predefinedKeys[2] == 2))){
                        println("Check: 2 QR Scanned")
                        dotColor = 2
                        // vmState.update { it.copy( scan = scan, showBottomSheet = false ) }
                    }else  if(scanState.keys.indices.contains(3)  == ((predefinedKeys[3] == 3))){
                        println("Check: 3 QR Scanned")
                        dotColor = 3
                        // vmState.update { it.copy( scan = scan, showBottomSheet = false ) }
                    }else  if(scanState.keys.indices.contains(4)  == ((predefinedKeys[4] == 4))){
                        println("Check: 4 QR Scanned")
                        dotColor = 4
                        //  vmState.update { it.copy( scan = scan, showBottomSheet = false ) }
                    }else{
                        println("Waiting...")
                        dotColor = 5
                    }
                }else{
                    println("Nothing...")
                }
                //==================================


                val dotRadius = with(LocalDensity.current) { 4.dp.toPx() }
                val dotSpacing = with(LocalDensity.current) { 16.dp.toPx() }
                Canvas(modifier = Modifier
                    .align(Alignment.Center)
                    .padding((screenWidth * 0.05f).dp)
                    .size(width = (screenWidth * 0.8f).dp, height = (screenWidth * 0.1f).dp),
                ) {
                    val dotIsCenter = if(scanValue == 5){6}else{2}

                    val totalWidth = (dotIsCenter * dotSpacing) + (2 * dotRadius) // Total width of the dots

                    // Calculate the starting position to center the dots
                    val startX = (size.width - totalWidth) / 2f
                    // Draw five dots with spacing
                    if (scanValue != null) {
                        for (i in 1 ..  scanValue.toInt()) {
                            drawCircle(
                                color = if (i < dotColor) Color.Green else Color.White,
                                radius = dotRadius,
                                center = Offset(startX + (i * dotSpacing) + dotRadius, size.height / 2f)
                            )
                        }
                    }else{
                        dotColor = 0
                    }
                }
            }


            Text(
//            text = stringResource(id = R.string.scan_message),
                text = "Scan ${scanValue} QR or bar code" ,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .align(Alignment.CenterHorizontally),
                color = Color.White,
                fontSize = when (window.width) {
                    WindowType.Normal -> dpToSp(15.dp)
                    else -> dpToSp(12.dp)
                },
                textAlign = TextAlign.Center,
            )
        }


    }
}


/**
 * QRコード読み込み枠
 */
@Composable
private fun QrCodeLoadingFrame(
    isLoadingCompleted: Boolean,
    isLightModeActive: Boolean,
) {
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
        Box(
            modifier = Modifier
                .size(width = (screenWidth * 0.8f).dp, height = (screenWidth * 0.8f).dp)
                .align(Alignment.Center)
                .qrShimmerLoadingAnimation(
                    isLoadingCompleted = isLoadingCompleted,
                    isLightModeActive = isLightModeActive,
                )
                .qrCodeScanningLine()
        ){}
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .size(width = (screenWidth * 0.8f).dp, height = (screenWidth * 0.8f).dp),
            painter = painterResource(id = R.drawable.qr_mark),
            contentDescription = "qr mark"
        )

    }
}


@Preview
@Composable
fun QRScanPreview() {
    SimpleAppTheme {
        CameraPreviewLayout(1, scanState = emptyMap(), emptyList())
    }

}

@Composable
fun DrawDots() {
    val dotRadius = with(LocalDensity.current) { 4.dp.toPx() }
    val dotSpacing = with(LocalDensity.current) { 16.dp.toPx() }
    Canvas(modifier = Modifier.size(200.dp)) {
        val totalWidth = (4 * dotSpacing) + (2 * dotRadius) // Total width of the dots

        // Calculate the starting position to center the dots
        val startX = (size.width - totalWidth) / 2f

        // Draw five dots with spacing
        for (i in 0 until 5) {
            drawCircle(
                color = Color.Black,
                radius = dotRadius,
                center = Offset(startX + (i * dotSpacing) + dotRadius, size.height / 2f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDrawDots() {
    SimpleAppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            DrawDots()
        }
    }
}