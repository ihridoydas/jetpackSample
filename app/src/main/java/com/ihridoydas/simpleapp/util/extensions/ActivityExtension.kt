package com.ihridoydas.simpleapp.util.extensions

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

fun Activity.hideSystemBars() {
    val widowInsetsController = ViewCompat.getWindowInsetsController(window.decorView) ?: return
    widowInsetsController.systemBarsBehavior =
        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    widowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())
    widowInsetsController.show(WindowInsetsCompat.Type.statusBars())
}

fun Activity.openBrowser(uri: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
    startActivity(intent)
}

fun Activity.getDeviceName(): String {
    return try {
        Settings.Global.getString(contentResolver, Settings.Global.DEVICE_NAME)
            ?: "${android.os.Build.MANUFACTURER} ${android.os.Build.MODEL}"
    } catch (e: RuntimeException) {
        Log.e("getDeviceName", "error: failed to get the device name.")
        "${android.os.Build.MANUFACTURER} ${android.os.Build.MODEL}"
    }
}
