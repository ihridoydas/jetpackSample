package com.ihridoydas.simpleapp.ar.augmentedImage

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.ar.core.AugmentedImageDatabase
import com.google.ar.core.Config
import com.google.ar.core.TrackingFailureReason
import io.github.sceneview.ar.ArSceneView
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.util.constants.BAD_STATE
import com.ihridoydas.simpleapp.util.constants.CAMERA_UNAVAILABLE
import com.ihridoydas.simpleapp.util.constants.EXCESSIVE_MOTION
import com.ihridoydas.simpleapp.util.constants.INSUFFICIENT_FEATURES
import com.ihridoydas.simpleapp.util.constants.INSUFFICIENT_LIGHT
import com.ihridoydas.simpleapp.util.constants.NONE

fun ArSceneView.setupAvConfigurations() {
    configureSession { arSession, config ->
        config.focusMode = Config.FocusMode.FIXED
        config.instantPlacementMode = Config.InstantPlacementMode.LOCAL_Y_UP
        config.planeFindingMode = Config.PlaneFindingMode.HORIZONTAL_AND_VERTICAL
        config.lightEstimationMode = Config.LightEstimationMode.ENVIRONMENTAL_HDR
        config.augmentedImageDatabase = resources.openRawResource(R.raw.imagedb).use { database ->
            AugmentedImageDatabase.deserialize(arSession, database)
        }
    }
}

//Tracking ちゃんとできない場合
fun TrackingFailureReason.getDescription(context: Context) = when (this) {
    TrackingFailureReason.NONE -> ""
    TrackingFailureReason.BAD_STATE -> context.getString(R.string.sceneview_bad_state_message)
    TrackingFailureReason.INSUFFICIENT_LIGHT -> context.getString(
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.R) {
            R.string.sceneview_insufficient_light_message
        } else {
            R.string.sceneview_insufficient_light_android_s_message
        }
    )

    TrackingFailureReason.EXCESSIVE_MOTION -> context.getString(R.string.sceneview_excessive_motion_message)
    TrackingFailureReason.INSUFFICIENT_FEATURES -> context.getString(R.string.sceneview_insufficient_features_message)
    TrackingFailureReason.CAMERA_UNAVAILABLE -> context.getString(R.string.sceneview_camera_unavailable_message)
    else -> context.getString(R.string.sceneview_unknown_tracking_failure, this)
}


fun enableFullScreen(context: Context) {
    val window = context.findActivity().window
    WindowCompat.setDecorFitsSystemWindows(window, false)
    WindowInsetsControllerCompat(window, window.decorView).apply {
        systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        hide(WindowInsetsCompat.Type.systemBars())
    }
}

fun disableFullScreen(context: Context) {
    val window = context.findActivity().window
    WindowCompat.setDecorFitsSystemWindows(window, true)
    WindowInsetsControllerCompat(window, window.decorView).apply {
        show(WindowInsetsCompat.Type.systemBars())
        systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
    }
}

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}


fun setupTrackingFailureChanged(trackingFailureReason: TrackingFailureReason) {
    trackingFailureReason.apply {
        when (this) {
            TrackingFailureReason.NONE -> NONE
            TrackingFailureReason.BAD_STATE -> BAD_STATE
            TrackingFailureReason.INSUFFICIENT_LIGHT -> INSUFFICIENT_LIGHT
            TrackingFailureReason.EXCESSIVE_MOTION -> EXCESSIVE_MOTION
            TrackingFailureReason.INSUFFICIENT_FEATURES -> INSUFFICIENT_FEATURES
            TrackingFailureReason.CAMERA_UNAVAILABLE -> CAMERA_UNAVAILABLE
        }
    }

}