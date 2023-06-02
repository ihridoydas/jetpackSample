package com.ihridoydas.simpleapp.features.sortingVisualizer.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ihridoydas.simpleapp.features.sortingVisualizer.model.Algorithm
import com.ihridoydas.simpleapp.features.sortingVisualizer.ui.algorithms.bubbleSort
import com.ihridoydas.simpleapp.features.sortingVisualizer.ui.algorithms.insertionSort
import com.ihridoydas.simpleapp.features.sortingVisualizer.ui.algorithms.mergeSort
import com.ihridoydas.simpleapp.features.sortingVisualizer.ui.algorithms.quickSort
import com.ihridoydas.simpleapp.features.sortingVisualizer.ui.algorithms.randomize
import com.ihridoydas.simpleapp.features.sortingVisualizer.ui.algorithms.selectionSort
import com.ihridoydas.simpleapp.features.sortingVisualizer.ui.algorithms.twoPointerQuickSort
import com.ihridoydas.simpleapp.features.sortingVisualizer.ui.components.BottomButtons
import com.ihridoydas.simpleapp.features.sortingVisualizer.ui.components.CustomChipRow
import com.ihridoydas.simpleapp.ui.theme.GreenExtraDark
import com.ihridoydas.simpleapp.features.sortingVisualizer.ui.util.getScreenHeight
import com.ihridoydas.simpleapp.features.sortingVisualizer.ui.util.getScreenWidth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun VisualScreen() {
    val width = getScreenWidth()
    val height = getScreenHeight() / 2

    var items by remember { mutableStateOf(mutableListOf<Int>()) }
    var selectedAlgorithm by remember { mutableStateOf(Algorithm.BUBBLE_SORT) }
    var isSortRunning by remember { mutableStateOf(false) }

    val onUpdateItems: (MutableList<Int>) -> Unit = {
        items = mutableListOf()
        items = it
    }

    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    Scaffold(
        backgroundColor = GreenExtraDark,
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .background(Color.White)
            ) {
                Column {
                    CustomChipRow(
                        scrollState = scrollState,
                        selectedAlgorithm = selectedAlgorithm,
                        onSelectedAlgorithmChanged = {
                            selectedAlgorithm = it
                            items = randomize(width, height)
                        }
                    )

                    Spacer(modifier = Modifier.height(22.dp))

                    BottomButtons(
                        isSortRunning = isSortRunning,
                        onStartSorting = {
                            startSortingAlgorithm(
                                algorithm = selectedAlgorithm,
                                scope = scope,
                                items = items,
                                onUpdateItems = onUpdateItems,
                                onSortRunning = { isSortRunning = it }
                            )
                        },
                        onRandomize = {
                            items = randomize(width, height)
                        }
                    )
                }
            }
        }
    ) {
        Spacer(modifier = Modifier.height(22.dp))
        DrawingCanvas(
            modifier = Modifier
                .padding(it)
                .fillMaxHeight(0.7f),
            items = items
        )
    }
}

fun startSortingAlgorithm(
    algorithm: Algorithm,
    scope: CoroutineScope,
    items: MutableList<Int>,
    onUpdateItems: (MutableList<Int>) -> Unit,
    onSortRunning: (Boolean) -> Unit
) {
    when (algorithm) {
        Algorithm.BUBBLE_SORT -> {
            scope.launch {
                onSortRunning(true)

                bubbleSort(
                    list = items,
                    onUpdateItems = onUpdateItems
                )

                onSortRunning(false)
            }
        }

        Algorithm.SELECTION_SORT -> {
            scope.launch {
                onSortRunning(true)

                selectionSort(
                    list = items,
                    onUpdateItems = onUpdateItems
                )

                onSortRunning(false)
            }
        }

        Algorithm.INSERTION_SORT -> {
            scope.launch {
                onSortRunning(true)

                insertionSort(
                    list = items,
                    onUpdateItems = onUpdateItems
                )

                onSortRunning(false)
            }
        }

        Algorithm.QUICK_SORT -> {
            scope.launch {
                onSortRunning(true)

                quickSort(
                    list = items,
                    start = 0,
                    end = items.size - 1,
                    onUpdateItems = onUpdateItems
                )

                onSortRunning(false)
            }
        }

        Algorithm.TWO_POINTER_QUICK_SORT -> {
            scope.launch {
                onSortRunning(true)

                twoPointerQuickSort(
                    list = items,
                    left = 0,
                    right = items.size - 1,
                    onUpdateItems = onUpdateItems
                )

                onSortRunning(false)
            }
        }

        Algorithm.MERGE_SORT -> {
            scope.launch {
                onSortRunning(true)

                val list = items
                val temp = mutableListOf<Int>()
                items.forEach { temp.add(it) }

                mergeSort(
                    list = list,
                    temp = temp,
                    leftStart = 0,
                    rightEnd = list.size - 1,
                    onUpdateItems = onUpdateItems
                )

                onSortRunning(false)
            }
        }
    }
}






















