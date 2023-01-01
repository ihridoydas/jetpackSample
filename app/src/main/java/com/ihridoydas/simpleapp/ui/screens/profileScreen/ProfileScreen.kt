package com.ihridoydas.simpleapp.ui.screens.profileScreen

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ihridoydas.simpleapp.navigation.HomeScreenSpec
import com.ihridoydas.simpleapp.navigation.ProfileScreenSpec

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(windowSizeClass: WindowSizeClass,navController: NavController) {

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
                        .clickable {
                            navController?.navigate(HomeScreenSpec.requestNavigationRoute()) {
                                popUpTo(ProfileScreenSpec.route) {
                                    inclusive = true
                                }
                            }
                        }
                )
            }
        },
        content = {paddingvalues->
            Column(
                Modifier
                    .padding(paddingValues = paddingvalues)
                    .fillMaxSize(),
                Arrangement.Center,
                Alignment.CenterHorizontally
            ) {
                Text(text = "Profile Screen")

            }
        }
    )
    

}