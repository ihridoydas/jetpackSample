package com.ihridoydas.simpleapp.features.newTonsTimer

import androidx.compose.animation.animateColorAsState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.ihridoydas.simpleapp.features.newTonsTimer.timer.NewtonsTimerScreen
import com.ihridoydas.simpleapp.features.newTonsTimer.ui.theme.MyTheme

/**
 * My newtons timer app
 * In setContent just put in
    setContent {
            val viewModel: TimerViewModel = viewModel()
            MyTheme(darkMode = viewModel.darkMode) {
            val systemBarsColor by animateColorAsState(Colors.systemBars)
            setSystemBarsColor(systemBarsColor)

            MyNewtonsTimerApp()

            }
     }
 */

@Composable
fun MyNewtonsTimerApp() {
    val backgroundColor by animateColorAsState(MaterialTheme.colors.background)
    Surface(color = backgroundColor) {
        NewtonsTimerScreen()
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyNewtonsTimerApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkMode = true) {
        MyNewtonsTimerApp()
    }
}
