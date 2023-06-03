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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.tooling.preview.Preview
import com.ihridoydas.simpleapp.features.composibleSheep.canvasExtensions.guidelines.drawAxis
import com.ihridoydas.simpleapp.features.composibleSheep.canvasExtensions.guidelines.drawGrid
import com.ihridoydas.simpleapp.features.composibleSheep.canvasExtensions.nextIndexLoop
import com.ihridoydas.simpleapp.features.composibleSheep.components.CheckBoxLabel
import com.ihridoydas.simpleapp.features.composibleSheep.components.LabeledText
import com.ihridoydas.simpleapp.ui.theme.Grid
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme

@Composable
fun PointsScreen(modifier: Modifier = Modifier) {
    var showGuidelines by remember { mutableStateOf(false) }
    var pointModeIndex by remember { mutableStateOf(0) }
    var strokeCapIndex by remember { mutableStateOf(0) }
    var pathEffectIndex by remember { mutableStateOf(0) }
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

            val points = listOf(
                Offset(0f, size.center.y),
                Offset(size.width.times(0.25f), size.center.y.times(0.25f)),
                size.center,
                Offset(size.width.times(0.75f), size.center.y.times(0.75f)),
                Offset(size.width, size.center.y),
            )
            drawPoints(
                points = points,
                pointMode = pointModeOptions[pointModeIndex].second,
                color = Color.Magenta,
                strokeWidth = Grid.One.toPx(),
                cap = strokeCapOptions[strokeCapIndex].second,
                pathEffect = pathEffectOptions[pathEffectIndex].second
            )

            if (showGuidelines) {
                drawPoints(
                    points = points,
                    pointMode = PointMode.Points,
                    color = Color.Black,
                    strokeWidth = Grid.One.toPx(),
                    cap = strokeCapOptions[strokeCapIndex].second,
                    pathEffect = pathEffectOptions[pathEffectIndex].second
                )

                drawGrid()
                drawAxis()
            }
        }

        Spacer(modifier = Modifier.height(Grid.Two))

        LabeledText("PointMode: ", pointModeOptions[pointModeIndex].first)
        LabeledText("StrokeCap: ", strokeCapOptions[strokeCapIndex].first)
        LabeledText("PathEffect: ", pathEffectOptions[pathEffectIndex].first)

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                pointModeIndex = pointModeOptions.nextIndexLoop(pointModeIndex)
            }
        ) {
            val text = "Change Point Mode"
            Text(text = text)
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                strokeCapIndex = strokeCapOptions.nextIndexLoop(strokeCapIndex)
            }
        ) {
            val text = "Change Stroke Cap"
            Text(text = text)
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                pathEffectIndex = pathEffectOptions.nextIndexLoop(pathEffectIndex)
            }
        ) {
            val text = "Change Path Effect"
            Text(text = text)
        }

        CheckBoxLabel(
            text = "Show Guidelines",
            checked = showGuidelines,
            onCheckedChange = { showGuidelines = it }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    SimpleAppTheme {
        PointsScreen()
    }
}
