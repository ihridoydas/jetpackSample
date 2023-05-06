package com.ihridoydas.simpleapp.util.responsiveUI.component.canvas.practice

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ihridoydas.simpleapp.R


@Composable
fun DrawCanvas() {
    val context = LocalContext.current

    val textMeasure = rememberTextMeasurer()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(300.dp)
                .background(Color.White)
                .align(Alignment.CenterHorizontally)
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                val canvasWidth = size.width
                val canvasHeight = size.height

                //Draw Rect
                drawRectInCanvas(this, canvasWidth, canvasHeight)
                //Draw Circle
                drawCircleInCanvas(this, canvasWidth, canvasHeight)

                //Draw CrossLines
                drawCrossLinesInCanvas(this, canvasWidth, canvasHeight)

                //Draw Bitmap
                drawBitmapInCanvas(this, context, R.drawable.icon, canvasWidth, canvasHeight)

                //DrawText
                drawTextInCanvas(this, canvasWidth, canvasHeight, textMeasure)
            }
        }

    }

}


fun drawRectInCanvas(drawScope: DrawScope, width: Float, height: Float) {
    drawScope.drawRect(
        color = Color.Cyan,
        topLeft = Offset(width * 0.25f, height * 0.25f),
        size = Size(width * 0.5f, height * 0.5f)
    )
}

fun drawCircleInCanvas(drawScope: DrawScope, width: Float, height: Float) {

    drawScope.drawCircle(
        color = Color.Yellow,
        radius = (width * 0.4f) * 0.5f,
        center = Offset(width * 0.5f, height * 0.5f)
    )
}

fun drawCrossLinesInCanvas(drawScope: DrawScope, width: Float, height: Float) {
    drawScope.drawLine(
        start = Offset(0f, height * 0.5f),
        end = Offset(width, y = height * 0.5f),
        color = Color.Black,
        strokeWidth = 8f
    )

    drawScope.drawLine(
        start = Offset(width * 0.5f, 0f),
        end = Offset(width * 0.5f, y = height),
        color = Color.Black,
        strokeWidth = 8f
    )
}

fun drawBitmapInCanvas(
    drawScope: DrawScope,
    context: Context,
    imageResource: Int,
    width: Float,
    height: Float
) {
    val bitmapWidth = (width * 0.2f).toInt()
    val bitmapHeight = (height * 0.2f).toInt()

    val bitmap = BitmapFactory.decodeResource(context.resources, imageResource)
    val imageBitmap =
        Bitmap.createScaledBitmap(bitmap, bitmapWidth, bitmapHeight, true).asImageBitmap()

    val topLeftX = 100f // padding start 100px
    val topLeftY = height - bitmapHeight - 30f // padding bottom 30 px

    drawScope.drawImage(
        image = imageBitmap,
        topLeft = Offset(topLeftX, topLeftY)
    )

}

@OptIn(ExperimentalTextApi::class)
fun drawTextInCanvas(
    drawScope: DrawScope,
    width: Float,
    height: Float,
    textMeasurer: TextMeasurer

) {
    val text = "Hridoy Chandra Das"
    val textStyle = TextStyle(
        color = Color.DarkGray,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
    //calculate text area size
    val textLayoutResult: TextLayoutResult = textMeasurer.measure(
        text = AnnotatedString(text = text)
    )

    val textSize = textLayoutResult.size

    drawScope.drawText(
        textMeasurer = textMeasurer,
        text = text,
        topLeft = Offset(
            (width - textSize.width) * 0.5f, //in center x
            20f //padding top 20x
        ),
        style = textStyle
    )


}