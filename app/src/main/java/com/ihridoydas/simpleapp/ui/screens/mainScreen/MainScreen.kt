package com.ihridoydas.simpleapp.ui.screens.mainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ihridoydas.simpleapp.util.responsiveUI.component.bottom_navigation.BottomNavigationFluid
import com.ihridoydas.simpleapp.util.responsiveUI.component.drawerNavigation.NavDrawer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    state:ScaffoldState,
    coroutineScope: CoroutineScope,
    windowSizeClass: WindowSizeClass,
    navController: NavController,
) {
    Scaffold(
        content = {
            Column (modifier = Modifier.padding(it)){
                BottomNavigationFluid(navController = navController)
            }
        }
    )

}