package com.ihridoydas.simpleapp.features.ticTokToeGame.data

import com.ihridoydas.simpleapp.R


enum class Mark(val markImg: Int) {
    X(R.drawable.x_mark),
    O(R.drawable.o_mark)
}

data class Player(
    val name: String,
    val mark: Mark,
)