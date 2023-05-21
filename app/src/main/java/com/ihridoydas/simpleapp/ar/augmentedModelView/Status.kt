package com.ihridoydas.simpleapp.ar.augmentedModelView

import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.ihridoydas.simpleapp.R

@Composable
fun StatusOfScreen(status : String) {
    Column(modifier = Modifier.fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally) {
        val reasonCodeTitleAndImage: TitleAndImage = when (status) {
            in "NONE" -> TitleAndImage(
                title = "",
                image = 1
            )
            in "BAD_STATE" -> TitleAndImage(
                title = stringResource(id = R.string.sceneview_bad_state_message),
                image = R.drawable.logo
            )
            in "INSUFFICIENT_LIGHT" -> TitleAndImage(
                title = stringResource(id =if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.R) {
                    R.string.sceneview_insufficient_light_message
                } else {
                    R.string.sceneview_insufficient_light_android_s_message
                }),
                image = R.drawable.logo
            )
            in "EXCESSIVE_MOTION" -> TitleAndImage(
                title = stringResource(id = R.string.sceneview_excessive_motion_message),
                image = R.drawable.logo
            )
            in "INSUFFICIENT_FEATURES" -> TitleAndImage(
                title = stringResource(id = R.string.sceneview_insufficient_features_message),
                image = R.drawable.logo
            )
            in "CAMERA_UNAVAILABLE" -> TitleAndImage(
                title = stringResource(id = R.string.sceneview_camera_unavailable_message),
                image = R.drawable.logo
            )
            else -> {
                TitleAndImage(
                    title = "",
                    image = 0
                )
            }
        }

        //TODO UI Design (TransparentView)
        Text(
            text = reasonCodeTitleAndImage.title,
            modifier = Modifier,
            style = TextStyle(
                color = Color.White,
                textAlign = TextAlign.Center
            )
        )
    }

}

data class TitleAndImage(val title: String, val image: Int)