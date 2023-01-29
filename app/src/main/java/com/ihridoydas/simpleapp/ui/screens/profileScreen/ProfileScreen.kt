package com.ihridoydas.simpleapp.ui.screens.profileScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.runtime.*
import androidx.compose.ui.text.style.TextMotion.Companion.Animated
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(windowSizeClass: WindowSizeClass,navController: NavController,onBackPress : ()->Unit) {

    // スクロールの行動 (scrollBehavior)
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)

    Scaffold(
        topBar = {
            Row {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackPress() }
                )
            }
        },
        content = {paddingvalues->
            Column(
                Modifier
                    .padding(paddingValues = paddingvalues)
                    .fillMaxSize(),
               // Arrangement.Center,
               // Alignment.CenterHorizontally
            ) {
                Text(text = "Profile Screen")

             //   ThreeButtons1()

                MyScreen()
            }
        }
    )
    

}

@Composable
fun ThreeButtons1() {

    val buttonOneVisible = remember { mutableStateOf(true) }
    val buttonTwoVisible = remember { mutableStateOf(true) }
    val buttonThreeVisible = remember { mutableStateOf(true) }

    Row (modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly){

        if (buttonOneVisible.value) {
            Button(
                onClick = {
                    buttonOneVisible.value = false
                    buttonTwoVisible.value = false
                    buttonThreeVisible.value = true
                },
            ) {
                Text("Button One")
            }
        }
        if (buttonTwoVisible.value) {
            Button(
                onClick = {
                    buttonOneVisible.value = false
                    buttonTwoVisible.value = false
                    buttonThreeVisible.value = false
                },
            ) {
                Text("Button Two")
            }
        }
        if (buttonThreeVisible.value) {
            Button(
                onClick = {
                    buttonOneVisible.value = true
                    buttonTwoVisible.value = false
                    buttonThreeVisible.value = false


                },
            ) {
                Text("Button Three")
            }
        }
    }

}


@Composable
fun MyScreen() {
//    val array = arrayOf(0, 1, 2)
//    var selectedIndex by remember { mutableStateOf(-1) }
//    Column {
//        array.forEachIndexed { index, value ->
//            Button(onClick = { selectedIndex = index }) {
//                Text("Select $value")
//            }
//        }
//        when(selectedIndex) {
//            0 -> Text("You selected ${array[2]}")
//            1 -> Text("You selected ${array[1]}")
//            2 -> Text("You selected ${array[0]}")
//            else -> Text("Please select a button")
//        }
//    }



}


