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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ihridoydas.simpleapp.util.responsiveUI.WindowType
import com.ihridoydas.simpleapp.util.responsiveUI.dpToSp
import com.ihridoydas.simpleapp.util.responsiveUI.rememberWindowSize


/**
 * Created by DavidA on 2022/07/06.
 * Copyright © 2022 Cognivision inc. All rights reserved.
 */

/**
 * Alert modal with title, text and buttons
 * @see AlertViewModel.show to show the alert
 */
@Composable
fun Alert(isPreview: Boolean = false) {
    if (isPreview) return
    val viewModel: AlertViewModel = viewModel()
    // window Size
    val window = rememberWindowSize()

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
            title ={
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (viewModel.title.isNotEmpty()) {
                            Text(
                                text = viewModel.title,
                                textAlign = TextAlign.Center,
                                color = Color.Black,
                                fontWeight = FontWeight.SemiBold,
                                style = TextStyle(
                                    lineHeight = 1.5.em,
                                    platformStyle = PlatformTextStyle(
                                        includeFontPadding = false
                                    ),
                                    fontSize = when (window.width) {
                                        WindowType.Normal -> dpToSp(18.dp)
                                        else -> dpToSp(15.dp)
                                    },
                                    lineHeightStyle = LineHeightStyle(
                                        alignment = LineHeightStyle.Alignment.Center,
                                        trim = LineHeightStyle.Trim.None
                                    )
                            ))

                    } else {
                        null
                    }
                }

            },

            text ={
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (viewModel.text.isNotEmpty()) {

                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = viewModel.text,
                                    textAlign = TextAlign.Center,
                                    color = Color.Black,
                                    maxLines = Int.MAX_VALUE,
                                    style = TextStyle(
                                        lineHeight = 1.5.em,
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
                    } else {
                        null
                    }
                }
            },

            buttons = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Divider(Modifier.background(Color.Black))
                    if (viewModel.button1Text.isNotEmpty()) {
                        Button(
                            modifier = Modifier
                                .fillMaxWidth(),
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
                                viewModel.button1Text,
                                fontSize = when (window.height) {
                                    WindowType.Normal -> dpToSp(18.dp)
                                    else -> dpToSp(16.dp)
                                }
                            )
                        }
                    }
                    if (viewModel.button2Text.isNotEmpty()) {
                        Divider(Modifier.background(Color.Black))
                        Button(
                            modifier = Modifier
                                .fillMaxWidth(),
                            onClick = {
                                viewModel.button2Action()
                            },
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = Color.Transparent,
                                contentColor = Color.Blue,
                                disabledContentColor = Color.Transparent
                            )
                        ) {
                            Text(
                                viewModel.button2Text,
                                fontSize = when (window.height) {
                                    WindowType.Normal -> dpToSp(18.dp)
                                    else -> dpToSp(16.dp)
                                }
                            )
                        }
                    }
                    if (viewModel.button3Text.isNotEmpty()) {
                        Divider(Modifier.background(Color.Black))
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                viewModel.button3Action()
                            },
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = Color.Transparent,
                                contentColor = Color.Blue,
                                disabledContentColor = Color.Transparent
                            )
                        ) {
                            Text(
                                viewModel.button3Text,
                                fontSize = when (window.height) {
                                    WindowType.Normal -> dpToSp(18.dp)
                                    else -> dpToSp(16.dp)
                                }
                            )
                        }
                    }
                }
            }
        )
    }
}
