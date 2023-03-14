package com.ihridoydas.simpleapp.util.responsiveUI.component.textField

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NumberPlateBasicCustomTextField(
    value: String = "",
    onTextChanged: (String) -> Unit = {},
    singleLine: Boolean = false,
    cursorBrush: SolidColor = SolidColor(Color.White),
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    textStyle: TextStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
    onFocusChanged: (FocusState) -> Unit = {},
    modifier: Modifier
) {
    BasicTextField(
        value = value,
        onValueChange = {
            onTextChanged(it)
        },
        singleLine = singleLine,
        cursorBrush = cursorBrush,
        keyboardOptions = keyboardOptions,
        textStyle = textStyle,
        modifier = modifier
            .fillMaxHeight(0.15f)
            .clip(shape = RoundedCornerShape(10.dp))
            .onFocusChanged {
                onFocusChanged(it)
            },

        decorationBox = { innerTextField ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                innerTextField()
            }
        }
    )
}

@Preview
@Composable
fun CustomFieldPreview() {
    val focusRequesterFree: FocusRequester

    NumberPlateBasicCustomTextField(
        value = "Hello",
        onTextChanged = {
            //remember value == it
        },
        //singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Center,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        ),
        onFocusChanged = {
            //Check is focus or not
        },
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            //.focusRequester(), //when use focus requester
    )
}


//Use Like this

