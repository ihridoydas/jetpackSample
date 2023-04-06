package com.ihridoydas.simpleapp.ui

/**
 * Created By Hridoy Chandra Das
 */

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ihridoydas.simpleapp.navigation.HomeScreenSpec.route
import com.ihridoydas.simpleapp.navigation.MainNavHost
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import com.ihridoydas.simpleapp.util.common.RootUtil
import com.ihridoydas.simpleapp.util.responsiveUI.component.drawerNavigation.NavDrawer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object {
        private val Tag = MainActivity::class.java.simpleName
    }
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (RootUtil.isDeviceRooted()) {
            Log.e(Tag, "onCreate - Rooted device.")
            finish()
            return
        }
        Log.d(Tag, "onCreate")
        installSplashScreen()
        //Show FeedBack Review
        //showFeedBackDialog(context = applicationContext, activity = this)
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            val state = rememberScaffoldState()
            val coroutineScope = rememberCoroutineScope()
            MyApp(windowSizeClass = windowSizeClass,state,coroutineScope)
        }
    }

    private var pressedTime: Long = 0
    // on below line we are calling on back press method.
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        // on below line we are checking if the press time is greater than 2 sec
        if (pressedTime + 2000 > System.currentTimeMillis()) {
            // if time is greater than 2 sec we are closing the application.
            super.onBackPressed()
            finish()
        } else {
            // in else condition displaying a toast message.
            Toast.makeText(applicationContext, "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        // on below line initializing our press time variable
        pressedTime = System.currentTimeMillis();
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MyApp(windowSizeClass: WindowSizeClass,state:ScaffoldState,coroutineScope:CoroutineScope) {
    SimpleAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "Navigation Demo") },
                        navigationIcon = {
                            IconButton(onClick = {
                                coroutineScope.launch { state.drawerState.open() }
                            }) {
                                Icon(Icons.Default.Menu, contentDescription = null)
                            }
                        }
                    )
                },
                drawerShape = RoundedCornerShape(topEnd = 23.dp, bottomEnd = 23.dp),
                drawerContent = { NavDrawer(state, coroutineScope) },
                content = {
                    //
                    val navController = rememberAnimatedNavController()
                    val systemUiController = rememberSystemUiController()
                    /**
                    // when use Auto Logout && Fixed time to navigate another screen
                    AutoLogoutColumn(navController = navController) { // implement your navHost }
                     */
                    MainNavHost(
                        windowSizeClass = windowSizeClass,
                        navController = navController,
                        systemUiController = systemUiController,
                        startDestination = route
                    )
                }
            )
        }
    }

}