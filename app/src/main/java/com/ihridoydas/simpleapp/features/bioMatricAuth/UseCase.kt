package com.ihridoydas.simpleapp.features.bioMatricAuth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import com.ihridoydas.simpleapp.ui.MainActivity
import com.ihridoydas.simpleapp.util.common.OnComposeLifecycleEvent
import isBiometricSupported
import showBiometricPrompt
import showMessage

//Just Use This in your Activity
// BiomatricApi(activity = this)
@Composable
fun BiomatricApi(activity: MainActivity) {
    Column(modifier = Modifier.fillMaxSize()) {
        OnComposeLifecycleEvent { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> {
                    if (isBiometricSupported(activity = activity)) {
                        showBiometricPrompt(activity)
                    } else {
                        // Handle the case when biometric authentication is not supported
                        showMessage("Not Support",activity)
                    }
                }

                else -> {}
            }
        }
    }

}