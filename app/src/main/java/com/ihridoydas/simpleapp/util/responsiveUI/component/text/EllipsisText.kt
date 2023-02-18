package com.ihridoydas.simpleapp.util.responsiveUI.component.text


import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun MiddleEllipsisText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    softWrap: Boolean = true,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current,
) {
    // some letters, like "r", will have less width when placed right before "."
    // adding a space to prevent such case
    val layoutText = remember(text) { "$text $ellipsisText" }
    val textLayoutResultState = remember(layoutText) {
        mutableStateOf<TextLayoutResult?>(null)
    }
    SubcomposeLayout(modifier) { constraints ->
        // result is ignored - we only need to fill our textLayoutResult
        subcompose("measure") {
            androidx.compose.material3.Text(
                text = layoutText,
                color = color,
                fontSize = fontSize,
                fontStyle = fontStyle,
                fontWeight = fontWeight,
                fontFamily = fontFamily,
                letterSpacing = letterSpacing,
                textDecoration = textDecoration,
                textAlign = textAlign,
                lineHeight = lineHeight,
                softWrap = softWrap,
                maxLines = 1,
                onTextLayout = { textLayoutResultState.value = it },
                style = style,
            )
        }.first().measure(Constraints())
        // to allow smart cast
        val textLayoutResult = textLayoutResultState.value
            ?: // shouldn't happen - onTextLayout is called before subcompose finishes
            return@SubcomposeLayout layout(0, 0) {}
        val placeable = subcompose("visible") {
            val finalText = remember(text, textLayoutResult, constraints.maxWidth) {
                if (text.isEmpty() || textLayoutResult.getBoundingBox(text.indices.last).right <= constraints.maxWidth) {
                    // text not including ellipsis fits on the first line.
                    return@remember text
                }

                val ellipsisWidth = layoutText.indices.toList()
                    .takeLast(ellipsisCharactersCount)
                    .let widthLet@{ indices ->
                        // fix this bug: https://issuetracker.google.com/issues/197146630
                        // in this case width is invalid
                        for (i in indices) {
                            val width = textLayoutResult.getBoundingBox(i).width
                            if (width > 0) {
                                return@widthLet width * ellipsisCharactersCount
                            }
                        }
                        // this should not happen, because
                        // this error occurs only for the last character in the string
                        throw IllegalStateException("all ellipsis chars have invalid width")
                    }
                val availableWidth = constraints.maxWidth - ellipsisWidth
                val startCounter = BoundCounter(text, textLayoutResult) { it }
                val endCounter = BoundCounter(text, textLayoutResult) { text.indices.last - it }

                while (availableWidth - startCounter.width - endCounter.width > 0) {
                    val possibleEndWidth = endCounter.widthWithNextChar()
                    if (
                        startCounter.width >= possibleEndWidth
                        && availableWidth - startCounter.width - possibleEndWidth >= 0
                    ) {
                        endCounter.addNextChar()
                    } else if (availableWidth - startCounter.widthWithNextChar() - endCounter.width >= 0) {
                        startCounter.addNextChar()
                    } else {
                        break
                    }
                }
                startCounter.string.trimEnd() + ellipsisText + endCounter.string.reversed().trimStart()
            }
            androidx.compose.material3.Text(
                text = finalText,
                color = color,
                fontSize = fontSize,
                fontStyle = fontStyle,
                fontWeight = fontWeight,
                fontFamily = fontFamily,
                letterSpacing = letterSpacing,
                textDecoration = textDecoration,
                textAlign = textAlign,
                lineHeight = lineHeight,
                softWrap = softWrap,
                onTextLayout = onTextLayout,
                style = style,
            )
        }[0].measure(constraints)
        layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    }
}

private const val ellipsisCharactersCount = 3
private const val ellipsisCharacter = '.'
private val ellipsisText = List(ellipsisCharactersCount) { ellipsisCharacter }.joinToString(separator = "")

private class BoundCounter(
    private val text: String,
    private val textLayoutResult: TextLayoutResult,
    private val charPosition: (Int) -> Int,
) {
    var string = ""
        private set
    var width = 0f
        private set

    private var _nextCharWidth: Float? = null
    private var invalidCharsCount = 0

    fun widthWithNextChar(): Float =
        width + nextCharWidth()

    private fun nextCharWidth(): Float =
        _nextCharWidth ?: run {
            var boundingBox: Rect
            // invalidCharsCount fixes this bug: https://issuetracker.google.com/issues/197146630
            invalidCharsCount--
            do {
                boundingBox = textLayoutResult
                    .getBoundingBox(charPosition(string.count() + ++invalidCharsCount))
            } while (boundingBox.right == 0f)
            _nextCharWidth = boundingBox.width
            boundingBox.width
        }

    fun addNextChar() {
        string += text[charPosition(string.count())]
        width += nextCharWidth()
        _nextCharWidth = null
    }
}




@Preview(showBackground = true)
@Composable
private fun PreviewMiddleEllipsisText() {
    MaterialTheme {
        Card(
            Modifier.padding(16.dp),
            border = BorderStroke(1.dp, MaterialTheme.colors.onSurface),
        ) {
            MiddleEllipsisText("vjhgvjhvjhcvjhcjgchgchgchgfxhfxgfxzgdfxzfdxxgfxgfxgfxgfxgfxgf",
            modifier = Modifier.padding(5.dp))
        }
    }
}


@Composable
private fun LocationLabelArea(locations: String) {
    Row(
        modifier = Modifier
            .height(48.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var additional by remember { mutableStateOf(10) }

        Box(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = locations,
                modifier = Modifier.fillMaxWidth(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                onTextLayout = { textLayoutResult ->
                    if (textLayoutResult.hasVisualOverflow) {
                        val lineEndIndex = textLayoutResult.getLineEnd(
                            lineIndex = 0,
                            visibleEnd = true
                        )
                        additional = locations
                            .substring(lineEndIndex)
                            .count { it == ',' }
                    }
                },
            )
        }

        if (additional != 0) {
            CounterBadge(additionalLocationsCount = additional)
        }
    }
}

@Composable
private fun CounterBadge(additionalLocationsCount: Int) {
    Box(
        modifier = Modifier
            .padding(start = 8.dp)
            .size(32.dp)
            .clip(RoundedCornerShape(100))
            .background(MaterialTheme.colors.onBackground.copy(alpha = 0.3f))
    ) {
        Text(
            text = "+$additionalLocationsCount",
            overflow = TextOverflow.Visible,
            maxLines = 1,
            modifier = Modifier
                .align(Alignment.Center),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    MaterialTheme {
        Card(
            Modifier.padding(16.dp),
            border = BorderStroke(1.dp, MaterialTheme.colors.onSurface),
        ) {
            LocationLabelArea(
                locations = "Some location, Some, Some location, Some location, Some locationSome location, Some, Some location, Some location, Some location"
            )
        }
    }
}


@Composable
fun ExpandableText(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Ellipsis,
) {
    var isEllipsized by remember { mutableStateOf(false) }
    var currentMaxLines by remember { mutableStateOf(maxLines) }

    LaunchedEffect(isEllipsized) {
        if (!isEllipsized) currentMaxLines = Int.MAX_VALUE
    }

    Text(
        text = text,
        modifier = modifier
            .clickable(enabled = isEllipsized) { isEllipsized = !isEllipsized }
            .animateContentSize(),
        maxLines = currentMaxLines,
        overflow = overflow,
        onTextLayout = { result ->
            isEllipsized = result.isLineEllipsized(result.lineCount - 1)
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewExpandableText() {
    MaterialTheme {
        Card(
            Modifier.padding(16.dp),
            border = BorderStroke(1.dp, MaterialTheme.colors.onSurface),
        ) {
            ExpandableText(
                "hghjhgjhgigvhkjgkfkfdtydyrtstrdsthrdstrdtdyjtdfytdfytdytdyjtdytdytdytdfyjtdfyjtdytdfyjtdyjtdfyjtdjytdjydfydyt",
                modifier = Modifier.padding(5.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}