package com.ihridoydas.simpleapp.ar.augmentedModelView

import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import io.github.sceneview.ar.ARScene
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArNode
import io.github.sceneview.ar.node.AugmentedImageNode
import io.github.sceneview.math.Position
import io.github.sceneview.math.Rotation
import io.github.sceneview.node.VideoNode

@Composable
fun VideoAugmented(lifecycleScope: LifecycleCoroutineScope,videoNode: VideoNode,sceneView: ArSceneView) {

//        val _node = MutableStateFlow<ArNode?>(null)
//        val nodes = _node.asStateFlow()
//        val node by nodes.collectAsState()
    val nodes = remember { mutableStateListOf<ArNode>() }
    val context = LocalContext.current

    var videNode = videoNode
    Box(modifier = Modifier.fillMaxSize()) {
        ARScene(
            modifier = Modifier.fillMaxSize(),
            nodes = nodes,//node?.let { listOf(it) } ?: emptyList(),
            planeRenderer = true,
            onCreate = { arSceneView ->
                // Apply your configuration

                lifecycleScope.launchWhenCreated {
                    arSceneView.addChild(AugmentedImageNode(
                        engine = sceneView.engine,
                        imageName = "qrcode",
                        bitmap = context.assets.open("augmentedimages/qrcode.png")
                            .use(BitmapFactory::decodeStream),
                        onUpdate = { node, _ ->
                            if (!videoNode.player.isPlaying) {
                                videoNode.player.start()
                            }
//                                            if (node.isTracking) {
//                                                if (!videoNode.player.isPlaying) {
//                                                    videoNode.player.start()
//                                                }
//                                            } else {
//                                                if (videoNode.player.isPlaying) {
//                                                    videoNode.player.pause()
//                                                }
//                                            }
                        }
                    ).apply {
                        videNode = VideoNode(arSceneView.engine, MediaPlayer().apply {
                            setDataSource(
                                context,
                                Uri.parse("https://github.com/ihridoydas/ARSceneViewComposeSample/blob/feature/compose_AR_ImageWithDatabase/app/src/main/res/raw/sakura.mp4?raw=true")
                            )
                            isLooping = true
                            setOnPreparedListener {
                                if ((videoNode.parent as? AugmentedImageNode)?.isTracking == true) {
                                    start()
                                }
                            }
//                                            modelRotation = Rotation(x = 180f)
                            rotation = Rotation(x = 180f)
                            prepareAsync() },
                            glbFileLocation = "https://vrestudio.com/storage/AppTurismo/objetos/16_9.glb",
                            scaleToVideoRatio = false,
                            autoAnimate = true,
                            centerOrigin = Position(y = 1.0f),

                            )
                        arSceneView.addChild(videoNode)
                    })

                }

            },
            onSessionCreate = { session ->
                // Configure the ARCore session
            },
            onFrame = { arFrame ->
                // Retrieve ARCore frame update
            },
            onTap = { hitResult ->
                // User tapped in the AR view
            }
        )
    }

}