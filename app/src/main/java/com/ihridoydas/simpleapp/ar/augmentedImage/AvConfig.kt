package com.ihridoydas.simpleapp.ar.augmentedImage

import android.content.Context
import android.os.Build
import com.google.ar.core.AugmentedImageDatabase
import com.google.ar.core.Config
import com.google.ar.core.TrackingFailureReason
import io.github.sceneview.ar.ArSceneView
import com.ihridoydas.simpleapp.R

fun ArSceneView.setupAvConfigurations() {
    configureSession { arSession, config ->
        config.focusMode = Config.FocusMode.FIXED
        config.instantPlacementMode = Config.InstantPlacementMode.DISABLED
        config.planeFindingMode = Config.PlaneFindingMode.HORIZONTAL_AND_VERTICAL
        config.lightEstimationMode = Config.LightEstimationMode.ENVIRONMENTAL_HDR
        config.augmentedImageDatabase = resources.openRawResource(R.raw.imagedb).use { database ->
            AugmentedImageDatabase.deserialize(arSession, database)
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
}
