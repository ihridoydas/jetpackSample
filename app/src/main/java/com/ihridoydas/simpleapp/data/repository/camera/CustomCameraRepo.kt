package com.ihridoydas.simpleapp.data.repository.camera

import android.content.Context
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner

interface CustomCameraRepo {

    suspend fun captureAndSaveImage(context:Context)
    suspend fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner
    )
}