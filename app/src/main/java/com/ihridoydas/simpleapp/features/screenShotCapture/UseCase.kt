package com.ihridoydas.simpleapp.features.screenShotCapture

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.glance.LocalContext
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.features.screenShotCapture.capturable.capturable
import com.ihridoydas.simpleapp.features.screenShotCapture.capturable.controller.rememberCaptureController
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import kotlinx.coroutines.launch

//Library Link
//https://github.com/PatilShreyas/Capturable

@OptIn(ExperimentalComposeUiApi::class, ExperimentalComposeApi::class)
@Composable
fun TicketScreen(context: Context) {
    val captureController = rememberCaptureController()
    val uiScope = rememberCoroutineScope()

    // This will hold captured bitmap
    // So that we can demo it
    var ticketBitmap: ImageBitmap? by remember { mutableStateOf(null) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(24.dp)
    ) {
        // The content to be captured ⬇️
        Ticket(modifier = Modifier.capturable(captureController))

        Spacer(modifier = Modifier.size(32.dp))

        // Captures ticket bitmap on click
        Button(
            onClick = {
                uiScope.launch {
                    ticketBitmap = captureController.captureAsync().await()
                }

            }
        ) {
            Text("Preview Ticket Image")
        }

        // When Ticket's Bitmap image is captured, show preview in dialog
        ticketBitmap?.let { bitmap ->
            Dialog(onDismissRequest = { }) {
                Column(
                    modifier = Modifier
                        .background(LightGray)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Preview of Ticket image \uD83D\uDC47")
                    Spacer(Modifier.size(16.dp))
                    Image(
                        bitmap = bitmap,
                        contentDescription = "Preview of ticket"
                    )
                    Spacer(Modifier.size(4.dp))
                    Button(onClick = {
                        //save image from bitmap
                        saveBitmapToGallery(context = context,bitmap.asAndroidBitmap())
                    }) {
                        Text("Save Image")
                    }
                    Spacer(modifier = Modifier.size(32.dp))
                    Button(onClick = { ticketBitmap = null }) {
                        Text("Close Preview")
                    }
                }
            }
        }
    }
}

@Composable
fun Ticket(modifier: Modifier) {
    Card(modifier = modifier) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BookingConfirmedContent()
            MovieTitle()
            Spacer(modifier = Modifier.size(32.dp))
            BookingDetail()
            Spacer(modifier = Modifier.size(32.dp))
            BookingQRCode()
        }
    }
}

@Composable
fun BookingConfirmedContent() {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Booking Confirmed",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.weight(0.8f)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_check_circle_24),
            contentDescription = "Successfully booked",
            modifier = Modifier
                .weight(0.2f)
                .size(128.dp)
        )
    }
}

@Composable
fun MovieTitle() {
    Text(
        "Jetpack Compose - The Movie",
        style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold)
    )
}

@Composable
fun BookingDetail() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Column {
            Text("Sat, 1 Jan", style = MaterialTheme.typography.caption)
            Text("12:45 PM", style = MaterialTheme.typography.subtitle2)
        }

        Column {
            Text("SCREEN", style = MaterialTheme.typography.caption)
            Text("JET 01", style = MaterialTheme.typography.subtitle2)
        }

        Column {
            Text("SEATS", style = MaterialTheme.typography.caption)
            Text("J1, J2, J3", style = MaterialTheme.typography.subtitle2)
        }
    }
}

@Composable
fun BookingQRCode() {
    Text(
        "----- SCAN QR CODE AT CINEMA -----",
        style = MaterialTheme.typography.overline
    )
    Icon(
        painter = painterResource(R.drawable.ic_baseline_qr_code_24),
        contentDescription = "Success",
        modifier = Modifier.size(128.dp)
    )

    Text("Booking ID: JETPACK0000012345", style = MaterialTheme.typography.caption)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleAppTheme {
        TicketScreen(LocalContext.current)
    }
}