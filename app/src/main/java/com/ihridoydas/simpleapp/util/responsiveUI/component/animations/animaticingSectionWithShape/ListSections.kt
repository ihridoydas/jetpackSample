package com.ihridoydas.simpleapp.util.responsiveUI.component.animations.animaticingSectionWithShape

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimatingListSections() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        val todos = remember {
            mutableStateListOf(
                Todo(text = "Foo", isChecked = false),
                Todo(text = "Bar", isChecked = false),
                Todo(text = "Bauz", isChecked = false),
                Todo(text = "Baz", isChecked = false),
            )
        }

        val listItems = todos.toSections().toListElements()

        val radioOptions = listOf("Merged", "Divided", "Rounded")
        val (selectedOption, onOptionSelected) = remember { mutableIntStateOf(0) }
        val spacedBy by animateDpAsState(Dp(selectedOption * 2f))
        val innerCornerSize by animateDpAsState(Dp(selectedOption * 4f))

        Column {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(spacedBy, Alignment.CenterVertically)
            ) {
                items(
                    items = listItems,
                    key = { it.id },
                    contentType = { it::class }
                ) {
                    when (it) {
                        is ListElement.Header -> HeaderItem(
                            modifier = Modifier.animateItemPlacement(),
                            text = it
                        )

                        is ListElement.Item -> TodoItem(
                            modifier = Modifier.animateItemPlacement(),
                            todoItem = it,
                            innerCornerSize = innerCornerSize
                        ) { clickedId ->
                            val index = todos.indexOf(todos.find { it.id == clickedId })
                            if (index >= 0) {
                                todos[index] = todos[index].copy(isChecked = !todos[index].isChecked)
                            }
                        }
                    }
                }
            }

            Column(Modifier.selectableGroup()) {
                radioOptions.forEachIndexed { index, text ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .selectable(
                                selected = (index == selectedOption),
                                onClick = { onOptionSelected(index) },
                                role = Role.RadioButton
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (index == selectedOption),
                            onClick = null
                        )
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
        }
    }

}

@Preview
@Composable
fun AnimatingListSectionPreview() {
    SimpleAppTheme {
        AnimatingListSections()
    }

}