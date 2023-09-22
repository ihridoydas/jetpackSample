package com.ihridoydas.simpleapp.features.stepperCompose.stepper_compose

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.features.stepperCompose.stepper_compose.StepState.COMPLETE
import com.ihridoydas.simpleapp.features.stepperCompose.stepper_compose.StepState.TODO


@Composable
fun Stepper(
  //type: StepperType = vertical,
  scrollState: ScrollState = rememberScrollState(1),
  currentStep: MutableState<Int> = remember { mutableStateOf(0) },
  steps: List<Step>,
  nextButton: @Composable (() -> Unit)? = {
    Button(onClick = {
      if (currentStep.value < steps.size) {
        steps.getOrNull(currentStep.value)?.state?.value = COMPLETE
        currentStep.value = currentStep.value + 1
      }
    }) { Text("Next".uppercase()) }
  },
  previousButton: @Composable (() -> Unit)? = {
    TextButton(onClick = {
      if(currentStep.value > 0) {
        steps.getOrNull(currentStep.value)?.state?.value = TODO
        currentStep.value = currentStep.value - 1
      }
    }) { Text("Cancel".uppercase(), color = Color.Gray) }
  }) {
  Column(modifier = Modifier
    .padding(horizontal = 20.dp)
    .verticalScroll(
      state = scrollState,
      enabled = true,
      reverseScrolling = false,
    )) {
    Spacer(modifier = Modifier.height(10.dp))
    steps.forEachIndexed { index, step ->
      StepUi(
        modifier = Modifier.clickable { currentStep.value = index },
        index = index + 1,
        step = step,
        expanded = index == currentStep.value,
        isLastStep = index == (steps.size - 1),
        nextButton = nextButton,
        previousButton = previousButton,
      )
    }
  }
}

/**
 * Defines the [Stepper]'s main axis.
 */
enum class StepperType {
  // A vertical layout of the steps with their content in-between the titles.
  vertical,

  // A horizontal layout of the steps with their content below the titles.
  horizontal
}