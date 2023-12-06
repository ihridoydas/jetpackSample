package com.ihridoydas.simpleapp.features.screenShotCapture.capturable

import android.graphics.Bitmap
import android.graphics.Picture
import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.CacheDrawModifierNode
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.draw
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DelegatingNode
import androidx.compose.ui.node.ModifierNodeElement
import com.ihridoydas.simpleapp.features.screenShotCapture.capturable.controller.CaptureController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ***Deprecated: Use Modifier.[capturable] for replacement.***
 *
 * A composable with [content] which supports to capture [ImageBitmap] from a [content].
 *
 * Example usage:
 *
 * ```
 *  val captureController = rememberCaptureController()
 *  Capturable(
 *      controller = captureController,
 *      onCaptured = { bitmap, error ->
 *          // Do something with [bitmap]
 *          // Handle [error] if required
 *      }
 *  ) {
 *      // Composable content
 *  }
 *
 *  Button(onClick = {
 *      // Capture content
 *      captureController.capture()
 *  }) { ... }
 * ```
 *
 * @param controller A [CaptureController] which gives control to capture the [content].
 * @param modifier The [Modifier] to be applied to the layout.
 * @param onCaptured The callback which gives back [ImageBitmap] after composable is captured.
 * If any error is occurred while capturing bitmap, [Throwable] is provided.
 * @param content [Composable] content to be captured.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Deprecated(
    "This Composable method has been deprecated & will be removed in the future releases. " +
        "Use Modifier `capturable()` directly.",
    level = DeprecationLevel.WARNING
)
@Composable
fun Capturable(
    controller: CaptureController,
    modifier: Modifier = Modifier,
    onCaptured: (ImageBitmap?, Throwable?) -> Unit,
    content: @Composable () -> Unit
) {
    val updatedOnCaptured by rememberUpdatedState(newValue = onCaptured)

    Column(modifier = modifier.capturable(controller)) {
        content()
    }

    LaunchedEffect(key1 = controller) {
        controller.captureRequests.collect { request ->
            try {
                val imageBitmap = request.imageBitmapDeferred.await()
                updatedOnCaptured(imageBitmap, null)
            } catch (error: Throwable) {
                updatedOnCaptured(null, error)
            }
        }
    }
}

/**
 * Adds a capture-ability on the Composable which can draw Bitmap from the Composable component.
 *
 * Example usage:
 *
 * ```
 *  val captureController = rememberCaptureController()
 *  val uiScope = rememberCoroutineScope()
 *
 *  // The content to be captured in to Bitmap
 *  Column(
 *      modifier = Modifier.capturable(captureController),
 *  ) {
 *      // Composable content
 *  }
 *
 *  Button(onClick = {
 *      // Capture content
 *      val bitmapAsync = captureController.captureAsync()
 *      try {
 *          val bitmap = bitmapAsync.await()
 *          // Do something with `bitmap`.
 *      } catch (error: Throwable) {
 *          // Error occurred, do something.
 *      }
 *  }) { ... }
 * ```
 *
 * @param controller A [CaptureController] which gives control to capture the Composable content.
 */
@ExperimentalComposeUiApi
fun Modifier.capturable(controller: CaptureController): Modifier {
    return this then CapturableModifierNodeElement(controller)
}

/**
 * Modifier implementation of Capturable
 */
private data class CapturableModifierNodeElement(
    private val controller: CaptureController
) : ModifierNodeElement<CapturableModifierNode>() {
    override fun create(): CapturableModifierNode {
        return CapturableModifierNode(controller)
    }

    override fun update(node: CapturableModifierNode) {
        node.controller = controller
    }
}

/**
 * Capturable Modifier node which delegates task to the [CacheDrawModifierNode] for drawing
 * Composable UI to the Picture and then helping it to converting picture into a Bitmap.
 */
@Suppress("unused")
private class CapturableModifierNode(
    var controller: CaptureController
) : DelegatingNode(), DelegatableNode {

    val picture = Picture()

    /**
     * Delegates the drawing to [CacheDrawModifierNode] in order to draw content rendered on the
     * canvas directly to the [picture].
     */
    val drawModifierNode = delegate(
        CacheDrawModifierNode {
            // Example that shows how to redirect rendering to an Android Picture and then
            // draw the picture into the original destination
            val width = this.size.width.toInt()
            val height = this.size.height.toInt()

            onDrawWithContent {
                val pictureCanvas = Canvas(picture.beginRecording(width, height))

                draw(this, this.layoutDirection, pictureCanvas, this.size) {
                    this@onDrawWithContent.drawContent()
                }
                picture.endRecording()

                drawIntoCanvas { canvas -> canvas.nativeCanvas.drawPicture(picture) }
            }
        }
    )

    override fun onAttach() {
        super.onAttach()
        coroutineScope.launch {
            controller.captureRequests.collect { request ->
                val completable = request.imageBitmapDeferred
                try {
                    val bitmap = withContext(Dispatchers.Default) {
                        picture.asBitmap(request.config)
                    }
                    completable.complete(bitmap.asImageBitmap())
                } catch (error: Throwable) {
                    completable.completeExceptionally(error)
                }
            }
        }
    }
}

/**
 * Creates a [Bitmap] from a [Picture] with provided [config]
 */
private fun Picture.asBitmap(config: Bitmap.Config): Bitmap {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        Bitmap.createBitmap(this@asBitmap)
    } else {
        val bitmap = Bitmap.createBitmap(
            /* width = */
            this@asBitmap.width,
            /* height = */
            this@asBitmap.height,
            /* config = */
            config
        )
        val canvas = android.graphics.Canvas(bitmap)
        canvas.drawColor(android.graphics.Color.WHITE)
        canvas.drawPicture(this@asBitmap)
        bitmap
    }
}