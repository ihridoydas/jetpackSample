package com.ihridoydas.simpleapp.features.pendulum

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.ui.theme.Crystal
import com.ihridoydas.simpleapp.ui.theme.Desire
import com.ihridoydas.simpleapp.ui.theme.Honeydew
import com.ihridoydas.simpleapp.ui.theme.JellyBeanBlue
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme

@Composable
fun PendulumApp() {
    SimpleAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Desire
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Pendulum(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    bgColor = Desire,
                    boxColor = Honeydew,
                    circleColor = JellyBeanBlue,
                    eyeColor = Honeydew,
                    pupilColor = Color.Black,
                    threadColor = Color.Black,
                    ballColor = Crystal
                )
                Spacer(modifier = Modifier.height(60.dp))
                Time(
                    modifier = Modifier.fillMaxWidth(),
                    color = Honeydew
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleAppTheme {
        PendulumApp()
    }
}