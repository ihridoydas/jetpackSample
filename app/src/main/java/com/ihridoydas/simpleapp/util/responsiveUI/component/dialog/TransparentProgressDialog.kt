
package com.ihridoydas.simpleapp.util.responsiveUI.component.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ihridoydas.simpleapp.util.responsiveUI.WindowSize
import com.ihridoydas.simpleapp.util.responsiveUI.WindowType
import com.ihridoydas.simpleapp.util.responsiveUI.component.text.AutoSizeText
import com.ihridoydas.simpleapp.util.responsiveUI.dpToSp
import com.ihridoydas.simpleapp.util.responsiveUI.rememberWindowSize


@Composable
fun TransparentProgressDialog(
    window: WindowSize,
    showDialog: Boolean
) {
        if (showDialog) {
            Column(
                modifier= Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.8f)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AlertDialog(
                    modifier = Modifier
                        .size(200.dp, 230.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    backgroundColor = Color.Transparent,
                    onDismissRequest = { },
                    title = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 5.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AutoSizeText(
                                text = "Title",
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                maxLines = Int.MAX_VALUE,
                                style = TextStyle(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = when (window.width) {
                                        WindowType.Normal -> dpToSp(14.dp)
                                        else -> dpToSp(12.dp)
                                    },
                                ),
                                overflow = TextOverflow.Visible,
                            )
                        }
                    },
                    buttons = {
                        Column(
                            modifier = Modifier.padding(top = 10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                                Row(
                                    modifier = Modifier.padding(all = 10.dp),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    LinearProgressIndicator(
                                        modifier = Modifier
                                            .clip(shape = RoundedCornerShape(2.dp)),
                                        progress = 10f,
                                        color = Color.Blue,
                                        trackColor = Color.Red.copy(alpha = 0.8f)
                                    )
                                }

                                Row(
                                        modifier = Modifier.padding(all = 10.dp),
                                horizontalArrangement = Arrangement.Center
                                ) {
                                    // 送信済み枚数/総画像枚数
                                    Text(
                                        "Progress 100%",
                                        color = Color.White,
                                        fontSize = when (window.width) {
                                            WindowType.Normal -> dpToSp(16.dp)
                                            else -> dpToSp(12.dp)
                                        }
                                    )
                                }
                                


                            Row(
                                modifier = Modifier.padding(all = 0.dp).fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                // インジケーター
                                CircularProgressIndicator(color = Color.Black)
                            }
                            
                                Row(
                                    modifier = Modifier.padding(all = 0.dp),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Button(
                                        modifier = Modifier
                                            .padding(top = 10.dp)
                                            .size(190.dp, 60.dp),
                                        shape = RoundedCornerShape(8.dp),
                                        onClick = {},
                                        border = BorderStroke(2.dp, color = Color.White),
                                        colors = ButtonDefaults.textButtonColors(
                                            containerColor = Color.Transparent,
                                            contentColor = Color.Blue,
                                            disabledContentColor = Color.Transparent
                                        )
                                    ) {
                                        Text(
                                            "Stop",
                                            color = Color.White,
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
                )
            }
        }

}

@Preview(showBackground = true)
@Composable
fun PreviewWorkspaceSendProgressDialog() {

    TransparentProgressDialog(
        window = rememberWindowSize(),
        showDialog = true,
    )
}
