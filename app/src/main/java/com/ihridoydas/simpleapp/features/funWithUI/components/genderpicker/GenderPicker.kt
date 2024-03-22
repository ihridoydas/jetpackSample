package com.ihridoydas.simpleapp.features.funWithUI.components.genderpicker

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.R

sealed interface Gender {
    object Male: Gender
    object Female: Gender

}

@Composable
fun GenderPicker(
    modifier : Modifier = Modifier,
    maleGradient : List<Color> = listOf(Color(0xFF6D6DFF), Color.Blue),
    femaleGradient : List<Color> = listOf(Color(0xFFEA76FF), Color.Magenta),
    distanceBetween : Dp = 50.dp,
    scale: Float = 7f,
    onGenderClick: (Gender) -> Unit,
) {

    var selectedGender by remember { mutableStateOf<Gender>(Gender.Female) }
    var center by remember { mutableStateOf(Offset.Unspecified) }

    val malePathString = stringResource(id = R.string.male_path)
    val femalePathString = stringResource(id = R.string.female_path)

    val malePath = remember { PathParser().parsePathString(malePathString).toPath() }
    val femalePath = remember { PathParser().parsePathString(femalePathString).toPath() }

    val malePathBounds = remember { malePath.getBounds() }
    val femalePathBounds = remember { femalePath.getBounds() }

    var maleTranslationOffset by remember { mutableStateOf(Offset.Zero) }
    var femaleTranslationOffset by remember { mutableStateOf(Offset.Zero) }

    var currentClickOffset by remember { mutableStateOf(Offset.Zero) }

    val maleSelectionRadius = animateFloatAsState(
        targetValue = if (selectedGender is Gender.Male) 80f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "maleAnimation"
    )

    val femaleSelectionRadius = animateFloatAsState(
        targetValue = if (selectedGender is Gender.Female) 80f else .0f,
        animationSpec = tween(durationMillis = 500),
        label = "maleAnimation"
    )

    Canvas(
        modifier = modifier
            .pointerInput(true) {
                detectTapGestures { offset ->
                    val transformedMaleRect = Rect(
                        offset = maleTranslationOffset,
                        size = malePathBounds.size * scale
                    )
                    val transformedFemaleRect = Rect(
                        offset = femaleTranslationOffset,
                        size = femalePathBounds.size * scale
                    )

                    if (transformedMaleRect.contains(offset) && selectedGender != Gender.Male) {
                        currentClickOffset = offset
                        selectedGender = Gender.Male
                        onGenderClick(Gender.Male)
                    }
                    else if (transformedFemaleRect.contains(offset) && selectedGender != Gender.Female) {
                        currentClickOffset = offset
                        selectedGender = Gender.Female
                        onGenderClick(Gender.Female)
                    }
                }
            }
    ) {
        center = this.center

        maleTranslationOffset = Offset(
            x = center.x - malePathBounds.width * scale - distanceBetween.toPx() / 2,
            y = center.y - scale * malePathBounds.height / 2
        )

        femaleTranslationOffset = Offset(
            x = center.x + distanceBetween.toPx() / 2,
            y = center.y - scale * femalePathBounds.height / 2
        )

        val untransformedMaleClickOffset = if (currentClickOffset == Offset.Zero) {
            malePathBounds.center
        } else {
            (currentClickOffset - maleTranslationOffset) / scale
        }

        val untransformedFemaleClickOffset = if (currentClickOffset == Offset.Zero) {
            femalePathBounds.center
        } else {
            (currentClickOffset - maleTranslationOffset) / scale
        }

        translate(
            left = maleTranslationOffset.x,
            top = maleTranslationOffset.y,
        ) {
            scale(
                scale = scale,
                pivot = malePathBounds.topLeft
            ) {
                drawPath(
                    path = malePath,
                    color = Color.LightGray
                )
                clipPath(
                    path = malePath
                ) {
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = maleGradient,
                            center = untransformedMaleClickOffset,
                            radius = maleSelectionRadius.value + 1f
                        ),
                        radius = maleSelectionRadius.value,
                        center = untransformedMaleClickOffset
                    )
                }
            }
        }
        translate(
            left = femaleTranslationOffset.x,
            top = femaleTranslationOffset.y,
        ) {
            scale(
                scale = scale,
                pivot = femalePathBounds.topLeft
            ) {
                drawPath(
                    path = femalePath,
                    color = Color.LightGray
                )
                clipPath(
                    path = femalePath
                ) {
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = femaleGradient,
                            center = untransformedFemaleClickOffset,
                            radius = femaleSelectionRadius.value + 1f
                        ),
                        radius = femaleSelectionRadius.value,
                        center = untransformedFemaleClickOffset
                    )
                }
            }
        }
    }
}