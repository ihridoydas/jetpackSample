/*
 * Created by hridoydas on 2023/02/10
 * Last modified 2/10/23, 10:13 PM
 * Copyright © 2023 Cognivision Inc. All rights reserved.
 */

package com.ihridoydas.simpleapp.util.responsiveUI.component.alert

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ihridoydas.simpleapp.util.responsiveUI.WindowSize
import com.ihridoydas.simpleapp.util.responsiveUI.WindowType
import com.ihridoydas.simpleapp.util.responsiveUI.dpToSp
import com.ihridoydas.simpleapp.util.responsiveUI.rememberWindowSize


@Composable
fun NumberPlateAlertDialog() {
    val window = rememberWindowSize()
    val viewModel: AlertViewModel = viewModel()
    if (viewModel.title.isNotEmpty()) {
        AlertDialog(
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(MaterialTheme.colorScheme.background),
            backgroundColor = MaterialTheme.colorScheme.background,
            properties = DialogProperties(
                dismissOnClickOutside = false,
                dismissOnBackPress = true,
            ),
            onDismissRequest = {
                viewModel.backAction?.let {
                    viewModel.callDismissAction()
                }
            },
            title = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    if (viewModel.title.isNotEmpty()) {
                        Text(
                            text = viewModel.title,
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = when (window.width) {
                                WindowType.Normal -> dpToSp(16.dp)
                                else -> dpToSp(12.dp)
                            }
                        )
                    }

                }
            },
            text = {
                if (viewModel.text.isNotEmpty()) {

                    Text(
                        text = viewModel.text,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        maxLines = Int.MAX_VALUE,
                        style = TextStyle(
                            lineHeight = 1.8.em,
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = false
                            ),
                            fontWeight = FontWeight.Medium,
                            fontSize = when (window.width) {
                                WindowType.Normal -> dpToSp(14.dp)
                                else -> dpToSp(12.dp)
                            },
                            lineHeightStyle = LineHeightStyle(
                                alignment = LineHeightStyle.Alignment.Center,
                                trim = LineHeightStyle.Trim.None
                            )
                        ),
                        overflow = TextOverflow.Visible,
                    )
                }


            },
            buttons = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Divider(Modifier.background(Color.Black))
                    Row(
                        modifier = Modifier.height(IntrinsicSize.Min),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        if (viewModel.button2Text.isNotEmpty()) {
                            Button(
                                onClick = {
                                    viewModel.button2Action()
                                },
                                Modifier
                                    .fillMaxWidth()
                                    .weight(1F),
                                colors = ButtonDefaults.textButtonColors(
                                    containerColor = Color.Transparent,
                                    disabledContentColor = Color.Transparent
                                )
                            ) {
                                Text(
                                    text = viewModel.button2Text,
                                    color = Color.Black,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = when (window.width) {
                                        WindowType.Normal -> dpToSp(16.dp)
                                        else -> dpToSp(14.dp)
                                    }
                                )
                            }
                        }
                        if(viewModel.button1Text.isNotEmpty() && viewModel.button2Text.isNotEmpty()){
                            Divider(
                                modifier = Modifier
                                    .background(Color.Black)
                                    .width(1.dp)
                                    .fillMaxHeight()
                            )
                        }
                        if (viewModel.button1Text.isNotEmpty()) {
                            Button(
                                onClick = {
                                    viewModel.button1Action()
                                },
                                Modifier
                                    .fillMaxWidth()
                                    .weight(1F),
                                colors = ButtonDefaults.textButtonColors(
                                    containerColor = Color.Transparent,
                                    disabledContentColor = Color.Transparent
                                )
                            ) {
                                Text(
                                    text = viewModel.button1Text,
                                    color = Color.Black,
                                    fontSize = when (window.width) {
                                        WindowType.Normal -> dpToSp(14.dp)
                                        else -> dpToSp(12.dp)
                                    }
                                )
                            }
                        }

                    }
                }
            },
        )
    }
}


//UI Dialog Preview
@Composable
fun NumberPlateAlertDialogView(
    window : WindowSize,
    title :String,
    textBody :String,
    button1Text: String,//button1Text
    button2Text: String,//button2Text
    button1Action: () -> Unit,//leftButton
    button2Action: () -> Unit,//rightButton
) {
    AlertDialog(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(MaterialTheme.colorScheme.background),
        backgroundColor = MaterialTheme.colorScheme.background,
        onDismissRequest = { button2Action.invoke() },
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = title,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = when (window.width) {
                        WindowType.Normal -> dpToSp(16.dp)
                        else -> dpToSp(12.dp)
                    }
                )
            }
        },
        text={
            Text(
                text = textBody,
                textAlign = TextAlign.Center,
                color = Color.Black,
                maxLines = Int.MAX_VALUE,
                style = TextStyle(
                    lineHeight = 1.8.em,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
                    fontWeight = FontWeight.Medium,
                    fontSize = when (window.width) {
                        WindowType.Normal -> dpToSp(14.dp)
                        else -> dpToSp(12.dp)
                    },
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Center,
                        trim = LineHeightStyle.Trim.None
                    )
                ),
                overflow = TextOverflow.Visible,
            )

        },
        buttons = {
            Column (modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center){
                Divider(Modifier.background(Color.Black))
                Row(
                    modifier = Modifier.height(IntrinsicSize.Min),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ){

                    Button(
                        onClick = {
                            // キャンセルをタップしたとき
                            button1Action.invoke()
                        },
                        Modifier
                            .fillMaxWidth()
                            .weight(1F),
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = Color.Transparent,
                            disabledContentColor = Color.Transparent
                        )
                    ) {
                        Text(
                            text = button1Text,
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = when (window.width) {
                                WindowType.Normal -> dpToSp(16.dp)
                                else -> dpToSp(14.dp)
                            }
                        )
                    }
                    Divider(
                        modifier = Modifier
                            .background(Color.Black)
                            .width(1.dp)
                            .fillMaxHeight()
                    )
                    Button(
                        onClick = {
                            // 削除ボタン：アラートを表示する
                            button2Action.invoke()
                        },
                        Modifier
                            .fillMaxWidth()
                            .weight(1F),
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = Color.Transparent,
                            disabledContentColor = Color.Transparent
                        )
                    ) {
                        Text(
                            text = button2Text,
                            color = Color.Black,
                            fontSize = when (window.width) {
                                WindowType.Normal -> dpToSp(14.dp)
                                else -> dpToSp(12.dp)
                            }
                        )
                    }
                }
            }
        },
    )
}

@Preview
@Composable
fun PreviewNumberPlateAlertDialog() {
    NumberPlateAlertDialogView(window = rememberWindowSize(),"タイトル","Body.......","キャンセル","次へ",{},{})
}