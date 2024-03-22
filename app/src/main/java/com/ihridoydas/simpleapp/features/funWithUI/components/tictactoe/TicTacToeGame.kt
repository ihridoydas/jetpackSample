package com.ihridoydas.simpleapp.features.funWithUI.components.tictactoe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TicTacToeGame(
    modifier: Modifier = Modifier
) {

    var winner by remember { mutableStateOf<Player?>(null) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TicTacToe(
            modifier = Modifier.fillMaxWidth().height(500.dp),
            size = 300.dp,
            onNewRound = { winner = null },
            showWinner = {
                winner = it
            }
        )
        Spacer(modifier = Modifier.height(60.dp))

        winner?.let {
            Text(
                text = "Player ${it.char} win!",
                style = TextStyle(
                    color = it.color,
                    fontSize = 26.sp,
                )
            )
        }

    }
}