package com.ihridoydas.simpleapp.util.responsiveUI.component.tabLayout.view


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.ihridoydas.simpleapp.ui.theme.seed

@Composable
fun TabScreenOne(tabName: String) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = tabName,
            style = MaterialTheme.typography.h5,
            color = seed,
            fontFamily = FontFamily.Monospace,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )
    }

}

@Composable
fun TabScreenTwo(tabName: String) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = tabName,
            style = MaterialTheme.typography.h5,
            color = seed,
            fontFamily = FontFamily.Monospace,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )
    }

}

@Composable
fun TabScreenThree(tabName: String) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = tabName,
            style = MaterialTheme.typography.h5,
            color = seed,
            fontFamily = FontFamily.Monospace,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )
    }

}