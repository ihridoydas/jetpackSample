
package com.ihridoydas.simpleapp.util.permission

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import com.ihridoydas.simpleapp.util.responsiveUI.WindowSize
import com.ihridoydas.simpleapp.util.responsiveUI.WindowType
import com.ihridoydas.simpleapp.util.responsiveUI.dpToSp
import com.ihridoydas.simpleapp.util.responsiveUI.rememberWindowSize

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

    AlertDialog(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(Color.White),
        backgroundColor = Color.White,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        ),
        onDismissRequest = {onDismiss()}, //戻るボタン→ OnBackPress
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    maxLines = Int.MAX_VALUE,
                    style = TextStyle(
                        lineHeight = 1.4.em,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        ),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = when (window.width) {
                            WindowType.Normal -> dpToSp(16.dp)
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
        text = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = message,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        maxLines = Int.MAX_VALUE,
                        style = TextStyle(
                            lineHeight = 1.6.em,
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = false
                            ),
                            fontWeight = FontWeight.Normal,
                            fontSize = when (window.width) {
                                WindowType.Normal -> dpToSp(12.dp)
                                else -> dpToSp(10.dp)
                            },
                            lineHeightStyle = LineHeightStyle(
                                alignment = LineHeightStyle.Alignment.Center,
                                trim = LineHeightStyle.Trim.None
                            )
                        ),
                        overflow = TextOverflow.Visible,
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.svg_camera_and_media_image_access_permission_guide_image_android),
                            contentDescription = "Camera and media access permission guide image",
                            modifier = Modifier.size(width = 200.dp, height = 80.dp),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }
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
                    androidx.compose.material3.Button(
                        onClick = { onDismiss() },
                        Modifier
                            .fillMaxWidth()
                            .weight(1F),
                        colors = androidx.compose.material3.ButtonDefaults.textButtonColors(
                            containerColor = Color.Transparent,
                            disabledContentColor = Color.Transparent
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.cancel),
                            color = Color.Blue,
                            fontWeight = FontWeight.Normal,
                            fontSize = when (window.width) {
                                WindowType.Normal -> dpToSp(18.dp)
                                else -> dpToSp(15.dp)
                            }
                        )
                    }
                    Divider(
                        modifier = Modifier
                            .background(Color.Black)
                            .width(1.dp)
                            .fillMaxHeight()
                    )
                    androidx.compose.material3.Button(
                        onClick = { onSettingsTapped() },
                        Modifier
                            .fillMaxWidth()
                            .weight(1F),
                        colors = androidx.compose.material3.ButtonDefaults.textButtonColors(
                            containerColor = Color.Transparent,
                            disabledContentColor = Color.Transparent
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.settings),
                            color = Color.Blue,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = when (window.width) {
                                WindowType.Normal -> dpToSp(18.dp)
                                else -> dpToSp(15.dp)
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
fun ShowGotoSettingsDialogPreview() {
    SimpleAppTheme() {
        ShowGotoSettingsDialog(
            window = rememberWindowSize(),
            title =stringResource(id = R.string.title_camera_and_photo_permissions_dialog),
            message = stringResource(id = R.string.camera_and_photo_permissions_dialog),
            onSettingsTapped = {},
            onDismiss = {})
    }
}
