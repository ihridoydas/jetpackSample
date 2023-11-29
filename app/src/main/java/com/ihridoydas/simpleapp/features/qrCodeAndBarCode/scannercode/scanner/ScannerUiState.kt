package com.ihridoydas.simpleapp.features.qrCodeAndBarCode.scannercode.scanner

import androidx.camera.view.PreviewView
import com.ihridoydas.simpleapp.features.qrCodeAndBarCode.scannercode.model.Scan

data class ScannerUiState(
    val previewView: PreviewView? = null,
    val scan: Scan? = null,
    val scanValues: Scan? = null,
    val showBottomSheet: Boolean = false,
    val showCameraRequiredDialog: Boolean = false
)
