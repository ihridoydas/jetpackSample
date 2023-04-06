package com.ihridoydas.simpleapp.util.responsiveUI.component.drawerNavigation.navoption

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import com.ihridoydas.simpleapp.R


fun listOfNavOptionSource() = mutableListOf(
    NavOption(aIcon = Icons.Default.Favorite, "My All Memories"),
    NavOption(aIcon = Icons.Default.Add, "Create new Memory"),
    NavOption(aIcon = Icons.Default.Close, "Memory's Dustbin")
)

fun listOfNavOptionHorizontalSource() = mutableListOf(
    NavOptionHorizontal(aImage = R.drawable.user_one, "With Her"),
    NavOptionHorizontal(aImage = R.drawable.user_one, "Family Diaries"),
    NavOptionHorizontal(aImage = R.drawable.user_one, "College Memories")
)