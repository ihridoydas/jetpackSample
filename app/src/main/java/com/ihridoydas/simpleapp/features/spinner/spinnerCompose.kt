package com.ihridoydas.simpleapp.features.spinner

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme

@Composable
fun SpinView() {
    val isPressed by remember { mutableStateOf(false) }
    val vibrator = LocalContext.current.getSystemService(Vibrator::class.java) as Vibrator

    var rotationState by remember { mutableStateOf(0f) }
    var rotationSpeed by remember { mutableStateOf(0f) }

    val rotationAnimation = rememberInfiniteTransition(label = "")
        .animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            ), label = ""
        )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.fidget_spinner_yellow),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, _, _ ->
                        rotationSpeed = pan.x * 0.5f
                    }
                }
                .rotate(rotationState),
            contentScale = ContentScale.FillBounds,
            alignment = Alignment.Center,
            alpha = if (isPressed) 0.5f else 1.0f
        )

        LaunchedEffect(isPressed) {
            if (isPressed) {
                startVibration(vibrator)
            } else {
                stopVibration(vibrator)
            }
        }

        LaunchedEffect(rotationSpeed) {
            rotationState = rotationAnimation.value
        }
    }
}
@Suppress("DEPRECATION")
private fun startVibration(vibrator: Vibrator?) {
    // if the device can vibrate, then let's vibrate!
    vibrator?.let {
        if (it.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                it.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                @Suppress("DEPRECATION")
                it.vibrate(500)
            }
        }
    }
}

private fun stopVibration(vibrator: Vibrator?) {
    // If the device can vibrate, then let's stop all of its vibrations
    vibrator?.let {
        if (it.hasVibrator()) {
            it.cancel()
        }
    }
}

@Preview
@Composable
fun SpinViewPreview() {
    SimpleAppTheme {
        SpinView()
    }

}
