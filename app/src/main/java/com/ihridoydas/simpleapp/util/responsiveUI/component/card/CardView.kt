package com.ihridoydas.simpleapp.util.responsiveUI.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun CardView() {
    listOfCard()
}

@Composable
fun listOfCard() {
    val cards = Data.cards
    LazyVerticalGrid(columns = GridCells.Fixed(1),
        content = {
            items(cards, itemContent = {
                card(it)
            })
        })
}

@Composable
fun card(card: CardData) {
    Card(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .width(400.dp)
            .height(140.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(10.dp)

    ) {
        Row(
            modifier = Modifier
                .clickable { card.feature }
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            card.color1,
                            card.color2
                        )
                    )
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                card.text,
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

        }

    }
}


