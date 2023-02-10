package com.ihridoydas.simpleapp.util.responsiveUI

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit


/**
 * ResponsiveTextのTEXT_SCALE_REDUCTION_INTERVAL
 */
private const val TEXT_SCALE_REDUCTION_INTERVAL = 0.9f

/**
 * Responsive Custom Text
 */
@Composable
fun ResponsiveCustomText(
    modifier: Modifier = Modifier,
    text: AnnotatedString,
    color: Color,
    textAlign: TextAlign,
    textStyle: TextStyle,
    targetTextSize: TextUnit = textStyle.fontSize,
    maxLines: Int = Int.MAX_VALUE,
    softWrap: Boolean = true,
    overflow: TextOverflow = TextOverflow.Clip,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onClick: (Int) -> Unit = {}
) {
    var textSize: TextUnit by remember { mutableStateOf(targetTextSize) }

    val layoutResult = remember { mutableStateOf<TextLayoutResult?>(null) }
    val pressIndicator = Modifier.pointerInput(onClick) {
        detectTapGestures { pos ->
            layoutResult.value?.let { layoutResult ->
                onClick(layoutResult.getOffsetForPosition(pos))
            }
        }
    }

    Text(
        modifier = modifier.then(pressIndicator),
        text = text,
        color = color,
        textAlign = textAlign,
        maxLines = maxLines,
        softWrap = softWrap,
        overflow = overflow,
        fontFamily = textStyle.fontFamily,
        fontStyle = textStyle.fontStyle,
        fontWeight = textStyle.fontWeight,
        lineHeight = textStyle.lineHeight,
        onTextLayout = { textLayoutResult: TextLayoutResult ->
            layoutResult.value = textLayoutResult
            onTextLayout(textLayoutResult)
            val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1
            if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex)) {
                textSize *= TEXT_SCALE_REDUCTION_INTERVAL
            }
        },
        fontSize = textSize,
    )
}