package com.ihridoydas.simpleapp.util.responsiveUI.component.tabLayout.view


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.ui.theme.seed
import com.ihridoydas.simpleapp.util.responsiveUI.component.appIconChanger.MainScreen
import com.ihridoydas.simpleapp.util.responsiveUI.component.appIconChanger.utils.gregAppIcons
import com.ihridoydas.simpleapp.util.responsiveUI.component.matrixCompose.MatrixText
import com.ihridoydas.simpleapp.util.responsiveUI.component.thumbnailOfStory.StoryThumbnailBasic

@Composable
fun TabScreenOne(tabName: String) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = tabName,
            style = MaterialTheme.typography.h5,
            color = seed,
            fontFamily = FontFamily.Monospace,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )

        MainScreen(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF161D26)),
            icons = gregAppIcons,
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

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "$tabName\n \nthumbnail for a story",
            style = MaterialTheme.typography.h5,
            color = seed,
            fontFamily = FontFamily.Monospace,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )
        StoryThumbnailBasic()
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

        MatrixText()
    }

}