package com.ihridoydas.simpleapp.util.responsiveUI.component.shape.ticketShape

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import com.ihridoydas.simpleapp.util.responsiveUI.component.shape.polyWaveShape.PolygonImageComposable

@Composable
fun TicketComposable(modifier: Modifier) {
    Text(
        text = "🎉 CINEMA TICKET 🎉",
        style = TextStyle(
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Black,
        ),
        textAlign = TextAlign.Center,
        modifier = modifier
            .wrapContentSize()
            .graphicsLayer {
                shadowElevation = 8.dp.toPx()
                shape = TicketShape(circleRadius = 15.dp, cornerSize = CornerSize(5.dp))
                clip = true
            }
            .background(color = Color.Black)
            .drawBehind {
                scale(scale = 0.9f) {
                    drawPath(
                        path = drawTicketPath(size = size, cornerRadius = 24.dp.toPx()),
                        color = Color.Red,
                        style = Stroke(
                            width = 2.dp.toPx(),
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
                        )
                    )
                }
            }
            .padding(start = 32.dp, top = 64.dp, end = 32.dp, bottom = 64.dp)
    )
}


@Composable
fun SimpleTicketComposable(modifier: Modifier) {
    Text(
        text = "🎉 CINEMA TICKET 🎉",
        style = TextStyle(
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Black,
        ),
        textAlign = TextAlign.Center,
        modifier = modifier
            .wrapContentSize()
            .graphicsLayer {
                shadowElevation = 8.dp.toPx()
                shape = SimpleTicketShape(50f)
                clip = true
            }
            .background(color = Color.Black)
            .drawBehind {
                scale(scale = 0.9f) {
                    drawPath(
                        path = drawTicketPath(size = size, cornerRadius = 24.dp.toPx()),
                        color = Color.Red,
                        style = Stroke(
                            width = 2.dp.toPx(),
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
                        )
                    )
                }
            }
            .padding(start = 32.dp, top = 64.dp, end = 32.dp, bottom = 64.dp)
    )
}

@Preview
@Composable
fun AllShape() {
    SimpleAppTheme {
        Column(modifier= Modifier.fillMaxSize(),Arrangement.Center,Alignment.CenterHorizontally) {
            TicketComposable(modifier = Modifier)
            Spacer(modifier = Modifier.height(10.dp))
            SimpleTicketComposable(modifier = Modifier)
            Spacer(modifier = Modifier.height(10.dp))
            PolygonImageComposable(modifier = Modifier)
            Spacer(modifier = Modifier.height(10.dp))
            TicketWaveComposable(modifier = Modifier)
        }

    }
}