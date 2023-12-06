package com.ihridoydas.simpleapp.features.qrCodeAndBarCode.scannercode.data

import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.ihridoydas.simpleapp.features.qrCodeAndBarCode.scannercode.model.Scan
import kotlinx.coroutines.flow.Flow

interface ScanRepository {

    fun getLatestScan(): Flow<Scan>

    suspend fun getCameraPreview(lifecycleOwner: LifecycleOwner): PreviewView

    suspend fun pauseScan()

    suspend fun resumeScan()

}