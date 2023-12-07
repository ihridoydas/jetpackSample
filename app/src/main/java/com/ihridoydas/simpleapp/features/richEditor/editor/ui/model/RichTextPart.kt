package com.ihridoydas.simpleapp.features.richEditor.editor.ui.model

data class RichTextPart(
    val fromIndex: Int,
    val toIndex: Int,
    val styles: Set<RichTextStyle>,
)