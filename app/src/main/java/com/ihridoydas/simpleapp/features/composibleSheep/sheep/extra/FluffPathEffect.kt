package com.ihridoydas.simpleapp.features.composibleSheep.sheep.extra

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StampedPathEffectStyle
import com.ihridoydas.simpleapp.features.composibleSheep.sheep.model.FluffStyle
import com.ihridoydas.simpleapp.features.composibleSheep.sheep.parts.getFluffPath

fun getSheepPathEffect(
    miniFluffRadius: Float
) =
    PathEffect.stampedPathEffect(
        shape = getFluffPath(
            circleCenterOffset = Offset.Zero,
            circleRadius = miniFluffRadius,
            fluffStyle = FluffStyle.Random()
        ),
        advance = miniFluffRadius * 3f,
        phase = miniFluffRadius,
        style = StampedPathEffectStyle.Morph
    )
