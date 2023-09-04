package com.ihridoydas.simpleapp.features.bottomSheets.material3Sheet

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.ihridoydas.simpleapp.features.bottomSheets.sheetCore.BottomSheetState
import com.ihridoydas.simpleapp.features.bottomSheets.sheetCore.CoreBottomSheet
import com.ihridoydas.simpleapp.features.bottomSheets.sheetCore.CoreBottomSheetDefaults
import com.ihridoydas.simpleapp.features.bottomSheets.sheetCore.CoreBottomSheetLayout
import com.ihridoydas.simpleapp.features.bottomSheets.sheetCore.DialogSheetBehaviors
import com.ihridoydas.simpleapp.features.bottomSheets.sheetCore.PeekHeight
import com.ihridoydas.simpleapp.features.bottomSheets.sheetCore.SheetBehaviors

/**
 * A bottom sheet will be displayed in a dialog window.
 *
 * @param state The bottom sheet state. Call [rememberBottomSheetState] to create one.
 * @param modifier Modifier for bottom sheet content.
 * @param skipPeeked Skip the peeked state if set to true. Defaults to false.
 * @param peekHeight Peek height, could be a dp, px, or fraction value.
 * @param backgroundColor Background color for sheet content.
 * @param dimColor Dim color. Defaults to [Color.Black].
 * @param maxDimAmount Maximum dim amount. Defaults to 0.45f.
 * @param behaviors Dialog sheet behaviors. Including system bars, clicking, window input mode, etc.
 * @param dragHandle Bottom sheet drag handle. A round bar was displayed by default.
 * @param content Sheet content.
 */
@Composable
fun BottomSheet(
    state: BottomSheetState,
    modifier: Modifier = Modifier,
    skipPeeked: Boolean = false,
    peekHeight: PeekHeight = PeekHeight.fraction(0.5f),
    shape: Shape = BottomSheetDefaults.shape,
    backgroundColor: Color = BottomSheetDefaults.backgroundColor,
    dimColor: Color = Color.Black,
    maxDimAmount: Float = CoreBottomSheetDefaults.MaxDimAmount,
    behaviors: DialogSheetBehaviors = BottomSheetDefaults.dialogSheetBehaviors(),
    dragHandle: @Composable () -> Unit = { BottomSheetDragHandle() },
    content: @Composable () -> Unit
) {
    CoreBottomSheet(
        state = state,
        modifier = modifier,
        skipPeeked = skipPeeked,
        peekHeight = peekHeight,
        shape = shape,
        backgroundColor = backgroundColor,
        dimColor = dimColor,
        maxDimAmount = maxDimAmount,
        behaviors = behaviors,
        dragHandle = dragHandle,
        content = content,
    )
}

/**
 * A bottom sheet layout will be embedded in the composable directly.
 *
 * @param state The bottom sheet state. Call [rememberBottomSheetState] to create one.
 * @param modifier Modifier for bottom sheet content.
 * @param skipPeeked Skip the peeked state if set to true. Defaults to false.
 * @param peekHeight Peek height, could be a dp, px, or fraction value.
 * @param backgroundColor Background color for sheet content.
 * @param dimColor Dim color. Defaults to [Color.Black].
 * @param maxDimAmount Maximum dim amount. Defaults to 0.45f.
 * @param behaviors Dialog sheet behaviors. Including system bars, clicking, window input mode, etc.
 * @param dragHandle Bottom sheet drag handle. A round bar was displayed by default.
 * @param content Sheet content.
 */
@Composable
fun BottomSheetLayout(
    state: BottomSheetState,
    modifier: Modifier = Modifier,
    skipPeeked: Boolean = false,
    peekHeight: PeekHeight = PeekHeight.fraction(0.5f),
    shape: Shape = BottomSheetDefaults.shape,
    backgroundColor: Color = BottomSheetDefaults.backgroundColor,
    dimColor: Color = Color.Black,
    maxDimAmount: Float = CoreBottomSheetDefaults.MaxDimAmount,
    behaviors: SheetBehaviors = SheetBehaviors(),
    dragHandle: @Composable () -> Unit = { BottomSheetDragHandle() },
    content: @Composable () -> Unit
) {
    CoreBottomSheetLayout(
        state = state,
        modifier = modifier,
        skipPeeked = skipPeeked,
        peekHeight = peekHeight,
        shape = shape,
        backgroundColor = backgroundColor,
        dimColor = dimColor,
        maxDimAmount = maxDimAmount,
        behaviors = behaviors,
        dragHandle = dragHandle,
        content = content,
    )
}