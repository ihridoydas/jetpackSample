
package com.ihridoydas.simpleapp.controll

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ihridoydas.simpleapp.data.local.PrefDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class AutoLogoutViewModel @Inject constructor(
    private val prefDataStore: PrefDataStore
): ViewModel() {
    fun setOnShowCaseCompleted(OnShowCaseCompleted: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            prefDataStore.setOnShowCaseCompleted(OnShowCaseCompleted)
        }
    }

    val getOnShowCaseCompleted: Flow<Boolean?> = prefDataStore.getOnShowCaseCompleted

    fun getIfIsShowCaseCompleted(): Boolean {
        return runBlocking { prefDataStore.getOnShowCaseCompleted.first() ?: false }
    }
}