package com.ihridoydas.simpleapp.features.sortingVisualizer.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import com.ihridoydas.simpleapp.ui.theme.GreenExtraLight

@Composable
fun DrawingCanvas(
    modifier: Modifier,
    items: MutableList<Int>
) {
    Canvas(
        modifier = modifier.fillMaxWidth()
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        items.forEachIndexed { i, height ->
            drawLine(
                start = Offset(x = (i).toFloat(), y = canvasHeight),
                end = Offset(x = (i).toFloat(), y = canvasHeight - height),
                color = GreenExtraLight,
                strokeWidth = 2f
            )
        }
    }
}