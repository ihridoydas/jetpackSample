package com.ihridoydas.simpleapp.util.responsiveUI.component.animations.dragAndDrop.businessCardDesign.utils

import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned

@Composable
fun <T> DragTarget(
    modifier: Modifier,
    dataToDrop: T,
    content: @Composable (() -> Unit),
    onDragEnd: (dragTargetInfo: DragTargetInfo) -> Unit
) {
    var currentPosition by remember { mutableStateOf(Offset.Zero) }
    val currentState = LocalDragTargetInfo.current

    Box(modifier = modifier
        .onGloballyPositioned {
            currentPosition = it.localToRoot(Offset.Zero)
        }
        .pointerInput(Unit) {
            // detect DragGestures After LongPress
            detectDragGesturesAfterLongPress(
                onDragStart = {
                    currentState.dataToDrop = dataToDrop
                    currentState.isDragging = true
                    currentState.initialOffset = it
                    currentState.dragPosition = currentPosition + it
                    currentState.draggableComposable = content
                },
                onDrag = { change, dragAmount ->
                    change.consumeAllChanges()
                    currentState.dragOffset += Offset(dragAmount.x, dragAmount.y)
                },
                onDragEnd = {
                    onDragEnd(currentState)
                    currentState.isDragging = false
                    currentState.dragOffset = Offset.Zero
                },
                onDragCancel = {
                    currentState.dragOffset = Offset.Zero
                    currentState.isDragging = false
                })
        }) {
        content()
    }
}