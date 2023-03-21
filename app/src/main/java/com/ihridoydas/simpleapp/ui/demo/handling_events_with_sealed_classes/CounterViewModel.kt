package com.ihridoydas.simpleapp.ui.demo.handling_events_with_sealed_classes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ihridoydas.simpleapp.ui.demo.handling_events_with_sealed_classes.event.CounterEvent
import com.ihridoydas.simpleapp.ui.demo.handling_events_with_sealed_classes.event.UIEvent
import com.ihridoydas.simpleapp.ui.demo.handling_events_with_sealed_classes.state.MainScreenState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class CounterViewModel : ViewModel() {
    private val _screenState = mutableStateOf(
        MainScreenState(
            inputValue = "",
            displayingResult = "Total is 0.0"
        )
    )

    val screenState : State<MainScreenState> = _screenState

    private val _uiEventFlow = MutableSharedFlow<UIEvent>()

    val uiEventFlow = _uiEventFlow.asSharedFlow()

    private var total = 0.0

    private fun addTotal(){
        total += _screenState.value.inputValue.toDouble()
        _screenState.value = _screenState.value.copy(
            displayingResult = "Total is $total",
            isCountButtonVisible = false
        )
    }
    private fun resetTotal(){
        total = 0.0
        _screenState.value = _screenState.value.copy(
            displayingResult = "Total is $total",
            inputValue = "",
            isCountButtonVisible = false
        )
    }

    fun onEvent(event: CounterEvent){
        when(event){
            is CounterEvent.ValueEnterd->{
                _screenState.value = _screenState.value.copy(
                    inputValue = event.value,
                    isCountButtonVisible = true
                )
            }
            is CounterEvent.CounterButtonClicked->{
                addTotal()
                viewModelScope.launch {
                    _uiEventFlow.emit(
                        UIEvent.showMessage(
                            message = "Value Added Successfully"
                        )
                    )
                }
            }
            is CounterEvent.ResetButtonClicked->{
                resetTotal()
                viewModelScope.launch {
                    _uiEventFlow.emit(
                        UIEvent.showMessage(
                            message = "Value Reset Successfully"
                        )
                    )
                }
            }
        }
    }
}