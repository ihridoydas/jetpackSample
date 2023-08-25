package com.ihridoydas.simpleapp.features.bottomSheets.uitils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun M3SimpleSheetContent(
    state: com.ihridoydas.simpleapp.features.bottomSheets.sheetCore.BottomSheetState,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = modifier
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .fillMaxWidth()
    ) {
        Header(
            text = "Lorem ipsum",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = LoremIpsum.SHORT,
            modifier = Modifier
                .weight(weight = 1f, fill = false)
                .verticalScroll(state = rememberScrollState()),
            lineHeight = 1.5.em,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { scope.launch { state.collapse() } },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Close")
        }
    }
}
