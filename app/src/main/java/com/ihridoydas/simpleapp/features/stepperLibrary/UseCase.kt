package com.ihridoydas.simpleapp.features.stepperLibrary

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.features.stepperLibrary.kotstep.ui.horizontal.HorizontalIconStepper
import com.ihridoydas.simpleapp.features.stepperLibrary.kotstep.ui.horizontal.HorizontalSequencedStepper
import com.ihridoydas.simpleapp.features.stepperLibrary.kotstep.ui.vertical.VerticalIconStepper
import com.ihridoydas.simpleapp.features.stepperLibrary.kotstep.ui.vertical.VerticalSequencedStepper
import com.ihridoydas.simpleapp.features.stepperLibrary.utils.StepperItemShape
import com.ihridoydas.simpleapp.features.stepperLibrary.utils.StepperItemShape.Companion.getShapeFromEnum
import com.ihridoydas.simpleapp.features.stepperLibrary.utils.StepperTypes

@Preview(showBackground = true)
@Composable
fun MainStepper() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        var totalSteps by rememberSaveable {
            mutableStateOf(5)
        }

        var currentStep by rememberSaveable { mutableStateOf(0) }

        var expanded by remember { mutableStateOf(false) }

        var expandedShapeMenu by remember { mutableStateOf(false) }

        var currentStepperType by remember {
            mutableStateOf(StepperTypes.HORIZONTAL_SEQUENCED_STEPPER)
        }

        var currentStepperItemShape by remember {
            mutableStateOf(StepperItemShape.CIRCLE_SHAPE)
        }

        var stepItemSize by remember {
            mutableStateOf(35)
        }

        var lineThickness by rememberSaveable {
            mutableStateOf(3)
        }


        Column(
            modifier = Modifier
                .fillMaxHeight(0.7f)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box {
                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Localized description")
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Horizontal Sequenced Stepper") },
                            onClick = {
                                currentStepperType = StepperTypes.HORIZONTAL_SEQUENCED_STEPPER
                            },
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.KeyboardArrowRight,
                                    contentDescription = null
                                )
                            })
                        DropdownMenuItem(
                            text = { Text("Vertical Sequenced Stepper") },
                            onClick = {
                                currentStepperType = StepperTypes.VERTICAL_SEQUENCED_STEPPER
                            },
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.KeyboardArrowDown,
                                    contentDescription = null
                                )
                            })
                        DropdownMenuItem(
                            text = { Text("Horizontal Icon Stepper") },
                            onClick = { currentStepperType = StepperTypes.HORIZONTAL_ICON_STEPPER },
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.KeyboardArrowRight,
                                    contentDescription = null
                                )
                            })
                        DropdownMenuItem(
                            text = { Text("Vertical Icon Stepper") },
                            onClick = { currentStepperType = StepperTypes.VERTICAL_ICON_STEPPER },
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.KeyboardArrowDown,
                                    contentDescription = null
                                )
                            })
                    }
                }

                Box {
                    IconButton(onClick = { expandedShapeMenu = true }) {
                        Icon(Icons.Default.Menu, contentDescription = "Localized description")
                    }
                    DropdownMenu(
                        expanded = expandedShapeMenu,
                        onDismissRequest = { expandedShapeMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("RectangleShape") },
                            onClick = { currentStepperItemShape = StepperItemShape.RECTANGLE_SHAPE }
                        )
                        DropdownMenuItem(
                            text = { Text("CircleShape") },
                            onClick = { currentStepperItemShape = StepperItemShape.CIRCLE_SHAPE }
                        )
                        DropdownMenuItem(
                            text = { Text("CutCornerShape") },
                            onClick = {
                                currentStepperItemShape = StepperItemShape.CUT_CORNER_SHAPE
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("RoundedCornerShape") },
                            onClick = {
                                currentStepperItemShape = StepperItemShape.ROUNDED_CORNER_SHAPE
                            }
                        )
                    }
                }
            }

            when (currentStepperType) {
                StepperTypes.HORIZONTAL_SEQUENCED_STEPPER -> {
                    HorizontalSequencedStepper(
                        totalSteps = totalSteps,
                        currentStep = currentStep,
                        stepSize = stepItemSize.dp,
                        stepShape = getShapeFromEnum(currentStepperItemShape),
                        lineThickness = lineThickness.dp,
                        completedColor = MaterialTheme.colorScheme.primary,
                        incompleteColor = Color.Gray,
                        checkMarkColor = Color.White,
                        stepNameOnIncompleteColor = Color.White,
                        stepNameOnCompleteColor = Color.White
                    )
                }

                StepperTypes.VERTICAL_SEQUENCED_STEPPER -> {
                    VerticalSequencedStepper(
                        totalSteps = totalSteps,
                        currentStep = currentStep,
                        stepSize = stepItemSize.dp,
                        stepShape = getShapeFromEnum(currentStepperItemShape),
                        lineThickness = lineThickness.dp,
                        completedColor = MaterialTheme.colorScheme.primary,
                        incompleteColor = Color.Gray,
                        checkMarkColor = Color.White,
                        stepNameOnIncompleteColor = Color.White,
                        stepNameOnCompleteColor = Color.White
                    )
                }

                StepperTypes.HORIZONTAL_ICON_STEPPER -> {
                    HorizontalIconStepper(
                        totalSteps = totalSteps,
                        currentStep = currentStep,
                        stepSize = stepItemSize.dp,
                        stepShape = getShapeFromEnum(currentStepperItemShape),
                        lineThickness = lineThickness.dp,
                        completedColor = MaterialTheme.colorScheme.primary,
                        incompleteColor = Color.Gray,
                        checkMarkColor = Color.White,
                        stepIconsColorOnIncomplete = Color.White,
                        stepIconsColorOnComplete = Color.White,
                        stepIconsList = listOf(
                            Icons.Default.AccountBox,
                            Icons.Default.AddCircle,
                            Icons.Default.Build,
                            Icons.Default.Face,
                            Icons.Default.Home,
                            Icons.Default.AccountBox,
                            Icons.Default.AddCircle,
                            Icons.Default.Build,
                            Icons.Default.Face,
                            Icons.Default.Home
                        )
                    )
                }

                else -> {
                    VerticalIconStepper(
                        totalSteps = totalSteps,
                        currentStep = currentStep,
                        stepSize = stepItemSize.dp,
                        stepShape = getShapeFromEnum(currentStepperItemShape),
                        lineThickness = lineThickness.dp,
                        completedColor = MaterialTheme.colorScheme.primary,
                        incompleteColor = Color.Gray,
                        checkMarkColor = Color.White,
                        stepIconsColorOnIncomplete = Color.White,
                        stepIconsColorOnComplete = Color.White,
                        stepIconsList = listOf(
                            Icons.Default.AccountBox,
                            Icons.Default.AddCircle,
                            Icons.Default.Build,
                            Icons.Default.Face,
                            Icons.Default.Home,
                            Icons.Default.AccountBox,
                            Icons.Default.AddCircle,
                            Icons.Default.Build,
                            Icons.Default.Face,
                            Icons.Default.Home
                        )
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(20.dp)
            ) {
                Text(
                    text = "Total Steps: : $totalSteps",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Slider(
                    value = totalSteps.toFloat(),
                    onValueChange = { newValue ->
                        totalSteps = newValue.toInt()
                    },
                    valueRange = 1f..10f, // Set the range of Total Steps
                    steps = 10, // Divide the range into 10 steps
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Step Item Size in DP: $stepItemSize",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Slider(
                    value = stepItemSize.toFloat(),
                    onValueChange = { newValue ->
                        stepItemSize = newValue.toInt()
                    },
                    valueRange = 35f..55f, // Set the range of Step Item size
                    steps = 20, // Divide the range into 20 steps
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Line Thickness in DP: $lineThickness",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Slider(
                    value = lineThickness.toFloat(),
                    onValueChange = { newValue ->
                        lineThickness = newValue.toInt()
                    },
                    valueRange = 1f..10f, // Set the range of Line Thickness
                    steps = 10, // Divide the range into 10 steps
                    modifier = Modifier.fillMaxWidth()
                )
            }


            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {

                AnimatedVisibility(
                    visible = currentStep >= 1,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Button(
                        onClick = { currentStep-- },
                        enabled = currentStep >= 1
                    ) {
                        Text(text = "Previous")
                    }
                }

                AnimatedVisibility(
                    visible = currentStep <= totalSteps,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Button(
                        onClick = { currentStep++ },
                        enabled = currentStep <= totalSteps
                    ) {
                        Text(
                            text =
                            if (currentStep == 0) "Start"
                            else if (currentStep >= totalSteps) "Finish"
                            else "Next"
                        )
                    }
                }
            }
        }
    }
}