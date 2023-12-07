package com.ihridoydas.simpleapp.features.stepperCompose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.features.stepperCompose.stepper_compose.Const.Black38
import com.ihridoydas.simpleapp.features.stepperCompose.stepper_compose.Step
import com.ihridoydas.simpleapp.features.stepperCompose.stepper_compose.Stepper
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme

@Preview(showSystemUi = true)
@Composable
fun MaterialStepperScreen() {
  SimpleAppTheme {
    Stepper(
      steps = List(8) { index ->
        Step(
          title = "Name of step ${index + 1}",
          subtitle = "Subtitle of step ${index + 1}"
        ) {
          Box(
            modifier = Modifier
              .height(250.dp)
              .fillMaxWidth()
              .background(color = Black38)
          )
        }
      }
    )
  }
}