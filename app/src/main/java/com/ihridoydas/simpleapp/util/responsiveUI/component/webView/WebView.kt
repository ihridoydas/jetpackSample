package com.ihridoydas.simpleapp.util.responsiveUI.component.webView

import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavController
import com.google.accompanist.web.*
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.navigation.HomeScreenSpec
import com.ihridoydas.simpleapp.navigation.ProfileScreenSpec
import com.ihridoydas.simpleapp.navigation.WebViewSpec
import com.ihridoydas.simpleapp.ui.theme.md_theme_light_onPrimary
import com.ihridoydas.simpleapp.ui.theme.md_theme_light_primary
import com.ihridoydas.simpleapp.util.constants.BROWSER_LINK
import com.ihridoydas.simpleapp.util.responsiveUI.component.text.AutoResizedText

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun WebBrowser(windowSizeClass: WindowSizeClass, navController: NavController) {
    var url by remember { mutableStateOf(BROWSER_LINK) }
    val state = rememberWebViewState(url = url)
    val navigator = rememberWebViewNavigator()
    var textFieldValue by remember(state.content.getCurrentUrl()) {
        mutableStateOf(state.content.getCurrentUrl() ?: "")
    }

    // スクロールの行動
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)

    Scaffold(
        backgroundColor = Color.White,
        topBar = {
            TopAppBar(modifier = Modifier,
                contentColor = md_theme_light_onPrimary,
                backgroundColor = md_theme_light_primary,
                content = {
                    Row(
                        modifier = Modifier.weight(0.3f),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        IconButton(onClick = {
                            navController?.navigate(ProfileScreenSpec.route) {
                                popUpTo(WebViewSpec.route) {
                                    inclusive = true
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.weight(0.5f),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        AutoResizedText(
                            text = "Web Browser", style = TextStyle(
                                color = Color.White,
                                fontSize = MaterialTheme.typography.h6.fontSize,
                                fontWeight = MaterialTheme.typography.h6.fontWeight
                            )
                        )
                    }

                    Row(
                        modifier = Modifier.weight(1.2f),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(onClick = { navigator.navigateBack() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                        IconButton(
                            modifier = Modifier.padding(start = 10.dp),
                            onClick = { navigator.navigateForward() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = "Forward"
                            )
                        }
                        IconButton(onClick = { navigator.reload() }) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Refresh"
                            )
                        }
                        IconButton(onClick = { url = textFieldValue }) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Go"
                            )
                        }
                    }
                }
            )

        },
        content = {
            Column(modifier = Modifier.padding(it)) {
                Row(modifier = Modifier.padding(all = 12.dp)) {
                    val keyboardController = LocalSoftwareKeyboardController.current
                    BasicTextField(
                        modifier = Modifier.weight(9f),
                        value = textFieldValue,
                        onValueChange = { textFieldValue = it },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                keyboardController?.hide()
                                url = textFieldValue
                            }),
                    )
                    if (state.errorsForCurrentRequest.isNotEmpty()) {
                        Icon(
                            modifier = Modifier
                                .weight(1f),
                            imageVector = Icons.Default.Warning,
                            contentDescription = "Error",
                            tint = Color.Red
                        )
                    }
                }

                val loadingState = state.loadingState
                if (loadingState is LoadingState.Loading) {
                    LinearProgressIndicator(
                        progress = loadingState.progress,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // A custom WebViewClient and WebChromeClient can be provided via subclassing
                val webClient = remember {
                    object : AccompanistWebViewClient() {
                        override fun onPageStarted(
                            view: WebView?,
                            url: String?,
                            favicon: Bitmap?
                        ) {
                            super.onPageStarted(view, url, favicon)
                            Log.d("Accompanist WebView", "Page started loading for $url")
                        }
                    }
                }

                WebView(
                    state = state,
                    modifier = Modifier.weight(1f),
                    navigator = navigator,
                    onCreated = { webView ->
                        webView.settings.javaScriptEnabled = true
                    },
                    client = webClient
                )
            }
        }
    )
}