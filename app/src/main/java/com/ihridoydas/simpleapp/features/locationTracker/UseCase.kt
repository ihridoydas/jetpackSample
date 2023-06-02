package com.ihridoydas.simpleapp.features.locationTracker


import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.ui.MainActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationTracker(
    onBackPress: ()-> Unit,
    activity: MainActivity,
    context: Context,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(Color.Black),
                title = { Text(text = "Location Tracker Background" , style = TextStyle(color = Color.White)) },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackPress()
                        },
                        modifier = Modifier
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White )
                    }
                },
            )
        },
        drawerShape = RoundedCornerShape(topEnd = 23.dp, bottomEnd = 23.dp),
        content = {
            Column(
                modifier = Modifier
                    .padding(it),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "🌍 Location Tracker Background Service.!!",
                        fontSize = MaterialTheme.typography.h6.fontSize
                    )
                    Spacer(modifier = Modifier.height(46.dp))

                    Button(onClick = {
                        if (LocationService.isMyServiceRunning) {
                            Toast.makeText(
                                activity,
                                "Service Already Running 😪😪...",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@Button
                        }
                        Intent(context.applicationContext, LocationService::class.java).apply {
                            action = LocationService.ACTION_START
                            activity.startService(this)//will send the action and Intent to service
                        }
                    }) {
                        Text(
                            fontSize = MaterialTheme.typography.h6.fontSize,
                            text = if (LocationService.isMyServiceRunning) "Service Started" else "Start 😇"
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = {
                        if (!LocationService.isMyServiceRunning) {
                            Toast.makeText(
                                activity,
                                "Service Not Running...",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@Button
                        }
                        Intent(context.applicationContext, LocationService::class.java).apply {
                            action = LocationService.ACTION_STOP
                            activity.startService(this)//will send the action and Intent to service
                        }
                    }) {
                        Text(fontSize = MaterialTheme.typography.h6.fontSize, text = "Stop 😅")
                    }
                }
            }
        }
    )
}