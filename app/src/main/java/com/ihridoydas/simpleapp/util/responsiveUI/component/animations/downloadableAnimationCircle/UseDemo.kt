package com.ihridoydas.simpleapp.util.responsiveUI.component.animations.downloadableAnimationCircle

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DownLoadableAnimation(onBackPress: ()-> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.primary),
                modifier = Modifier,
                title = { Text(text = "Downloadable Circle", style = TextStyle(color = Color.White)) },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackPress()
                        },
                        modifier = Modifier
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
            )
        },
        drawerShape = RoundedCornerShape(topEnd = 23.dp, bottomEnd = 23.dp),
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .padding(vertical = 0.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
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
        }
    )
}

@Preview
@Composable
fun DownloableCirclePrev() {
    SimpleAppTheme {
        DownLoadableAnimation({})
    }

}