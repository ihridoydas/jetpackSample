package com.ihridoydas.simpleapp.util.common

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme

@Composable
fun MyFullScreenComposable() {
    val view = LocalView.current
    val systemUiController = rememberSystemUiController()

    SideEffect {
        requestFullScreen(view,systemUiController)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // ... other content

        Column(modifier = Modifier.align(Alignment.Center)) {
            Button(
                onClick = { requestFullScreen(view, systemUiController) }
            ) {
                Text("Go fullscreen")
            }

            Button(
                onClick = { requestNormalScreen(view) }
            ) {
                Text("Show StatusBar And NavigationBar")
            }

        }


    }

    val context = LocalContext.current
    DisposableEffect(true) {
        enableFullScreen(context)
        onDispose {
            disableFullScreen(context)
        }
    }

}

fun requestFullScreen(view: View, systemUiController: SystemUiController) {
    // !! should be safe here since the view is part of an Activity
    val window = view.context.getActivity()!!.window
    WindowCompat.getInsetsController(window, view).hide(
        WindowInsetsCompat.Type.statusBars() or
                WindowInsetsCompat.Type.navigationBars()
    )
    // navigation bar
    systemUiController.isNavigationBarVisible = false

    // status bar
    systemUiController.isStatusBarVisible = false

    // system bars
    systemUiController.isSystemBarsVisible = false
}

fun requestNormalScreen(view: View) {
    // !! should be safe here since the view is part of an Activity
    val window = view.context.getActivity()!!.window
    WindowCompat.getInsetsController(window, view).show(
        WindowInsetsCompat.Type.statusBars() or
                WindowInsetsCompat.Type.navigationBars()
    )
}

fun Context.getActivity(): Activity? = when (this) {
    is Activity -> this
    // this recursion should be okay since we call getActivity on a view context
    // that should have an Activity as its baseContext at some point
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}


fun enableFullScreen(context: Context) {

    val window = context.findActivity().window
    WindowCompat.setDecorFitsSystemWindows(window, false)
    WindowInsetsControllerCompat(window, window.decorView).apply {
        systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        hide(WindowInsetsCompat.Type.systemBars())
    }
}

fun disableFullScreen(context: Context) {
    val window = context.findActivity().window
    WindowCompat.setDecorFitsSystemWindows(window, true)
    WindowInsetsControllerCompat(window, window.decorView).apply {
        show(WindowInsetsCompat.Type.systemBars())
        systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
    }
}

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}





@Preview(showBackground = true)
@Composable
fun ShowComposeView() {
    SimpleAppTheme {
        MyFullScreenComposable()
    }

}