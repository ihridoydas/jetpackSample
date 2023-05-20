package com.ihridoydas.simpleapp.util.responsiveUI.component.animations.themePickerAnimations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun ThemePickerApp(onBackPress: ()-> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(Color.White),
                title = { Text(text = "Theme Picker Animations" , style = TextStyle(color = Color.Black)) },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackPress()
                        },
                        modifier = Modifier
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black )
                    }
                },
            )
        },
        drawerShape = RoundedCornerShape(topEnd = 23.dp, bottomEnd = 23.dp),
        content = {
            Column(
                modifier = Modifier
                    .padding(it),
            ) {
                ThemePickerAnimationApp()
            }
        }
    )
}