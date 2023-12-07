package com.ihridoydas.simpleapp.widget.glance

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import java.util.Calendar



@Composable
fun DayaGlanceApp() {
    val startTime = Calendar.getInstance().apply {
        set(Calendar.YEAR, 53)
        set(Calendar.MONTH, 9)
        set(Calendar.DAY_OF_MONTH, 7)
        set(Calendar.HOUR_OF_DAY, 7)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
    }
    var differenceTime by remember {
        mutableStateOf(
            Calendar.getInstance().get(Calendar.DAY_OF_YEAR) -
                    startTime.get(Calendar.DAY_OF_YEAR) + 1
        )
    }

    val handler = Handler(Looper.getMainLooper())
    val runnable = object : Runnable {
        override fun run() {
            differenceTime = Calendar.getInstance().get(Calendar.DAY_OF_YEAR) -
                    startTime.get(Calendar.DAY_OF_YEAR) + 1
            handler.postDelayed(this, 1000)
        }
    }

    val uiHandler = LocalUriHandler.current

    LaunchedEffect(key1 = Unit) {
        handler.post(runnable)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
            ) {
                Surface(
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier
                        .width(200.dp)
                        .height(150.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cat),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = differenceTime.toString() + "th Day",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
            ) {
                IconButton(onClick = {
                    uiHandler.openUri("https://www.linkedin.com/in/ihridoydas/")
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.light),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(40.dp)
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                IconButton(onClick = {
                    uiHandler.openUri("https://github.com/ihridoydas")
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.user_one),
                        tint = Color.Unspecified,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                IconButton(onClick = {
                    uiHandler.openUri("https://www.facebook.com/ihridoydas/")
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.instagram),
                        tint = Color.Unspecified,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                IconButton(onClick = {
                    uiHandler.openUri("https://www.instagram.com/ihridoydas/")
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.pizza),
                        tint = Color.Unspecified,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleAppTheme {
        DayaGlanceApp()
    }
}