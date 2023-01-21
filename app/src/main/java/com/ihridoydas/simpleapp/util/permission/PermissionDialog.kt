
package com.ihridoydas.simpleapp.util.permission

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.ihridoydas.simpleapp.util.responsiveUI.WindowSize
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.util.responsiveUI.WindowType
import com.ihridoydas.simpleapp.util.responsiveUI.dpToSp

/**
 * アプリ設定画面へ遷移するダイアログを表示
 * Settingブタン→App Info に行きます。
 **/

@Composable
fun ShowGotoSettingsDialog(
    window: WindowSize,
    title: String = stringResource(id = R.string.title_camera_and_photo_permissions_dialog),
    message: String = stringResource(id = R.string.camera_and_photo_permissions_dialog),
    onSettingsTapped: () -> Unit,
    onDismiss: () -> Unit
) {

    Dialog(
        onDismissRequest = {/* Don't */ },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = 8.dp
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(top = 20.dp, start = 10.dp, end = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = title,
                    color= Color.Black,
                    fontSize = when (window.width) {
                        WindowType.Normal -> dpToSp(18.dp)
                        else -> dpToSp(15.dp)
                    },
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                Text(
                    textAlign = TextAlign.Center,
                    text = message,
                    color= Color.Black,
                    fontWeight = FontWeight.Medium,
                    fontSize = when (window.width) {
                        WindowType.Normal -> dpToSp(14.dp)
                        else -> dpToSp(12.dp)
                    })

                //写真使うため
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(100.dp)
//                                .padding(5.dp)
//                                .background(Color.Transparent),
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.Center,
//
//                            ) {
//
//                            Image(
//                                painter = painterResource(id = R.drawable.ic_logo),
//                                contentDescription = "For Using Image",
//                                modifier = Modifier.fillMaxSize(),
//                                contentScale = ContentScale.FillWidth
//                            )
//                        }

                Row(Modifier.padding(top = 10.dp)) {
                    Button(
                        onClick = { onDismiss() },
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.cancel),
                            color = Color.Black,
                            fontSize = when (window.width) {
                                WindowType.Normal -> dpToSp(16.dp)
                                else -> dpToSp(12.dp)
                            }
                        )
                    }

                    Button(
                        onClick = { onSettingsTapped() },
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.settings),
                            color = Color.White,
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
}
