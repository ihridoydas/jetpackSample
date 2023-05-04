package com.ihridoydas.simpleapp.features.composePDF.pdfUseCase

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ihridoydas.simpleapp.features.composePDF.pdf.HorizontalPdfReaderState
import com.ihridoydas.simpleapp.features.composePDF.pdf.PdfReaderState
import com.ihridoydas.simpleapp.features.composePDF.pdf.ResourceType
import com.ihridoydas.simpleapp.features.composePDF.pdf.VerticalPdfReaderState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PDFViewModel : ViewModel() {
    private val mStateFlow = MutableStateFlow<PdfReaderState?>(null)
    val stateFlow: StateFlow<PdfReaderState?> = mStateFlow

    val switchState = mutableStateOf(false)

    fun openResource(resourceType: ResourceType) {
        mStateFlow.tryEmit(
            if (switchState.value) {
                HorizontalPdfReaderState(resourceType, true)
            } else {
                VerticalPdfReaderState(resourceType, true)
            }
        )
    }

    fun clearResource() {
        mStateFlow.tryEmit(null)
    }
}
