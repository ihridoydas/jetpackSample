package com.ihridoydas.simpleapp.ui.demo.handling_events_with_sealed_classes.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ihridoydas.simpleapp.ui.demo.handling_events_with_sealed_classes.event.CounterEvent
import com.ihridoydas.simpleapp.ui.demo.handling_events_with_sealed_classes.CounterViewModel
import com.ihridoydas.simpleapp.ui.demo.handling_events_with_sealed_classes.event.UIEvent
import kotlinx.coroutines.flow.collectLatest


@Composable
fun CounterScreen(
    modifier: Modifier = Modifier,
    counterViewModel: CounterViewModel = viewModel()
) {

    val screenState = counterViewModel.screenState.value

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true){
        counterViewModel.uiEventFlow.collectLatest {event->
            when(event){
                is UIEvent.showMessage->{
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }

        }
    }

    Scaffold(scaffoldState = scaffoldState) {
        Column(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
                .padding(50.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Text(
                text = screenState.displayingResult,
                modifier = modifier.fillMaxWidth(),
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp,
                color = Color.Blue
            )
            OutlinedTextField(
                value =screenState.inputValue,
                onValueChange ={
                    counterViewModel.onEvent(event = CounterEvent.ValueEnterd(it))
                },
                modifier = modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                textStyle = TextStyle(
                    color = Color.LightGray,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                label = { Text(text = "New Count") }
            )

            if(screenState.isCountButtonVisible){
                Button(
                    onClick = {
                        counterViewModel.onEvent(event = CounterEvent.CounterButtonClicked)
                    },
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Count",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 30.sp
                    )

                }
            }

            Button(
                onClick = {
                    if(screenState.displayingResult != ""){
                        counterViewModel.onEvent(event = CounterEvent.ResetButtonClicked)
                    }
                },
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Reset",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 30.sp
                )

            }

        }

    }

}