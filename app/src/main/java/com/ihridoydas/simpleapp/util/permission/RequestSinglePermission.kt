
package com.ihridoydas.simpleapp.util.permission

import android.Manifest
import android.util.Log
import androidx.compose.runtime.*
import com.google.accompanist.permissions.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestLocationPermission(
    askPermission: Boolean,
    permission: String = Manifest.permission.ACCESS_FINE_LOCATION,
    navigateToCamera: () -> Unit = {}
) {
    if (askPermission) {

        val permissionState = rememberPermissionState(permission) {
            navigateToCamera()
        }

        HandleRequest(
            permissionState = permissionState,
            deniedContent = {
                if (permissionState.status.shouldShowRationale) {
                    Log.d("PERMISSION", "show rational: User denied permission")
                    navigateToCamera()
                } else{
                    Log.d("PERMISSION", "permission request")
                    LaunchedEffect(key1 = !permissionState.status.isGranted, block = {permissionState.launchPermissionRequest()})
                }
            },
            content = {
                Log.d("PERMISSION", "permission granted")
                navigateToCamera()
            }
        )
    }
}



@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun HandleRequest(
    permissionState: PermissionState,
    deniedContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) {

    when(permissionState.status) {

        is PermissionStatus.Granted -> {
            content()
        }
        is PermissionStatus.Denied -> {
            deniedContent()
        }
    }
}


