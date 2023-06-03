package com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.model

import androidx.compose.ui.unit.DpSize
import com.ihridoydas.simpleapp.features.composibleSheep.sheep.model.Sheep

data class GesturesSheepUiState(
    val sheep: Sheep = Sheep(),
    val sheepSize: DpSize = SheepDefaultSize,
)
