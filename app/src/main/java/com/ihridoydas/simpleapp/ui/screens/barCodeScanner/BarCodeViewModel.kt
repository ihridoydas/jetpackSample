package com.ihridoydas.simpleapp.ui.screens.barCodeScanner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ihridoydas.simpleapp.domain.barCode.BarCodeMainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BarCodeViewModel @Inject constructor(
    private val repo: BarCodeMainRepo
):ViewModel() {

    private val _state = MutableStateFlow(BarCodeScreenState())
    val state = _state.asStateFlow()


    fun startScanning(){
        viewModelScope.launch {
             repo.startScanning().collect{
                if (!it.isNullOrBlank()){
                    _state.value = state.value.copy(
                        details = it
                    )
                }
            }
        }
    }
}