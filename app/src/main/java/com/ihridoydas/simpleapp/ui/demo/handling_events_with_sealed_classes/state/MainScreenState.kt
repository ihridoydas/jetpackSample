package com.ihridoydas.simpleapp.ui.demo.handling_events_with_sealed_classes.state

data class MainScreenState(
    var isCountButtonVisible : Boolean = false,
    var displayingResult : String = "",
    var inputValue : String = ""
)
