package com.ihridoydas.simpleapp.ar.augmentedImage

import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.filament.MaterialInstance
import com.google.ar.sceneform.rendering.ExternalTexture
import com.ihridoydas.simpleapp.ui.MainActivity
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import com.ihridoydas.simpleapp.util.common.OnComposeLifecycleEvent
import io.github.sceneview.ar.ARScene
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.ArNode
import io.github.sceneview.ar.node.AugmentedImageNode
import io.github.sceneview.ar.node.PlacementMode
import io.github.sceneview.math.Position
import io.github.sceneview.math.Rotation
import io.github.sceneview.node.VideoNode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AugmentedImageARScreen(
    activity: MainActivity,
    onBackPress: () -> Unit
) {
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                AvApp(activity)
            }
        }
    )
}


@Composable
fun AvApp(activity: MainActivity) {
    val nodes = remember { mutableStateListOf<ArNode>() }

//    lateinit var sceneView: ArSceneView
    lateinit var videoNode: VideoNode
    lateinit var anchorNode: ArModelNode


    ARScene(
        modifier = Modifier.fillMaxSize(),
        nodes = nodes,
        planeRenderer = true,
        onCreate = { sceneView ->
            sceneView.apply {
                anchorNode = ArModelNode(sceneView.engine).apply {
                    placementMode = PlacementMode.BEST_AVAILABLE
                    // anchorPoseUpdateInterval = null
                    //isSmoothPoseEnable = true
                    //followHitPosition = false
                    applyPosePosition = true
                    isVisible = true
                    isScaleEditable = false
                    minEditableScale = 10.0f
                }
                sceneView.addChild(anchorNode)

                sceneView.addChild(AugmentedImageNode(
                    engine = sceneView.engine,
                    imageName = "qrcode",
                    bitmap = activity.assets.open("augmentedimages/augmented-images-earth.jpg")
                        .use(BitmapFactory::decodeStream),
//            onUpdate = { node, _ ->
//                if (node.isTracking) {
//                    if (!videoNode.player.isPlaying) {
//                        videoNode.player.start()
//                    }
//                } else {
//                    if (videoNode.player.isPlaying) {
//                        videoNode.player.pause()
//                    }
//                }
//            }
                ).apply {
                    videoNode = VideoNode(
                        sceneView.engine,
                        MediaPlayer().apply {
                            setDataSource(
                                activity,
                                Uri.parse("https://github.com/ihridoydas/ARSceneViewComposeSample/blob/feature/compose_AR_ImageWithDatabase/app/src/main/res/raw/sakura.mp4?raw=true")
                            )
                            isLooping = true
                            setOnPreparedListener {
                                if ((videoNode.parent as? AugmentedImageNode)?.isTracking == true) {
                                    start()
                                }else{
                                    Toast.makeText(context,"Please wait.Video is Loading..",Toast.LENGTH_LONG).show()
                                }
                            }
                            onTap = { _, _ ->
                                if (!this.isPlaying) {
                                    Log.d("message", "Is in play")
                                    // Start the video
                                    this.start()
                                } else {
                                    Log.d("message", "Is in pause mode")
                                    this.pause()
                                }

                            }
                            rotation = Rotation(x = 180.0f)
                            prepareAsync()
                        },
                        glbFileLocation = "https://vrestudio.com/storage/AppTurismo/objetos/16_9.glb",
                        scaleToVideoRatio = false,
                        centerOrigin = Position(y = 1.0f)
                    )

                    addChild(videoNode)
                })
            }
        },
        onFrame = {

        }
    )
    OnComposeLifecycleEvent { _, event ->
        when (event) {
            Lifecycle.Event.ON_DESTROY -> {
                videoNode.player.stop()
            }

            else -> {}
        }
    }
}

@Preview
@Composable
fun AvAppPreview() {
    SimpleAppTheme() {
        AvApp(activity = MainActivity())
    }
}
