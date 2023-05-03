package com.ihridoydas.simpleapp.ui.screens.startScreen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ihridoydas.simpleapp.data.local.PrefDataStore
import com.ihridoydas.simpleapp.navigation.animationNavHost.ScreenDestinations
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val prefDataStore: PrefDataStore,

    ) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()


    fun checkStartScreen(callback: (route: String) -> Unit = {}) {

        val isStartScreenCover =
            runBlocking { prefDataStore.getIsStartScreenCover.first() }

        // Hide splash screen when api response comes back
        _isLoading.value = false

        if (!isStartScreenCover!!) {
            callback(ScreenDestinations.StartShowCaseScreen.route)
        } else {
            callback(ScreenDestinations.ViewScreen.route)
        }
    }


}
