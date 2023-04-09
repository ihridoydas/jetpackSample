package com.ihridoydas.simpleapp.util.responsiveUI.component.animations.downloadableAnimationCircle

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun DownLoadableAnimation() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        val progress = remember {
            Animatable(0f)
        }
        var startDownload by remember {
            mutableStateOf(false)
        }

        DownloadButton(
            onClick = { startDownload = !startDownload },
            strokeColor = MaterialTheme.colorScheme.onPrimary,
            strokeSize = 8.dp,
            progress = progress.value,
            modifier = Modifier.size(300.dp)
        )

        LaunchedEffect(key1 = startDownload) {
            if (startDownload) {
                progress.animateTo(
                    1f,
                    tween(6000, 800)
                )
            } else {
                progress.snapTo(0f)
            }
        }
    }


}