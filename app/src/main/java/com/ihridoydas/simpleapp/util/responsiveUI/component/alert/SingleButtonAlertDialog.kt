/*
 * Created by hridoydas on 2023/02/13
 * Last modified 2/13/23, 3:55 PM
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ihridoydas.simpleapp.util.responsiveUI.WindowType
import com.ihridoydas.simpleapp.util.responsiveUI.dpToSp
import com.ihridoydas.simpleapp.util.responsiveUI.rememberWindowSize

@Composable
fun SingleButtonAlertDialog() {
    val window = rememberWindowSize()
    val viewModel: AlertViewModel = viewModel()

    if (viewModel.title.isNotEmpty()) {
        AlertDialog(
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(MaterialTheme.colorScheme.background),
            backgroundColor = MaterialTheme.colorScheme.background,
            onDismissRequest = {
                viewModel.backAction?.let {
                    viewModel.callDismissAction()
                }
            },
            title = {
                if (viewModel.title.isNotEmpty()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = viewModel.title,
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            maxLines = Int.MAX_VALUE,
                            style = TextStyle(
                                lineHeight = 1.8.em,
                                platformStyle = PlatformTextStyle(
                                    includeFontPadding = false
                                ),
                                fontWeight = FontWeight.SemiBold,
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
                }

            },
            buttons = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Divider(Modifier.background(Color.Black))

                    Row(
                        modifier = Modifier.padding(all = 0.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        if (viewModel.button1Text.isNotEmpty()) {
                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    viewModel.button1Action()
                                },
                                colors = ButtonDefaults.textButtonColors(
                                    containerColor = Color.Transparent,
                                    contentColor = Color.Blue,
                                    disabledContentColor = Color.Transparent
                                )
                            ) {
                                Text(
                                    text = viewModel.button1Text,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = when (window.width) {
                                        WindowType.Normal -> dpToSp(16.dp)
                                        else -> dpToSp(12.dp)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSingleButtonAlertDialog() {
    SingleButtonAlertDialog()
}
