
package com.ihridoydas.simpleapp.controll

import android.graphics.Color
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import java.util.*

@Composable
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
/** Control for playing local video in asset folder
 * @param filename filename without extension
 * @param extension filename extension, default .mp4
 * */
fun VideoPlayer(modifier: Modifier = Modifier, filename: String, extension: String = ".mp4") { //you can use view-model as a parameter

    val context = LocalContext.current

    fun refresh(exoPlayer: ExoPlayer) {
        Log.d("videoplayer", filename)
        val firstVideoUri = Uri.parse("asset:///${filename}$extension")
        val firstItem = MediaItem.fromUri(firstVideoUri)
        exoPlayer.addMediaItem(firstItem)
        exoPlayer.playWhenReady = true
        exoPlayer.prepare()
        exoPlayer.play()
    }

    val exoPlayer = remember() {
        ExoPlayer.Builder(context).build().apply {
            refresh(this)
        }
    }

    DisposableEffect(
        AndroidView(
            modifier = modifier.zIndex(100000f),
            factory = { context ->
                PlayerView(context).apply {
                    player = exoPlayer
                    useController = false
                    Log.d("videoplayer", "create videoplayer")
                    setShutterBackgroundColor(Color.argb(255, 232, 234, 238))
                }
            }
        )
    ) {
        onDispose {
            // release resources prevent memory leaks on recomposition
            Log.d("videoplayer", "dispose")
            exoPlayer.release()
        }
    }
}

class VideoPlayer() {

    companion object {
        /** Is video in video in foreground, used so video is not played when coming to foreground
         * (video was transparent and replays video). Later set to true when navigating to next new photo */
        var InForeground = mutableStateOf(false)
    }
}

//Use Like This

/**
     VideoPlayer(
        modifier = Modifier
        .size(116.dp,56.dp),
        filename = viewModel.cameraGuide.getAssetName(viewModel.indexPhoto + 1),
    )
 */