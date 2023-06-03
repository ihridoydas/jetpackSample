package com.ihridoydas.simpleapp.features.composibleSheep.screens.canvasbasics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ihridoydas.simpleapp.features.composibleSheep.canvasExtensions.guidelines.GuidelineDashPattern
import com.ihridoydas.simpleapp.features.composibleSheep.canvasExtensions.guidelines.drawAxis
import com.ihridoydas.simpleapp.features.composibleSheep.canvasExtensions.guidelines.drawGrid
import com.ihridoydas.simpleapp.features.composibleSheep.components.CheckBoxLabel
import com.ihridoydas.simpleapp.features.composibleSheep.sheep.extra.getSheepPathEffect
import com.ihridoydas.simpleapp.ui.theme.Grid
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme

@Composable
fun LineScreen(modifier: Modifier = Modifier) {
    var showSimpleDiagonal by remember { mutableStateOf(false) }
    var showDashPattern by remember { mutableStateOf(false) }
    var showSheepLine by remember { mutableStateOf(false) }
    var showGuidelines by remember { mutableStateOf(true) }
    var showCanvasGuideline by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Canvas(
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {

            if (showSimpleDiagonal) {
                drawLine(
                    color = Color.Magenta,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, size.height)
                )
            }

            if (showDashPattern) {
                drawLine(
                    color = Color.Cyan,
                    start = size.center,
                    end = Offset(0f, size.height),
                    pathEffect = GuidelineDashPattern,
                    strokeWidth = 10f
                )
            }

            if (showSheepLine) {
                val miniFluffRadius = size.width.div(20)
                drawLine(
                    color = Color.Blue,
                    start = Offset(0f, size.center.y),
                    end = Offset(size.width, 0f),
                    pathEffect = getSheepPathEffect(miniFluffRadius)
                )
            }

            if (showGuidelines) {
                drawGrid()
                drawAxis()
            }

            if (showCanvasGuideline) {
                drawGrid(color = Color.Black, numberOfCells = 10)
                drawAxis(
                    colorX = Color.Red,
                    colorY = Color.Blue,
                    axisCenter = Offset.Zero,
                    pathEffect = null,
                    lineStrokeWidth = Grid.Half.toPx()
                )
            }
        }

        Spacer(modifier = Modifier.height(Grid.Two))

        CheckBoxLabel(
            text = "Show Simple Diagonal",
            checked = showSimpleDiagonal,
            onCheckedChange = { showSimpleDiagonal = it }
        )

        CheckBoxLabel(
            text = "Show Dash Pattern",
            checked = showDashPattern,
            onCheckedChange = { showDashPattern = it }
        )

        CheckBoxLabel(
            text = "Show Sheep Line",
            checked = showSheepLine,
            onCheckedChange = { showSheepLine = it }
        )

        CheckBoxLabel(
            text = "Show Guidelines",
            checked = showGuidelines,
            onCheckedChange = { showGuidelines = it }
        )

        CheckBoxLabel(
            text = "Show Guidelines from (0,0)",
            checked = showCanvasGuideline,
            onCheckedChange = { showCanvasGuideline = it }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    SimpleAppTheme {
        LineScreen()
    }
}
