package com.ihridoydas.simpleapp.domain.ocr

import android.graphics.Bitmap
import android.net.Uri
import kotlinx.coroutines.flow.Flow

interface OCRRepo {


    fun getTextFromCapturedImage(bitmap: Bitmap):Flow<String>

    fun getTextFromSelectedImage(uri: Uri):Flow<String>

    fun copyTextToClipboard(text:String)

}