
package com.ihridoydas.simpleapp.util.permission

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

/**
 * Handle camera permissions
 */
@ExperimentalPermissionsApi
@Composable
fun Permission(
    shouldLaunchPermissionsRequest: Boolean = false,
    permission: List<String>
) {
    val multiplePermissionsState = rememberMultiplePermissionsState(permission)
    val lifecycleOwner = LocalLifecycleOwner.current

    /**
     * cameraアクセス案内画面から「次へ」ブタンタップすると
     * LifecycleEventObserver->ON_STARTイベントが実装する
     * Permissionダイアログを表示します。
     */
    DisposableEffect(
        key1 = lifecycleOwner,
        effect = {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_CREATE) {
                    if (!multiplePermissionsState.allPermissionsGranted) {
                        if (shouldLaunchPermissionsRequest) {
                            multiplePermissionsState.launchMultiplePermissionRequest()
                        }
                    }
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }
    )
}

/**
 * Does user have permission
 * 設定から許可チェンジするとログイン画面に移動する [PERMISSION CHECK]
 * @return
 */

///**
//ToDo 設定から許可チェンジするとログイン画面に移動する [PERMISSION CHECK]
@Composable
fun doesUserHavePermission(): Boolean {
    val result: Int =
        LocalContext.current.checkCallingOrSelfPermission(Manifest.permission.CAMERA)
    return result == PackageManager.PERMISSION_GRANTED
}

@Composable
fun doesUserHaveNotPermission(): Boolean {
    val result: Int =
        LocalContext.current.checkCallingOrSelfPermission(Manifest.permission.CAMERA)
    return result == PackageManager.PERMISSION_DENIED
}
//**/