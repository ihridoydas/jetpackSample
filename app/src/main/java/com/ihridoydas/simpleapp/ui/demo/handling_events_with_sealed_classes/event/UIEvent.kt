package com.ihridoydas.simpleapp.ui.demo.handling_events_with_sealed_classes.event

sealed class UIEvent {
    data class showMessage(val message:String) : UIEvent()
}