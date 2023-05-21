package com.ihridoydas.simpleapp.ar.augmentedImage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import io.github.sceneview.ar.ARScene


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AugmentedImageARScreen(onBackPress:()->Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(Color.Black),
                title = { Text(text = "Augmented Image" , style = TextStyle(color = Color.White)) },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackPress()
                        },
                        modifier = Modifier
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White )
                    }
                },
            )
        },
        drawerShape = RoundedCornerShape(topEnd = 23.dp, bottomEnd = 23.dp),
        content = {
           Box(modifier = Modifier.padding(it)){
               AvApp()
           }
        }
    )
}


@Composable
fun AvApp(
    mainViewModel: MainViewModel = viewModel(),
) {
    val node by mainViewModel.node.collectAsState()

    ARScene(
        modifier = Modifier.fillMaxSize(),
        nodes = node?.let { listOf(it) } ?: emptyList(),
        planeRenderer = true,
        onCreate = {
            it.setupAvConfigurations()
        },
        onFrame = {
            mainViewModel.renderVideo(it.updatedAugmentedImages)
        }
    )
}

@Preview
@Composable
fun AvAppPreview() {
    SimpleAppTheme() {
        AvApp()
    }
}
