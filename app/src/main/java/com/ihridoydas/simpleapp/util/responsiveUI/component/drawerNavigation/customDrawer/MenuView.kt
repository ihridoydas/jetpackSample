package com.ihridoydas.simpleapp.util.responsiveUI.component.drawerNavigation.customDrawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.ui.theme.md_theme_light_onPrimary
import com.ihridoydas.simpleapp.ui.theme.md_theme_light_onPrimaryContainer
import com.ihridoydas.simpleapp.ui.theme.md_theme_light_primary
import com.ihridoydas.simpleapp.util.responsiveUI.WindowSize
import com.ihridoydas.simpleapp.util.responsiveUI.WindowType
import com.ihridoydas.simpleapp.util.responsiveUI.dpToSp
import com.ihridoydas.simpleapp.util.responsiveUI.rememberWindowSize

@Composable
fun MenuView(
    window: WindowSize,
    closeTap: () -> Unit,
    settingsTap: () -> Unit,
    faqTap: () -> Unit,
    contactTap: () -> Unit,
    privacyPolicyTap: () -> Unit,
    termsOfUseTap: () -> Unit,
    logOutTap: () -> Unit
) {

    Box(
        modifier = Modifier
            .requiredWidth(220.dp)
            .requiredHeight(400.dp)
            .offset(x = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .width((220).dp)
            // .align(alignment = Alignment.TopStart)
        ) {
            Column(
                modifier = Modifier
                    .width(220.dp)
                    .fillMaxHeight()
                    .padding(0.dp)
                    .background(color = md_theme_light_onPrimary)
                    .clickable(enabled = false, null, null) {}
            ) {
                // ロゴと閉じるボタン
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                        .padding(start = 15.dp, top = 20.dp, bottom = 0.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(width = 106.dp, height = 20.19.dp)
                            .align(alignment = Alignment.CenterVertically),
                        colorFilter = ColorFilter.lighting(md_theme_light_primary, md_theme_light_onPrimaryContainer)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.close_bt_inactive),
                        contentDescription = "Close",
                        modifier = Modifier
                            .size(width = 44.dp, height = 44.dp)
                            .padding(end = 10.dp)
                            .align(alignment = Alignment.CenterVertically)
                            .clickable(onClick = closeTap),
                        colorFilter = ColorFilter.lighting(md_theme_light_primary, md_theme_light_onPrimaryContainer)
                    )
                }
                Spacer(modifier = Modifier.padding(top = 30.dp))

                Row( modifier = Modifier
                    .width((220).dp)) {

                    Column(
                        modifier = Modifier
                            .width(220.dp)
                            .padding(start = 15.dp)
                            .fillMaxHeight()

                    ) {
                        // 設定
                        SideMenuRow(
                            window,
                            modifier = Modifier,
                            iconPainter = painterResource(id = R.drawable.ic_settingicon),
                            contentDescription = "Setting",
                            menuText = stringResource(id = R.string.settings),
                            onClick = settingsTap
                        )

                        Spacer(modifier = Modifier.padding(top = 2.dp))
                        // よくあるご質問
                        SideMenuRow(
                            window,
                            modifier = Modifier,
                            iconPainter = painterResource(id = R.drawable.ic_helpicon),
                            contentDescription = "QA",
                            menuText = "QA",
                            onClick = faqTap
                        )

                        Spacer(modifier = Modifier.padding(top = 2.dp))
                        // お問い合せ
                        SideMenuRow(
                            window,
                            modifier = Modifier,
                            iconPainter = painterResource(id = R.drawable.ic_mailicon),
                            contentDescription = "otoiawase",
                            menuText = "Contact",
                            onClick = contactTap
                        )

                        Spacer(modifier = Modifier.padding(top = 2.dp))
                        // プライバシーポリシー
                        SideMenuRow(
                            window,
                            modifier = Modifier,
                            iconPainter = painterResource(id = R.drawable.ic_fileicon),
                            contentDescription = "privacy",
                            menuText = "Privacy",
                            onClick = privacyPolicyTap
                        )

                        Spacer(modifier = Modifier.padding(top = 2.dp))
                        // 利用規約
                        SideMenuRow(
                            window,
                            modifier = Modifier,
                            iconPainter = painterResource(id = R.drawable.ic_fileicon),
                            contentDescription = "kiyaku",
                            menuText = "File",
                            onClick = termsOfUseTap
                        )

                        Spacer(modifier = Modifier.padding(top = 2.dp))
                        // ログアウト
                        SideMenuRow(
                            window,
                            modifier = Modifier,
                            iconPainter = painterResource(id = R.drawable.ic_logouticon),
                            contentDescription = "logout",
                            menuText = "LogOut",
                            onClick = logOutTap
                        )

                    }
                }
            }
        }
    }
}

@Composable
fun SideMenuRow(
    window: WindowSize,
    modifier: Modifier = Modifier,
    iconPainter: Painter,
    contentDescription: String,
    menuText: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(38.dp)
            .clickable(onClick = onClick)
            .padding(start = 10.dp, top = 5.dp, bottom = 5.dp, end = 10.dp)
    ) {
        Image(
            painter = iconPainter,
            contentDescription = contentDescription,
            modifier = Modifier
                .width(19.dp)
                .height(19.dp)
                .align(alignment = Alignment.CenterVertically)
        )
        Text(
            text = menuText,
            color = md_theme_light_onPrimaryContainer,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
                .align(alignment = Alignment.CenterVertically),
            fontSize = when (window.width) {
                WindowType.Normal -> dpToSp(15.dp)
                else -> dpToSp(13.dp)
            },
        )
    }
}

@Preview
@Composable
fun SideMenuPreview() {
    MenuView(
        window = rememberWindowSize(),
        closeTap = {},
        settingsTap = {},
        faqTap = {},
        contactTap = {},
        privacyPolicyTap = {},
        termsOfUseTap = {},
        logOutTap = {}
    )
}
