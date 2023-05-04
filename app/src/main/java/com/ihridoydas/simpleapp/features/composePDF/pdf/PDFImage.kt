package com.ihridoydas.simpleapp.features.composePDF.pdf

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale

@Composable
internal fun PdfImage(
    dimension: () -> Dimension,
    graphicsLayerData: () -> GraphicsLayerData,
    bitmap: () -> ImageBitmap,
    contentDescription: String = "",
) {
    Image(
        bitmap = bitmap(),
        contentDescription = contentDescription,
        contentScale = ContentScale.None,
        modifier = Modifier
            .size(dimension())
            .graphicsLayer {
                val gld = graphicsLayerData()
                scaleX = gld.scale
                scaleY = gld.scale
                translationX = gld.translationX
                translationY = gld.translationY
            }
    )
}
