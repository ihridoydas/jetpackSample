package com.ihridoydas.simpleapp.features.qrCodeAndBarCode.scannercode.scanner

import androidx.lifecycle.LifecycleOwner

sealed class ScannerEvent {
    data class CreatePreviewView(val lifecycleOwner: LifecycleOwner): ScannerEvent()
    data object BottomSheetShown: ScannerEvent()
    data object BottomSheetHidden: ScannerEvent()
    data class CameraRequiredDialogVisibility(val show: Boolean): ScannerEvent()
}
