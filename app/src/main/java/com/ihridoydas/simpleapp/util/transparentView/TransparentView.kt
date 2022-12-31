/*
 * Created by yusuf on 6/20/22, 11:48 AM
 * Copyright (c) 2021 COGNIVISION . All rights reserved.
 */

package com.ihridoydas.simpleapp.util.transparentView

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * 透明のビュー。
 * UIを無効にする目的でメインUI上にサブUIとして表示する。
 */
@Composable
fun TransparentView() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.8f)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .weight(3f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Title",
                fontWeight = FontWeight.Medium,
                color = Color.White,
            )
        }

        Column(
            modifier = Modifier
                .weight(4f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(40.dp),
                color = Color.White,
                strokeWidth = 2.dp
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedButton(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .border(width = 1.dp, shape = RectangleShape, color = Color.White)
                    .size(width = 200.dp, height = 35.dp)
            ) {
                Text(text = "Cancel")
            }
        }
        Spacer(modifier = Modifier.weight(3f))
    }
}

/**
 * デバッグで透明ビューをプレビュー
 */
@Preview
@Composable
fun PreviewTransparentView() {
    TransparentView()
}
