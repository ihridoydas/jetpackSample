package com.ihridoydas.simpleapp.features.bottomSheets.materialSheet

import android.os.Build
import android.view.WindowManager
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.SecureFlagPolicy
import com.ihridoydas.simpleapp.features.bottomSheets.sheetCore.DialogSheetBehaviors

/**
 * Default values used by [BottomSheet] and [BottomSheetLayout].
 */
object BottomSheetDefaults {
    @Composable
    fun dialogSheetBehaviors(
        collapseOnBackPress: Boolean = true,
        collapseOnClickOutside: Boolean = true,
        extendsIntoStatusBar: Boolean = false,
        extendsIntoNavigationBar: Boolean = false,
        dialogSecurePolicy: SecureFlagPolicy = SecureFlagPolicy.Inherit,
        dialogWindowSoftInputMode: Int = WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED,
        lightStatusBar: Boolean = false,
        lightNavigationBar: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
                MaterialTheme.colors.isLight,
        statusBarColor: Color = Color.Transparent,
        navigationBarColor: Color = if (lightNavigationBar) Color.Transparent else Color.Black,
    ): DialogSheetBehaviors {
        return DialogSheetBehaviors(
            collapseOnBackPress = collapseOnBackPress,
            collapseOnClickOutside = collapseOnClickOutside,
            extendsIntoStatusBar = extendsIntoStatusBar,
            extendsIntoNavigationBar = extendsIntoNavigationBar,
            dialogSecurePolicy = dialogSecurePolicy,
            dialogWindowSoftInputMode = dialogWindowSoftInputMode,
            lightStatusBar = lightStatusBar,
            lightNavigationBar = lightNavigationBar,
            statusBarColor = statusBarColor,
            navigationBarColor = navigationBarColor,
        )
    }
}