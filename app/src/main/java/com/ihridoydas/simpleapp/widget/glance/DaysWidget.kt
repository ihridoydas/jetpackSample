package com.ihridoydas.simpleapp.widget.glance

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.updateAll
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.ihridoydas.simpleapp.R
import kotlinx.coroutines.launch
import java.util.Calendar

class DaysWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {

        provideContent {
            Content(context)
        }
    }

    @Composable
    private fun Content(context: Context) {
        val startTime by remember {
            mutableStateOf(
                Calendar.getInstance().apply {
                    set(Calendar.YEAR, 53)
                    set(Calendar.MONTH, 9)
                    set(Calendar.DAY_OF_MONTH, 7)
                    set(Calendar.HOUR_OF_DAY, 0)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                }
            )
        }
        var differenceTime by remember {
            mutableStateOf(
                Calendar.getInstance().get(Calendar.DAY_OF_YEAR) -
                        startTime.get(Calendar.DAY_OF_YEAR) + 1
            )
        }

        val handler by remember {
            mutableStateOf(
                Handler(Looper.getMainLooper())
            )
        }
        val runnable by remember {
            mutableStateOf(
                object : Runnable {
                    override fun run() {
                        differenceTime = Calendar.getInstance().get(Calendar.DAY_OF_YEAR) -
                                startTime.get(Calendar.DAY_OF_YEAR) + 1
                        handler.postDelayed(this, 24 * 60 * 60 * 1000)
                    }
                })
        }

        val scope = rememberCoroutineScope()

        LaunchedEffect(key1 = Unit) {
            handler.post(runnable)
        }


        Column(
            horizontalAlignment = Alignment.Horizontal.CenterHorizontally,
            modifier = GlanceModifier
                .fillMaxSize()
                .clickable {
                    scope.launch { DaysWidget().updateAll(context) }
                }
        ) {
            Image(
                provider = ImageProvider(R.drawable.cat),
                contentDescription = null,
                modifier = GlanceModifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .cornerRadius(30.dp),
            )
            Spacer(modifier = GlanceModifier.height(16.dp))

            Box(
                modifier = GlanceModifier
                    .background(Color.White)
                    .padding(5.dp)

            ) {
                Text(
                    text = differenceTime.toString() + "th Day ✌\uD83C\uDDF5\uD83C\uDDF8",
                    style = TextStyle(
                        color = ColorProvider(Color.Black),
                        fontWeight = FontWeight.Bold,
                        fontSize = TextUnit(18f, TextUnitType.Sp),
                        textAlign = TextAlign.Center
                    ),
                )
            }
        }
    }
}