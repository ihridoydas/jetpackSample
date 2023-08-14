package com.ihridoydas.simpleapp.features.composeImpressionTracker.demo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.features.composeImpressionTracker.utils.impression
import com.ihridoydas.simpleapp.features.composeImpressionTracker.utils.rememberDefaultImpressionState
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme


@Composable
fun List() {
    val impressionState = rememberDefaultImpressionState()
    LazyColumn(Modifier.fillMaxSize().padding(bottom = 30.dp)) {
        itemsIndexed((0..1000).toList()) { index, item ->
            // for recompose (this is not needed for production app)
            impressionState.impressFlow.collectAsState(initial = "").value
            val isImpressed = impressionState.alreadySentItems.contains(item)
            Text(
                "$index isImpressed:$isImpressed",
                Modifier.
                impression(
                    key = item,
                    impressionState = impressionState,
                    onImpression = { key ->
                        println("impressed:$key")
                    })
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleAppTheme {
        List()
    }
}
