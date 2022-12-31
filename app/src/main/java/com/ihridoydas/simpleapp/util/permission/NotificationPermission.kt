
package com.ihridoydas.simpleapp.util.permission

import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

/**
 * プッシュ通知許可を要求
 */
@RequiresApi(33)
@ExperimentalPermissionsApi
@Composable
fun NotificationPermission(
    permission: String = android.Manifest.permission.POST_NOTIFICATIONS,
    askPermission: Boolean
) {
    if (askPermission) {
        val permissionState = rememberPermissionState(permission)
        val lifecycleOwner = LocalLifecycleOwner.current

        /**
         * 初回起動時ホーム画面上でプッシュ通知許可を要求する。
         */
        DisposableEffect(
            key1 = lifecycleOwner,
            effect = {
                val observer = LifecycleEventObserver { _, event ->
                    if (event == Lifecycle.Event.ON_CREATE) {
                        if (!permissionState.status.isGranted) {
                            permissionState.launchPermissionRequest()
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
}
