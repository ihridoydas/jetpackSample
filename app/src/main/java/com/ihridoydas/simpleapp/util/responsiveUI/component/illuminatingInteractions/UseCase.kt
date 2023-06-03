package com.ihridoydas.simpleapp.util.responsiveUI.component.illuminatingInteractions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IlluminatingInteractions(onBackPress:()->Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(Color.Black),
                title = { Text(text = "Illumination Interaction Button and Text" , style = TextStyle(color = Color.White)) },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackPress()
                        },
                        modifier = Modifier
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White )
                    }
                },
            )
        },
        drawerShape = RoundedCornerShape(topEnd = 23.dp, bottomEnd = 23.dp),
        content = {
            Column(
                modifier = Modifier.padding(it).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                RippleEffect()
                Spacer(modifier = Modifier.height(5.dp))
                NeanIndication()
                Spacer(modifier = Modifier.height(10.dp))
                ScaleButton(onClick = {}) {
                    Icon(Icons.Filled.ShoppingCart, "")
                    Spacer(Modifier.padding(10.dp))
                    Text(text = "Add to cart!")
                }
                Spacer(modifier = Modifier.height(5.dp))
                InteractionWithScale()
                Spacer(modifier = Modifier.height(10.dp))
                SimplePress()
                Spacer(modifier = Modifier.height(10.dp))
                PressIconButton(
                    modifier = Modifier.height(60.dp).width(200.dp),
                    onClick = {},
                    icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = null) },
                    text = { Text("Add to cart") }
                )


            }
        }
    )


}