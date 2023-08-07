package com.ihridoydas.simpleapp.ui.screens.viewScreen

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Switch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.features.allFeature.AllFeatureScreen
import com.ihridoydas.simpleapp.features.bioMatricAuth.BiomatricApi
import com.ihridoydas.simpleapp.navigation.animationNavHost.ScreenDestinations
import com.ihridoydas.simpleapp.navigation.animationNavHost.navigateTo
import com.ihridoydas.simpleapp.ui.MainActivity
import com.ihridoydas.simpleapp.util.responsiveUI.component.bottom_navigation.BottomNavigationFluid
import com.ihridoydas.simpleapp.util.responsiveUI.component.card.CardView
import com.ihridoydas.simpleapp.util.responsiveUI.component.drawerNavigation.customDrawer.MenuView
import com.ihridoydas.simpleapp.util.responsiveUI.rememberWindowSize
import com.ihridoydas.simpleapp.util.showcase.ShowcaseStyle
import isBiometricSupported
import kotlinx.coroutines.CoroutineScope
import showBiometricPrompt
import showMessage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewScreen(
    state:ScaffoldState,
    coroutineScope: CoroutineScope,
    activity: MainActivity,
    windowSizeClass: WindowSizeClass,
    navController: NavController,
    onBackPress :()-> Unit,
    onClick: () -> Unit

) {
    val checked = remember { mutableStateOf(false) }

    // サイドメニューアニメーション用状態変数
    var editable by remember { mutableStateOf(false) }

    // サイドメニュー表示フラグ
    var sideMenuVisible by remember { mutableStateOf(false) }

    // ホーム画面オフセットアニメーション変数
    val homeOffsetX by animateIntAsState(
        targetValue = (if (editable) -220 else 0),
        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
    )

    // サイドメニューオフセットアニメーション変数
    val sideMenuOffsetX: Int by animateIntAsState(
        targetValue = if (editable) 0 else 220,
        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing),
        finishedListener = {
            if (!editable && sideMenuVisible) {
                sideMenuVisible = false
            }
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Simple App Demo") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackPress()
                        },
                        modifier = Modifier
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    Switch(
                        checked = checked.value,
                        onCheckedChange = {
                            checked.value = it
                            if (checked.value) {
                                if (isBiometricSupported(activity = activity)) {
                                    showBiometricPrompt(activity)
                                } else {
                                    // Handle the case when biometric authentication is not supported
                                    showMessage("Not Support", activity)
                                }
                            }
                        }
                    )
                    IconButton(onClick = {
                        editable = !editable
                        if (!sideMenuVisible) {
                            sideMenuVisible = true
                        }
                    }) {
                        Icon(Icons.Default.Menu, contentDescription = null)
                    }
                }
            )
        },
        drawerShape = RoundedCornerShape(topEnd = 23.dp, bottomEnd = 23.dp),
        content = {
            val scrollState = rememberScrollState()
            Box (modifier = Modifier.padding(it)){
                Column (modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)){
                    AllFeatureScreen(navController= navController)
                }

                // サイドメニュー表示
                if (sideMenuVisible) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = sideMenuOffsetX.dp)
                    ) {
                        MenuView(
                            window = rememberWindowSize(),
                            closeTap = { editable = false },
                            settingsTap = {  },
                            faqTap = { },
                            contactTap = {  },
                            privacyPolicyTap = {  },
                            termsOfUseTap = {  },
                            logOutTap = {  }
                        )

                    }
                }
            }

        }
    )

}