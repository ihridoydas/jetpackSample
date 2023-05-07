package com.ihridoydas.simpleapp.util.responsiveUI.component.canvas.practice

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.R

@Composable
fun ImageWithBadge() {
    val badge = remember {
        mutableStateOf(true)
    }

    Surface(color = Color.DarkGray) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(id = R.drawable.twitter),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(100.dp)
                    .padding(4.dp)
                    .drawWithContent {
                        //draw the image itself
                        this.drawContent()
                        if (badge.value) {
                            //draw the badge over the image
                            this.drawCircle(
                                Color.Red,
                                8.dp.toPx(),
                                Offset(size.width - 16.dp.toPx(), 16.dp.toPx())
                            )
                        }
                    }
                    .clip(RoundedCornerShape(6.dp))
            )
            Spacer(modifier = Modifier.padding(vertical = 40.dp))

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Filled.Notifications,
                    null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(60.dp)
                        .badge(badge.value,6.dp,15.dp,15.dp)
                )

            }

            Button(onClick = {
                badge.value = !badge.value
            }) {
                Text(text = "toggle badge")

            }

        }

    }

}


//Icons And Image Badge Extension
fun Modifier.badge(toggle:Boolean,radius: Dp,topPadding: Dp, rightPadding : Dp,color : Color = Color.Red,) : Modifier {

    return this.drawWithContent {
        //draw the image itself
        this.drawContent()
        if (toggle) {
            //draw the badge over the image
            this.drawCircle(
                color,
                radius.toPx(),
                Offset(size.width - rightPadding.toPx(),topPadding.toPx())
            )
        }
    }
}