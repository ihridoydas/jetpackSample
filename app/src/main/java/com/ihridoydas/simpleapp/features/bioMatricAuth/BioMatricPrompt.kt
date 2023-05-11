import android.content.Context
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.ihridoydas.simpleapp.ui.MainActivity

 fun showBiometricPrompt(activity: MainActivity) {
    val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle("Biometric Authentication")
        .setSubtitle("Log in using your biometric credential")
        .setNegativeButtonText("Cancel")
        .build()

    val biometricPrompt = BiometricPrompt(activity, ContextCompat.getMainExecutor(activity),
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                // Handle authentication error
                showMessage("Authentication error: $errString",activity)
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                // Handle authentication success
                showMessage("Authentication succeeded!",activity)
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                // Handle authentication failure
                showMessage("Authentication failed.",activity)
            }
        })

    biometricPrompt.authenticate(promptInfo)
}


 fun showMessage(message: String,context:Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

 fun isBiometricSupported(activity: MainActivity): Boolean {
    val biometricManager = BiometricManager.from(activity)
    val canAuthenticate = biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK)
    when (canAuthenticate) {
        BiometricManager.BIOMETRIC_SUCCESS -> {
            // The user can authenticate with biometrics, continue with the authentication process
            return true
        }

        BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE, BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE, BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
            // Handle the error cases as needed in your app
            return false
        }

        else -> {
            // Biometric status unknown or another error occurred
            return false
        }
    }
}