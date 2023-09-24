package com.ihridoydas.simpleapp.features.stepperLibrary.kotstep.ui.vertical

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.features.stepperLibrary.kotstep.components.VerticalIconStep

/**
 * Create a composable representing a vertical icon-based stepper with customizable appearance and behavior.
 *
 * Displays a vertical icon-based stepper with a specified number of steps, allowing customization
 * of colors, icons, labels, and icons for each step based on its state (current, visited, or completed).
 *
 * @param modifier The modifier for styling the composable. (Optional)
 * @param totalSteps The total number of steps in the stepper. (Required)
 * @param currentStep The current active step. Defaults to the first step (0). (Optional)
 * @param lineThickness The thickness of the connecting line between steps. Defaults to 1.dp. (Optional)
 * @param stepSize The size of the step icons. Defaults to 28.dp. (Optional)
 * @param completedColor The color for completed steps. Defaults to [Color.Blue]. (Optional)
 * @param incompleteColor The color for incomplete steps. Defaults to [Color.Gray]. (Optional)
 * @param checkMarkColor The color of the checkmark symbol for completed steps. Defaults to [Color.White]. (Optional)
 * @param stepTitleOnIncompleteColor The color of step titles on incomplete steps. Defaults to [checkMarkColor]. (Optional)
 * @param stepTitleOnCompleteColor The color of step titles on completed steps. Defaults to [completedColor]. (Optional)
 * @param stepIconsList A list of ImageVectors representing icons for each step. Must have a size equal to 'totalSteps'. (Required)
 * @param stepIconsColorOnIncomplete The color of step icons on incomplete steps. Defaults to [checkMarkColor]. (Optional)
 * @param stepIconsColorOnComplete The color of step icons on completed steps. Defaults to [incompleteColor]. (Optional)
 * @param stepDescription A list of step descriptions. Each description corresponds to a step. (Optional)
 * @param showCheckMarkOnDone Whether to display a checkmark icon for completed steps. Defaults to 'false'. (Optional)
 *
 * @see <a href="https://m1.material.io/components/steppers.html#steppers-types-of-steppers">Material Components - Stepper</a>
 */
@Composable
fun VerticalIconStepper(
    modifier: Modifier = Modifier,
    totalSteps: Int,
    currentStep: Int = 0,
    lineThickness: Dp = 1.dp,
    stepSize: Dp = 28.dp,
    stepShape: Shape = CircleShape,
    completedColor: Color = Color.Blue,
    incompleteColor: Color = Color.Gray,
    checkMarkColor: Color = Color.White,
    stepTitleOnIncompleteColor: Color = checkMarkColor,
    stepTitleOnCompleteColor: Color = completedColor,
    stepIconsList: List<ImageVector>,
    stepIconsColorOnIncomplete: Color = checkMarkColor,
    stepIconsColorOnComplete: Color = incompleteColor,
    stepDescription: List<String> = List(totalSteps) { "" },
    showCheckMarkOnDone: Boolean = false
) {

    val descriptionList = MutableList(totalSteps) { "" }

    stepDescription.forEachIndexed { index, element ->
        if (index < totalSteps)
            descriptionList[index] = element
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (stepAtIt in 1..totalSteps) {

            VerticalIconStep(
                lineThickness = lineThickness,
                stepSize = stepSize,
                stepShape = stepShape,
                stepTitle = descriptionList[stepAtIt - 1],
                isCurrent = stepAtIt == currentStep,
                isVisited = stepAtIt < currentStep,
                isCompleted = stepAtIt == totalSteps,
                incompleteColor = incompleteColor,
                completedColor = completedColor,
                checkMarkColor = checkMarkColor,
                stepIcon = stepIconsList[stepAtIt - 1],
                stepTitleOnIncompleteColor = stepTitleOnIncompleteColor,
                stepTitleOnCompleteColor = stepTitleOnCompleteColor,
                stepIconsColorOnComplete = stepIconsColorOnComplete,
                stepIconsColorOnIncomplete = stepIconsColorOnIncomplete,
                showCheckMarkOnDone = showCheckMarkOnDone
            )
        }
    }
}