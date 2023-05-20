package com.ihridoydas.simpleapp.util.responsiveUI.component.animations.mapAnimation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.media3.common.util.Util.contains
import com.ihridoydas.simpleapp.BuildConfig

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapAnimationView(onBackPress: ()-> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Map Animation(Need Map API key)") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackPress()
                        },
                        modifier = Modifier
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
        drawerShape = RoundedCornerShape(topEnd = 23.dp, bottomEnd = 23.dp),
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .padding(vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                fun checkStringContainsValue( value: String): Boolean {
                    return true
                }
                val API_key = BuildConfig.MAPS_API_KEY
                if(checkStringContainsValue(value = API_key)){
                    MapApp()
                }else{
                    ShowErrorApp()
                }
            }
        }
    )
}