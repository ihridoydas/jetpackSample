package com.ihridoydas.simpleapp.features.bioMatricAuth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
                        showMessage("Not Support", activity)
                    }
                }

                else -> {}
            }
        }
    }

}