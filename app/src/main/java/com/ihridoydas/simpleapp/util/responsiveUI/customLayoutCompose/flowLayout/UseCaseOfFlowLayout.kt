package com.ihridoydas.simpleapp.util.responsiveUI.customLayoutCompose.flowLayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import kotlin.random.Random

//Use Flow Layout in compose
@Composable
fun FlowLayoutScreen() {
    FlowLayout{
        val images = intArrayOf(
            R.drawable.user_one,
            R.drawable.burger,
            R.drawable.user_one,
            R.drawable.ic_logo,
            R.drawable.user_one,
            R.drawable.user_one,
            R.drawable.icon,
            R.drawable.user_one,
            R.drawable.base_image,
            R.drawable.user_one,

        )

        for (i in images.indices) {
            Image(
                painter = painterResource(id = images[i]),
                contentDescription = null,
                modifier = Modifier
                    .width(Random.nextInt(50, 120).dp)
                    .height(Random.nextInt(50, 120).dp)
                    .border(width = 1.dp, color = Color.White, shape = CircleShape)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop

            )
        }

    }

}

fun flowLayoutMeasurePolicy() = MeasurePolicy{ measurables,constraints ->
    layout(constraints.maxWidth,constraints.maxHeight){
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }
        var yPos = 0
        var xPos = 0
        var maxY = 0
        placeables.forEach { placeable ->
            if (xPos + placeable.width >
                constraints.maxWidth
            ) {
                xPos = 0
                yPos += maxY
                maxY = 0
            }
            placeable.placeRelative(
                x = xPos,
                y = yPos
            )
            xPos += placeable.width
            if (maxY < placeable.height) {
                maxY = placeable.height
            }
        }
    }
}

@Composable
fun FlowLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
){
    val measurePolicy = flowLayoutMeasurePolicy()
    Layout(measurePolicy = measurePolicy,
        content = content,
        modifier = modifier )
}

@Preview(showBackground = true,)
@Composable
fun FlowLayoutPreview() {
    SimpleAppTheme {
        FlowLayoutScreen()
    }

}