/*
 * Created by hridoydas on 2023/02/10
 * Last modified 2/10/23, 10:13 PM
 * Copyright © 2023 Cognivision Inc. All rights reserved.
 */

package com.ihridoydas.simpleapp.util.responsiveUI.component.dialog

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
import com.ihridoydas.simpleapp.ui.theme.md_theme_light_error
import com.ihridoydas.simpleapp.ui.theme.md_theme_light_onPrimaryContainer
import com.ihridoydas.simpleapp.ui.theme.md_theme_light_secondary
import com.ihridoydas.simpleapp.util.responsiveUI.WindowSize
import com.ihridoydas.simpleapp.util.responsiveUI.WindowType
import com.ihridoydas.simpleapp.util.responsiveUI.dpToSp
import com.ihridoydas.simpleapp.util.responsiveUI.rememberWindowSize


@Composable
fun NumberPlateAlertDialog(
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
                    color = md_theme_light_onPrimaryContainer,
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
                color = md_theme_light_onPrimaryContainer,
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
                Divider(Modifier.background(md_theme_light_onPrimaryContainer))
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
                            color = md_theme_light_secondary,
                            fontSize = when (window.width) {
                                WindowType.Normal -> dpToSp(16.dp)
                                else -> dpToSp(14.dp)
                            }
                        )
                    }
                    Divider(
                        modifier = Modifier
                            .background(md_theme_light_onPrimaryContainer)
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
                            color = md_theme_light_error,
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
    NumberPlateAlertDialog(window = rememberWindowSize(),"タイトル","Body.......","キャンセル","削除",{},{})
}