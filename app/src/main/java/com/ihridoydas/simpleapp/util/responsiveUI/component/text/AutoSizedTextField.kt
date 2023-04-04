package com.ihridoydas.simpleapp.util.responsiveUI.component.text


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontLoader
import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import com.ihridoydas.simpleapp.ui.theme.ThemeColor


@Composable
fun DemoTextField() {
    var text by remember { mutableStateOf("") }

    SimpleAppTheme() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ThemeColor.copy(0.7f))
                .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "AutoSize  TextField", fontWeight = FontWeight.Bold,
                color = Color.White
            )

            AutoSizableTextField(
                value = text,
                onValueChange = { text = it },
                maxLines = 3,
                minFontSize = 10.sp,
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 20.dp)
                    .fillMaxWidth()
                    .height(150.dp)
            )

            Text(
                text = "TextField", fontWeight = FontWeight.Bold,
                color = Color.White
            )

            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                maxLines = 3,
                textStyle = TextStyle(fontSize = 32.sp),
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .height(150.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.White,
                    focusedBorderColor = Color.White,
                    cursorColor = Color.White
                )
            )
        }
    }
}


@Composable
fun AutoSizableTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 32.sp,
    maxLines: Int = Int.MAX_VALUE,
    minFontSize: TextUnit,
    scaleFactor: Float = 0.9f,
) {
    BoxWithConstraints(
        modifier = modifier
    ) {

        var nFontSize = fontSize

        val calculateParagraph = @Composable {
            Paragraph(
                text = value,
                style = TextStyle(fontSize = nFontSize),
                density = LocalDensity.current,
                resourceLoader = LocalFontLoader.current,
                maxLines = maxLines,
                width = with(LocalDensity.current) { maxWidth.toPx() }
            )
        }

        var intrinsics = calculateParagraph()
        with(LocalDensity.current) {
            while ((intrinsics.height.toDp() > maxHeight || intrinsics.didExceedMaxLines) && nFontSize >= minFontSize) {
                nFontSize *= scaleFactor
                intrinsics = calculateParagraph()
            }
        }

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxSize(),
            shape = RoundedCornerShape(8.dp),
            maxLines = maxLines,
            textStyle = TextStyle(fontSize = nFontSize),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                focusedBorderColor = Color.White,
                cursorColor = Color.White
            )
        )
    }
}

@Preview
@Composable
fun AutoSizeTextFieldPreview() {
    DemoTextField()
}