package com.ihridoydas.simpleapp.ui.demo.handling_events_with_sealed_classes.event

sealed class CounterEvent{
    data class ValueEnterd(val value : String) : CounterEvent()
    object CounterButtonClicked : CounterEvent()
    object ResetButtonClicked : CounterEvent()

}

