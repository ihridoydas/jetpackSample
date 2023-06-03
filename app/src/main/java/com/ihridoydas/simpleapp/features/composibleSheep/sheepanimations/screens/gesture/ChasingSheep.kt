package com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.screens.gesture

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.spring
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.features.composibleSheep.sheep.model.Sheep
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.model.SheepDefaultSize
import com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.model.SheepUiState
import com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.model.withGroovyJump
import com.ihridoydas.simpleapp.ui.theme.Grid
import com.ihridoydas.simpleapp.ui.theme.SheepColor
import com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.screens.transition.JumpingSheep

private val colors = listOf(
    SheepColor.Gray,
    SheepColor.Blue,
    SheepColor.Green,
    SheepColor.Purple,
    SheepColor.Magenta,
    SheepColor.Orange,
)

private const val SingleTap = false
private const val ShowPointer = true
private const val Warping = true
private const val IsJumping = true
private const val HasShadow = true
private val PointerSize = Grid.Ten

@Composable
fun ChasingSheep(
    modifier: Modifier = Modifier,
) {
    val sheepUiState by remember {
        mutableStateOf(
            SheepUiState(
                sheep = Sheep(fluffColor = SheepColor.Magenta),
                sheepSize = SheepDefaultSize.div(2),
            ).withGroovyJump(HasShadow)
        )
    }
    var isSheepVisible by remember { mutableStateOf(true) }
    var isSheepJumping by remember { mutableStateOf(false) }
    val containerOffset = remember { Animatable(DpOffset(0.dp, 0.dp), DpOffset.VectorConverter) }

    var pointerColor by remember { mutableStateOf(colors.first()) }
    var pointerAlpha by remember { mutableStateOf(0f) }
    var pointerScale by remember { mutableStateOf(0f) }
    val pointerPosition = remember { Animatable(DpOffset(0.dp, 0.dp), DpOffset.VectorConverter) }

    val scope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(false) {
                if (SingleTap) {
                    detectTapGestures(onTap = { offset ->
                        scope.launch {
                            val dpOffset = DpOffset(offset.x.toDp(), offset.y.toDp())

                            // Pointer position
                            pointerPosition.snapTo(
                                DpOffset(
                                    x = dpOffset.x - PointerSize.div(2f),
                                    y = dpOffset.y - PointerSize.div(2f),
                                )
                            )
                            // Pointer in
                            pointerColor = colors.random()
                            coroutineScope {
                                launch { animate(0f, 1f) { value, _ -> pointerAlpha = value } }
                                launch { animate(0f, 1f) { value, _ -> pointerScale = value } }
                            }
                            // Pointer out
                            coroutineScope {
                                launch { animate(1f, 0f) { value, _ -> pointerAlpha = value } }
                                launch { animate(1f, 2f) { value, _ -> pointerScale = value } }
                            }

                            // Sheep pause
                            isSheepJumping = false

                            if (Warping) {
                                // Sheep out
                                isSheepVisible = false
                                delay(500)
                            }

                            // Sheep position
                            val newOffset = DpOffset(
                                x = dpOffset.x - sheepUiState.sheepSize.width / 2,
                                y = dpOffset.y - sheepUiState.sheepJumpSize - sheepUiState.sheepSize.height * 0.65f
                            )

                            // Sheep in
                            if (Warping) {
                                containerOffset.snapTo(newOffset)
                                isSheepVisible = true
                                delay(500)
                            } else {
                                containerOffset.animateTo(
                                    newOffset,
                                    animationSpec = spring(
                                        dampingRatio = Spring.DampingRatioLowBouncy,
                                        stiffness = Spring.StiffnessLow
                                    )
                                )
                            }

                            // Sheep resume
                            isSheepJumping = true
                        }
                    })
                } else {
                    // Advanced Animation
                    forEachGesture {
                        awaitPointerEventScope {
                            val firstDownEvent = awaitFirstDown()

                            // Pointer in
                            scope.launch {
                                pointerPosition.snapTo(
                                    DpOffset(
                                        x = firstDownEvent.position.x.toDp() - PointerSize.div(2f),
                                        y = firstDownEvent.position.y.toDp() - PointerSize.div(2f),
                                    )
                                )
                                pointerColor = colors.random()
                                coroutineScope {
                                    launch { animate(0f, 1f) { value, _ -> pointerAlpha = value } }
                                    launch { animate(0f, 1f) { value, _ -> pointerScale = value } }
                                }
                            }

                            do {
                                // Sheep pause
                                isSheepJumping = false

                                val pointerEvent = awaitPointerEvent()
                                pointerEvent.changes.forEach { change ->
                                    scope.launch {
                                        // Pointer offset
                                        pointerPosition.snapTo(
                                            DpOffset(
                                                x = change.position.x.toDp() - PointerSize.div(2f),
                                                y = change.position.y.toDp() - PointerSize.div(2f),
                                            )
                                        )
                                    }
                                }
                            } while (pointerEvent.changes.any { it.pressed })

                            scope.launch {
                                // Pointer Out
                                coroutineScope {
                                    launch { animate(1f, 0f) { value, _ -> pointerAlpha = value } }
                                    launch { animate(1f, 2f) { value, _ -> pointerScale = value } }
                                }

                                if (Warping) {
                                    // Sheep out
                                    isSheepVisible = false
                                    delay(500)
                                }

                                // Sheep in
                                val newOffset = DpOffset(
                                    x = pointerPosition.value.x - sheepUiState.sheepSize.width / 2 + PointerSize / 2f,
                                    y = pointerPosition.value.y - sheepUiState.sheepJumpSize - sheepUiState.sheepSize.height * 0.65f + PointerSize / 2f
                                )

                                // Sheep in
                                if (Warping) {
                                    containerOffset.snapTo(newOffset)
                                    isSheepVisible = true
                                    delay(500)
                                } else {
                                    containerOffset.animateTo(
                                        newOffset,
                                        animationSpec = spring(
                                            dampingRatio = Spring.DampingRatioLowBouncy,
                                            stiffness = Spring.StiffnessLow
                                        )
                                    )
                                }

                                // Sheep resume
                                isSheepJumping = true
                            }
                        }
                    }
                }
            },
    ) {
        if (ShowPointer) {
            Box(
                modifier = Modifier
                    .offset(
                        x = pointerPosition.value.x,
                        y = pointerPosition.value.y
                    )
                    .size(PointerSize)
                    .scale(scale = pointerScale)
                    .alpha(pointerAlpha)
                    .background(color = pointerColor, shape = CircleShape)
            )
        }
        AnimatedVisibility(
            modifier = Modifier.offset(x = containerOffset.value.x, y = containerOffset.value.y),
            visible = isSheepVisible,
            enter = scaleIn(
                animationSpec = spring(
                    stiffness = Spring.StiffnessMediumLow, dampingRatio = 0.25f
                )
            ),
            exit = scaleOut()
        ) {
            JumpingSheep(
                sheepUiState = sheepUiState,
                jumping = if (IsJumping) isSheepJumping else false
            )
        }
    }
}
