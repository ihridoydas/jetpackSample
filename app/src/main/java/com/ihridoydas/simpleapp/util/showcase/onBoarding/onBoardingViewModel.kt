package com.ihridoydas.simpleapp.util.showcase.onBoarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ihridoydas.simpleapp.data.local.PrefDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val prefDataStore: PrefDataStore
):ViewModel(){

    fun setOnShowCaseCompleted(OnShowCaseCompleted: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            prefDataStore.setOnShowCaseCompleted(OnShowCaseCompleted)
        }
    }

    val getOnShowCaseCompleted: Flow<Boolean?> = prefDataStore.getOnShowCaseCompleted

}