package com.ihridoydas.simpleapp.util.responsiveUI.component.stickyTabHeader

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme

@Composable
fun StickyHeaderScreen() {
    var tabSelected by rememberSaveable { mutableStateOf(Screen.A) }
    Column {
        TabRow(
            selectedTabIndex = tabSelected.ordinal
        ) {
            Screen.values().map { it.name }.forEachIndexed { index, title ->
                Tab(
                    text = { Text(text = title) },
                    selected = tabSelected.ordinal == index,
                    onClick = { tabSelected = Screen.values()[index] }
                )
            }
        }
        when (tabSelected) {
            Screen.A -> AScreen()
            Screen.B -> BScreen()
        }
    }
}

enum class Screen {
    A, B
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleAppTheme {
        StickyHeaderScreen()
    }
}

@Composable
fun AScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(10) {
            Text("A $it")
        }
    }
}

@Composable
fun BScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(10) {
            Text("B $it")
        }
    }
}