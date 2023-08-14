package com.ihridoydas.simpleapp.features.composeImpressionTracker.demo

import android.app.Activity
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.NavHostController
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
//Use this MainActivity for future. if you use? -> you can use it
@Composable
fun ImpressionScreen(activity: Activity, navController: NavHostController) {
    activity.setContentView(FrameLayout(activity).apply {
        layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        ).apply {
            (this as ViewGroup.MarginLayoutParams).topMargin = 1000
        }
        val composeView = ComposeView(activity)
        composeView.setContent {
            SimpleAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    List()
                }
            }
        }
        addView(composeView)
    })


}