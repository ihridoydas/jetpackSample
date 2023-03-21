
package com.ihridoydas.simpleapp.util.responsiveUI.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
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
import com.ihridoydas.simpleapp.util.responsiveUI.WindowType
import com.ihridoydas.simpleapp.util.responsiveUI.dpToSp
import com.ihridoydas.simpleapp.util.responsiveUI.rememberWindowSize

@Composable
fun SingleButtonAlertDialog() {
    val window = rememberWindowSize()

    AlertDialog(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(Color.White),
        backgroundColor = Color.White,
        onDismissRequest = {},
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "title",
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

        },
       //You can use Text in here [text={}]
        buttons = {
            Column(
                modifier = Modifier.fillMaxWidth().clickable {  },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Divider(Modifier.background(Color.Black))

                Row(
                    modifier = Modifier.padding(all = 0.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {},
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Blue,
                            disabledContentColor = Color.Transparent
                        )
                    ) {
                        Text(
                            text = "OK",
                            color = Color.Blue,
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

@Preview(showBackground = true)
@Composable
fun PreviewSingleButtonAlertDialog() {
    SingleButtonAlertDialog()
}
