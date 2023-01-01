package com.ihridoydas.simpleapp.ui.screens.homeScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ihridoydas.simpleapp.navigation.HomeScreenSpec
import com.ihridoydas.simpleapp.navigation.ProfileScreenSpec
import com.ihridoydas.simpleapp.util.responsiveUI.dpToSp

@Composable
fun HomeScreen(
    windowSizeClass: WindowSizeClass,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.clickable {
                navController?.navigate(ProfileScreenSpec.requestNavigationRoute()) {
                    popUpTo(HomeScreenSpec.route) {
                        inclusive = true
                    }
                }
            },
            text = "Click To Profile",
            textAlign = TextAlign.Center,
            fontSize = when (windowSizeClass.widthSizeClass) {
                WindowWidthSizeClass.Compact -> dpToSp(12.dp)
                else -> dpToSp(56.dp)
            }
        )
    }

}