package com.ihridoydas.simpleapp.ar.augmentedModelView

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.ar.core.Anchor
import com.google.ar.core.Config
import com.ihridoydas.simpleapp.ui.MainActivity
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode
import java.util.Objects

fun anchorOrMove(anchor: Anchor, arModelNode: ArModelNode, arSceneView: ArSceneView) {
    if (!arSceneView.children.contains(arModelNode)) {
        arSceneView.addChild(arModelNode)
    }
    arModelNode.anchor = anchor
}

fun lightEstimate(arSceneView: ArSceneView, mode: Config.LightEstimationMode){
    arSceneView.lightEstimationMode = mode
}



fun checkIsSupportedDeviceOrFinish(activity: Activity): Boolean {
    val MIN_OPENGL_VERSION = 3.0
    val TAG: String = MainActivity::class.java.simpleName
    val openGlVersionString =
        (Objects.requireNonNull(
            activity
                .getSystemService(Context.ACTIVITY_SERVICE)
        ) as ActivityManager)
            .deviceConfigurationInfo
            .glEsVersion
    if (openGlVersionString.toDouble() < MIN_OPENGL_VERSION) {
        Log.e(TAG, "Sceneform requires OpenGL ES ${MIN_OPENGL_VERSION} later")
        Toast.makeText(
            activity,
            "Sceneform requires OpenGL ES ${MIN_OPENGL_VERSION} or later",
            Toast.LENGTH_LONG
        )
            .show()
        activity.finish()
        return false
    }
    return true
}
