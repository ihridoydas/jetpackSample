package com.ihridoydas.simpleapp.util.responsiveUI.component.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun NoPaddingAlertDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    title: @Composable (() -> Unit)? = null,
    text: @Composable (() -> Unit)? = null,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable (() -> Unit)? = null,
    shape: Shape = MaterialTheme.shapes.medium,
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    properties: DialogProperties = DialogProperties()
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        Surface(
            modifier = modifier,
            shape = shape,
            color = backgroundColor,
            contentColor = contentColor
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                title?.let {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                        val textStyle = MaterialTheme.typography.subtitle1
                        ProvideTextStyle(textStyle, it)
                    }
                }
                text?.let {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                        val textStyle = MaterialTheme.typography.subtitle1
                        ProvideTextStyle(textStyle, it)
                    }
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(all = 8.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        dismissButton?.invoke()
                        confirmButton()
                    }
                }
            }
        }
    }
}

//Use Case in real Project

@Composable
fun MyCustomAlertDialog(openDialog: MutableState<Boolean> = mutableStateOf(true)) {
    if (openDialog.value) {
        NoPaddingAlertDialog(
            title = {
                Surface(
                    color = Color.Blue,
                    contentColor = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Text(
                        text = " Popup Title",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 16.dp),
                    )
                }
            },
            text = {
                Column(Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(horizontal = 8.dp, vertical = 16.dp)
                            .fillMaxWidth(),
                        value = "Alert Dialog content ...",
                        onValueChange = { },
                        label = { Text(text = "Content") },
                    )
                }
            },
            onDismissRequest = { /*TODO*/ },
            confirmButton = {
                Button(onClick = {  openDialog.value = false }) {
                    Text(text = "OK")
                }

            },
            dismissButton = {
                Button(onClick = { openDialog.value = false }) {
                    Text(text = "Cancel")
                }
            }
        )
    }
}

@Preview
@Composable
fun MyCustomAlertDialogDemo() {
    MyCustomAlertDialog()
}