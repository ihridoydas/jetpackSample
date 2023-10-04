package com.ihridoydas.simpleapp.features.richEditor.editor.ui.data

import android.net.Uri
import androidx.compose.runtime.Immutable
import com.ihridoydas.simpleapp.features.richEditor.editor.ui.data.ContentType
import com.ihridoydas.simpleapp.features.richEditor.editor.ui.data.ContentValue

@Immutable
data class VideoContentValue constructor(
    internal val uri: Uri,
) : ContentValue() {

    override val type: ContentType = ContentType.VIDEO
    override var isFocused: Boolean = false
}