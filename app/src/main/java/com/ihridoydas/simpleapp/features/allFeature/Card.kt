package com.ihridoydas.simpleapp.features.allFeature

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ihridoydas.simpleapp.navigation.animationNavHost.ScreenDestinations

@Composable
fun MyCard(color: List<Color>, name: String,navigate: ()-> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .fillMaxWidth()
            .height(50.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(10.dp)

    ) {
        Row(
            modifier = Modifier
                .clickable {
                   navigate()
                }
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = color
                    )
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                name,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

        }

    }
}


@Composable
fun MyCardTitle(color: List<Color>, name: String,width : Dp = 100.dp) {
    Card(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .width(width)
            .height(25.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(5.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = color
                    )
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                name,
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )

        }

    }
}