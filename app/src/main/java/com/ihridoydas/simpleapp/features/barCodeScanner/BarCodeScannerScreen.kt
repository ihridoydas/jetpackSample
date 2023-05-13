package com.ihridoydas.simpleapp.features.barCodeScanner

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ihridoydas.simpleapp.features.allFeature.AllFeatureScreen
import com.ihridoydas.simpleapp.util.responsiveUI.component.drawerNavigation.customDrawer.MenuView
import com.ihridoydas.simpleapp.util.responsiveUI.rememberWindowSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarCodeScreen(viewModel: BarCodeViewModel = hiltViewModel(),onBackPress : ()->Unit) {

    val state = viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Bar Code Scan") },
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
                Box(modifier = Modifier
                    .fillMaxSize()
                    .weight(0.5f), contentAlignment = Alignment.Center) {
                    Text(text =  state.value.details )
                }

                Box(modifier = Modifier
                    .fillMaxSize()
                    .weight(0.5f), contentAlignment = Alignment.BottomCenter) {
                    Button(onClick = { viewModel.startScanning() }) {
                        Text(text = "start scanning")
                    }
                }


            }
        }
    )




}