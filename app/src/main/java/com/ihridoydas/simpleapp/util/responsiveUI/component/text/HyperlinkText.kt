package com.ihridoydas.simpleapp.util.responsiveUI.component.text


import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.ihridoydas.simpleapp.R

/**
 * Hyperlink表示用のText
 * @param modifier Modifier
 * @param fullText 表示文字列
 * @param linkText リンク部分の文字列リスト
 * @param linkTextColor リンク部分の色指定
 * @param linkTextFontWeight リンク部分のフォント設定
 * @param linkTextDecoration リンク部分のデコレーション設定
 * @param hyperlinks ハイバーリンク情報のリスト
 * @param fontSize フォントサイズ
 */
@Composable
fun HyperlinkText(
    modifier: Modifier = Modifier,
    fullText: String,
    linkText: List<String>,
    linkTextColor: Color = Color.Blue,
    linkTextFontWeight: FontWeight = FontWeight.Medium,
    linkTextDecoration: TextDecoration = TextDecoration.None,
    hyperlinks: List<String>,
    fontSize: TextUnit = TextUnit.Unspecified
) {
    val inlineContentMap = mapOf(
        "urlImage" to InlineTextContent(
            Placeholder(15.sp, 15.sp, PlaceholderVerticalAlign.TextCenter)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.ic_external_link),
                contentDescription = "lock Image"
            )
        }
    )

    val annotatedString = buildAnnotatedString {
        var startIndexValue = 0
        var appendStartIndexValue = 0
        linkText.forEachIndexed { index, link ->
            val startIndex = fullText.indexOf(link, startIndexValue)
            val endIndex = startIndex + link.length
            val otherStr = fullText.substring(startIndexValue, startIndex)
            val linkStr = fullText.substring(startIndex + 1, endIndex - 1)
            append(otherStr)
            append(linkStr)
            val startPos = appendStartIndexValue + otherStr.length + index
            val endPos = startPos + linkStr.length
            addStyle(
                style = SpanStyle(
                    color = linkTextColor,
                    fontSize = fontSize,
                    fontWeight = linkTextFontWeight,
                    textDecoration = linkTextDecoration
                ),
                start = startPos,
                end = endPos
            )
            addStringAnnotation(
                tag = "URL",
                annotation = hyperlinks[index].substring(1, hyperlinks[index].length - 1),
                start = startPos,
                end = endPos
            )
            appendInlineContent(id = "urlImage")
            startIndexValue = endIndex
            appendStartIndexValue = (appendStartIndexValue + otherStr.length + linkStr.length)
        }
        if (fullText.length > startIndexValue) {
            append(fullText.substring(startIndexValue, fullText.length))
        }
        addStyle(
            style = SpanStyle(
                fontSize = fontSize
            ),
            start = 0,
            end = fullText.length
        )
    }

    val uriHandler = LocalUriHandler.current

    CustomClickableText(
        modifier = modifier,
        text = annotatedString,
        onClick = {
            annotatedString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        },
        inlineContent = inlineContentMap
    )
}

@Composable
fun CustomClickableText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle.Default,
    softWrap: Boolean = true,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    inlineContent: Map<String, InlineTextContent> = mapOf(),
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onClick: (Int) -> Unit
) {
    val layoutResult = remember { mutableStateOf<TextLayoutResult?>(null) }
    val pressIndicator = Modifier.pointerInput(onClick) {
        detectTapGestures { pos ->
            layoutResult.value?.let { layoutResult ->
                onClick(layoutResult.getOffsetForPosition(pos))
            }
        }
    }

    BasicText(
        text = text,
        modifier = modifier.then(pressIndicator),
        style = style,
        softWrap = softWrap,
        overflow = overflow,
        maxLines = maxLines,
        onTextLayout = {
            layoutResult.value = it
            onTextLayout(it)
        },
        inlineContent = inlineContent
    )
}
