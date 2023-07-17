package com.ihridoydas.simpleapp.ar.viewXMLVideoModel

import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.ihridoydas.simpleapp.R
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.AugmentedImageNode
import io.github.sceneview.ar.node.PlacementMode
import io.github.sceneview.math.Position
import io.github.sceneview.math.Rotation
import io.github.sceneview.node.VideoNode

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var sceneView: ArSceneView
    lateinit var videoNode: VideoNode
    lateinit var anchorNode: ArModelNode
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sceneView = view.findViewById(R.id.sceneView)

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
            bitmap = requireContext().assets.open("augmentedimages/sakura.jpeg")
                .use(BitmapFactory::decodeStream),
            onUpdate = { node, _ ->
                if (!videoNode.player.isPlaying) {
                        videoNode.player.start()
                    }
//
//                if (node.isTracking) {
//                    if (!videoNode.player.isPlaying) {
//                        videoNode.player.start()
//                    }
//                } else {
//                    if (videoNode.player.isPlaying) {
//                        videoNode.player.pause()
//                    }
//                }
            }
        ).apply {
            videoNode = VideoNode(
                sceneView.engine,
                MediaPlayer().apply {
                    setDataSource(
                        requireContext(),
                        Uri.parse("https://github.com/ihridoydas/ARSceneViewComposeSample/blob/feature/compose_AR_ImageWithDatabase/app/src/main/res/raw/sakura.mp4?raw=true")
                    )
                    isLooping = true
                    setOnPreparedListener {
                        if ((videoNode.parent as? AugmentedImageNode)?.isTracking == true) {
                            start()
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
                autoAnimate = true,
                scaleToUnits = 0.2f,
                glbFileLocation = "models/16_9.glb",
                scaleToVideoRatio = false,
                centerOrigin = Position(y = 2.0f)
            )
            addChild(videoNode)
        })
    }
    override fun onDestroy() {
        super.onDestroy()
        videoNode.player.stop()
    }
//    override fun onPause() {
//        super.onPause()
//        videoNode.player.pause()
//    }
}