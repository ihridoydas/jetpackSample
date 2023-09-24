package com.ihridoydas.simpleapp.features.timeLineCompose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.features.timeLineCompose.TimeLineViewModel.Companion.TEST_DATA
import com.ihridoydas.simpleapp.features.timeLineCompose.composables.LazyTimeline
import com.ihridoydas.simpleapp.features.timeLineCompose.entities.HiringStage
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme

@Composable
fun HireingScreenContent(timelineStages: Array<HiringStage>) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyTimeline(timelineStages)
    }
}

@Preview(showBackground = true)
@Composable
private fun TimelineUsagePreview() {
    SimpleAppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LazyTimeline(TEST_DATA)
        }
    }
}