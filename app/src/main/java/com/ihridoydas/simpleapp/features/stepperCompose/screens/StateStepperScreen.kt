package com.ihridoydas.simpleapp.features.stepperCompose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons.Rounded
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.features.stepperCompose.stepper_compose.Const.Black38
import com.ihridoydas.simpleapp.features.stepperCompose.stepper_compose.Step
import com.ihridoydas.simpleapp.features.stepperCompose.stepper_compose.StepState
import com.ihridoydas.simpleapp.features.stepperCompose.stepper_compose.Stepper
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private lateinit var currentStep: MutableState<Int>

private val statePrintAppStep = mutableStateOf(StepState.TODO)

private val exampleStateInStep = Step(
  title = "Printing App",
  subtitle = "Print Connect App must be installed",
  state = statePrintAppStep,
) {
  when (statePrintAppStep.value) {
    StepState.TODO -> Button(onClick = {
      statePrintAppStep.value = StepState.LOADING
      CoroutineScope(Dispatchers.IO).launch {
        delay(2000)
      }.invokeOnCompletion {
        statePrintAppStep.value = StepState.ERROR
      }
    }) {
      Text("click")
    }
    StepState.LOADING -> CircularProgressIndicator()
    StepState.ERROR -> Button(onClick = {
      statePrintAppStep.value = StepState.COMPLETE
      nextStep()
    }) {
      Text("RETRY")
    }
    StepState.COMPLETE -> Text("Complete")
  }
}

private fun nextStep() {
  if (currentStep.value < steps.size)
    currentStep.value = currentStep.value + 1
}

private val steps = listOf(
  exampleStateInStep,
  Step(title = "Printing App", subtitle = "Print Connect App must be installed") {
    Column(
      modifier = Modifier.padding(top = 8.dp, end = 24.dp)
    ) {
      Text(
        text = "L’application print connect n’est pas détecté sur votre PDA.",
        style = MaterialTheme.typography.caption
      )
      Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
      ) {
        Icon(
          Rounded.Close,
          modifier = Modifier
            .size(48.dp),
          tint = Color.Red,
          contentDescription = "Application non installée"
        )
      }
      Text(
        text = "Celle-ci est necessaire pour imprimer depuis l’application DLC Memo .\n\n" +
            "Si votre PDA autorise le téléchargement de nouvelles applications cliquer sur :",
        style = MaterialTheme.typography.caption
      )
      Spacer(modifier = Modifier.height(16.dp))
      Button(onClick = { /*TODO*/ }) {
        Text("Télécharger Print Connect".uppercase())
      }
    }
  },
  Step(
    title = "Bluetooth connection",
    subtitle = "Check if Bluetooth is present and enabled"
  ) {
    Box(
      modifier = Modifier
        .height(250.dp)
        .fillMaxWidth()
        .background(color = Black38)
    )
  }
)

@Composable
fun StateStepperScreen() {
  currentStep = remember { mutableStateOf(0) }
  SimpleAppTheme {
    Stepper(
      currentStep = currentStep,
      nextButton = null,
      previousButton = null,
      steps = steps
    )
  }
}