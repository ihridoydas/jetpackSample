package com.ihridoydas.simpleapp.features.screenShotCapture.capturable.controller

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

/**
 * Controller for capturing [Composable] content.
 * @see dev.shreyaspatil.capturable.Capturable for implementation details.
 */
class CaptureController internal constructor() {

    /**
     * Medium for providing capture requests
     */
    private val _captureRequests = MutableSharedFlow<CaptureRequest>(extraBufferCapacity = 1)
    internal val captureRequests = _captureRequests.asSharedFlow()

    /**
     * Creates and send a Bitmap capture request with specified [config].
     *
     * Make sure to call this method as a part of callback function and not as a part of the
     * [Composable] function itself.
     *
     * @param config Bitmap config of the desired bitmap. Defaults to [Bitmap.Config.ARGB_8888]
     */
    @Suppress("DeferredResultUnused")
    @OptIn(ExperimentalComposeApi::class)
    @Deprecated(
        message = "This method has been deprecated and will be removed in the upcoming releases. " +
            "Use `captureAsync()` instead",
        replaceWith = ReplaceWith("captureAsync(config)"),
        level = DeprecationLevel.WARNING
    )
    fun capture(config: Bitmap.Config = Bitmap.Config.ARGB_8888) {
        captureAsync(config)
    }

    /**
     * Creates and requests for a Bitmap capture with specified [config] and returns
     * an [ImageBitmap] asynchronously.
     *
     * This method is safe to be called from the "main" thread directly.
     *
     * Make sure to call this method as a part of callback function and not as a part of the
     * [Composable] function itself.
     *
     * @param config Bitmap config of the desired bitmap. Defaults to [Bitmap.Config.ARGB_8888]
     */
    @ExperimentalComposeApi
    fun captureAsync(config: Bitmap.Config = Bitmap.Config.ARGB_8888): Deferred<ImageBitmap> {
        val deferredImageBitmap = CompletableDeferred<ImageBitmap>()
        return deferredImageBitmap.also {
            _captureRequests.tryEmit(CaptureRequest(imageBitmapDeferred = it, config = config))
        }
    }

    /**
     * Holds information of capture request
     */
    internal class CaptureRequest(
        val imageBitmapDeferred: CompletableDeferred<ImageBitmap>,
        val config: Bitmap.Config
    )
}

/**
 * Creates [CaptureController] and remembers it.
 */
@Composable
fun rememberCaptureController(): CaptureController {
    return remember { CaptureController() }
}