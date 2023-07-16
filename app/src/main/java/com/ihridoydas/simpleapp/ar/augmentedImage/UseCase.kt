package com.ihridoydas.simpleapp.ar.augmentedImage

import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LOG_TAG
import com.google.ar.sceneform.lullmodel.VertexAttributeUsage.Position
import com.google.ar.schemas.lull.VertexAttributeUsage.Position
import com.ihridoydas.simpleapp.ar.augmentedModelView.VideoAugmented
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import io.github.sceneview.ar.ARScene
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArNode
import io.github.sceneview.ar.node.AugmentedImageNode
import io.github.sceneview.math.Position
import io.github.sceneview.math.Rotation
import io.github.sceneview.node.VideoNode
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AugmentedImageARScreen(
    videoNode: VideoNode,
    lifecycleScope: LifecycleCoroutineScope,
    sceneView: ArSceneView,
    onBackPress: () -> Unit) {

    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(Color.Black),
                title = { Text(text = "Augmented Image", style = TextStyle(color = Color.White)) },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackPress()
                        },
                        modifier = Modifier
                    ) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
            )
        },
        drawerShape = RoundedCornerShape(topEnd = 23.dp, bottomEnd = 23.dp),
        content = {
            Log.d(LOG_TAG, "AugmentedImageARScreen: $it")

            VideoAugmented(lifecycleScope = lifecycleScope, videoNode = videoNode, sceneView = sceneView)

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
        //AvApp()
    }
}
