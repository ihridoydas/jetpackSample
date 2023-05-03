

package com.ihridoydas.simpleapp.controll

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.ihridoydas.simpleapp.data.local.PrefDataStore
//import com.ihridoydas.simpleapp.navigation.WebViewSpec
import com.ihridoydas.simpleapp.ui.MainActivity
import com.ihridoydas.simpleapp.util.common.OnComposeLifecycleEvent
import com.ihridoydas.simpleapp.util.extensions.detectTaps
import com.ihridoydas.simpleapp.util.responsiveUI.component.alert.AlertViewModel
import kotlinx.coroutines.*
import java.lang.ref.WeakReference
import java.util.*

/**
 * A column that intercepts tap events and passes to auto logout
 * @param content The content this control wraps
 */
@Composable
fun AutoLogoutColumn(
    navController: NavController,
    autoLogoutViewModel: AutoLogoutViewModel = hiltViewModel(),
    alertViewModel: AlertViewModel = hiltViewModel(),
    content: @Composable() (ColumnScope.() -> Unit)
) {

    val context = WeakReference(LocalContext.current)

    val autoLogout = AutoLogout() {
        context.get()?.let { ctx ->
            val prefDataStore = PrefDataStore(context = ctx)
            Log.d("AutoLogout", "Send to login screen")

            runBlocking {
                prefDataStore.setOnShowCaseCompleted(false)

                val intent = Intent(ctx, MainActivity::class.java)
                intent.addFlags(
                 Intent.FLAG_ACTIVITY_CLEAR_TOP
                  or Intent.FLAG_ACTIVITY_CLEAR_TASK
                  or Intent.FLAG_ACTIVITY_NEW_TASK
                )
                ctx.startActivity(intent)
                (ctx as Activity).finish()
             //   navController.popBackStack(WebViewSpec.route, false)
            }
        }
    }


    // Androidバージョンによってギャラリーアクセス
    val mediaPermission = if (Build.VERSION.SDK_INT >= 33) {
        Manifest.permission.READ_MEDIA_IMAGES
    } else {
        Manifest.permission.ACCESS_MEDIA_LOCATION
    }

    // カメラアクセスpermission
    val cameraPermission = Manifest.permission.CAMERA

    //logout実行処理
    fun logout() {
        context.get()?.let { ctx ->
            val intent = Intent(ctx, MainActivity::class.java)
            intent.addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP
                        or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        or Intent.FLAG_ACTIVITY_NEW_TASK
            )
            ctx.startActivity(intent)
            (ctx as Activity).finish()
           // navController.popBackStack(WebViewSpec.route, false)
        }
    }

    /**
     * LifeCycleによるRuntimePermissionをチェックする。
     * アプリをバックグラウンドにしてRuntimePermissionを変更するとログアウト処理を実行する.
     * アプリインストールして初期起動の時デバイス登録画面上で、RuntimePermissionを要求しているところにこのコードの影響を防止するためデバイス登録フラグを使用する。
     */

    OnComposeLifecycleEvent { _, event ->
        if(autoLogoutViewModel.getIfIsShowCaseCompleted()){
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    context.get()?.let { ctx ->
                        logout()
                    }
                }
                Lifecycle.Event.ON_PAUSE -> {
                    context.get()?.let { ctx ->
                        //Write a code logic how you needed
                    }
                }
                Lifecycle.Event.ON_DESTROY -> {
                    Log.d("AutoLogout", "destroyed")
                    autoLogout.cleanUp()
                }
                else -> {}
            }
        }

    }

// Get Value when tap screen in time
    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTaps {
                    autoLogout.updateTouchTime(time = Date().time)
                }
            }
    ) {
        content()

        DisposableEffect(Unit) {
            onDispose {
                alertViewModel.hide()
            }
        }

    }

}
