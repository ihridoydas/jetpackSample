package com.ihridoydas.simpleapp.ui

/**
 * Created By Hridoy Chandra Das
 */

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ihridoydas.simpleapp.features.workManager.CustomWorker
import com.ihridoydas.simpleapp.navigation.animationNavHost.MainAnimationNavHost
import com.ihridoydas.simpleapp.ui.screens.startScreen.SplashViewModel
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import com.ihridoydas.simpleapp.util.common.RootUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import java.time.Duration

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    companion object {
        private val Tag = MainActivity::class.java.simpleName
    }
    private val splashViewModel: SplashViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //----------------
        //For Work Manager Practice
        val workRequest = OneTimeWorkRequestBuilder<CustomWorker>()
            .setInitialDelay(Duration.ofSeconds(10))
            .setBackoffCriteria(
                backoffPolicy = BackoffPolicy.LINEAR,
                duration = Duration.ofSeconds(15)
            )
            .build()
        WorkManager.getInstance(applicationContext).enqueue(workRequest)

        //----------------

        //---------------
        // For Location Tracker
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ),
            0
        )
        //---------------



        if (RootUtil.isDeviceRooted()) {
            Log.e(Tag, "onCreate - Rooted device.")
            finish()
            return
        }
        Log.d(Tag, "onCreate")
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                splashViewModel.isLoading.value
            }
        }
        //Show FeedBack Review
        //showFeedBackDialog(context = applicationContext, activity = this)
        splashViewModel.checkStartScreen() { route ->
            setContent {
                val navController = rememberAnimatedNavController()
                val systemUiController = rememberSystemUiController()
                val windowSizeClass = calculateWindowSizeClass(this)
                val state = rememberScaffoldState()
                val coroutineScope = rememberCoroutineScope()
                val context = LocalContext.current

                MyApp(
                    navController = navController,
                    systemUiController = systemUiController,
                    windowSizeClass = windowSizeClass,
                    state = state,
                    coroutineScope = coroutineScope,
                    startDestination = route,
                    activity = this@MainActivity,
                    context = context
                )

            }
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
            Toast.makeText(applicationContext, "Press back again to exit", Toast.LENGTH_SHORT)
                .show();
        }
        // on below line initializing our press time variable
        pressedTime = System.currentTimeMillis();
    }


    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @Composable
    fun MyApp(
        navController: NavHostController,
        systemUiController: SystemUiController,
        windowSizeClass: WindowSizeClass,
        state: ScaffoldState,
        coroutineScope: CoroutineScope,
        startDestination: String,
        activity: MainActivity,
        context : Context
    ) {
        SimpleAppTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {

                Scaffold(
                    content = {
                        /**
                        // when use Auto Logout && Fixed time to navigate another screen
                        AutoLogoutColumn(navController = navController) { // implement your navHost }
                         */
                        MainAnimationNavHost(
                            navController = navController,
                            windowSizeClass = windowSizeClass,
                            systemUiController = systemUiController,
                            scaffoldState = state,
                            coroutineScope = coroutineScope,
                            startDestination = startDestination,
                            activity = activity,
                            context = context
                        )
                    }
                )
            }
        }

    }
}