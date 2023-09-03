package com.ihridoydas.simpleapp.features.timeLineCompose.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.ui.theme.Gray200
import com.ihridoydas.simpleapp.ui.theme.Green500
import com.ihridoydas.simpleapp.ui.theme.Orange500
import com.ihridoydas.simpleapp.features.timeLineCompose.entities.HiringStage
import com.ihridoydas.simpleapp.features.timeLineCompose.entities.HiringStageStatus
import com.ihridoydas.simpleapp.features.timeLineCompose.timeline.models.LineParameters
import com.ihridoydas.simpleapp.features.timeLineCompose.timeline.models.StrokeParameters
import com.ihridoydas.simpleapp.features.timeLineCompose.timeline.TimelineNode
import com.ihridoydas.simpleapp.features.timeLineCompose.timeline.defaults.CircleParametersDefaults
import com.ihridoydas.simpleapp.features.timeLineCompose.timeline.defaults.LineParametersDefaults
import com.ihridoydas.simpleapp.features.timeLineCompose.timeline.models.TimelineNodePosition

@Composable
fun LazyTimeline(stages: Array<HiringStage>) {
    LazyColumn(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        content = {
            itemsIndexed(stages) { index, hiringStage ->
                TimelineNode(
                    position = mapToTimelineNodePosition(index, stages.size),
                    circleParameters = CircleParametersDefaults.circleParameters(
                        backgroundColor = getIconColor(hiringStage),
                        stroke = getIconStrokeColor(hiringStage),
                        icon = getIcon(hiringStage)
                    ),
                    lineParameters = getLineBrush(
                        circleRadius = 12.dp,
                        index = index,
                        items = stages
                    ),
                    contentStartOffset = 16.dp,
                    spacer = 24.dp
                ) { modifier ->
                    Message(hiringStage, modifier)
                }
            }
        },
        contentPadding = PaddingValues(16.dp)
    )
}

@Composable
private fun getLineBrush(circleRadius: Dp, index: Int, items: Array<HiringStage>): LineParameters? {
    return if (index != items.lastIndex) {
        val currentStage: HiringStage = items[index]
        val nextStage: HiringStage = items[index + 1]
        val circleRadiusInPx = with(LocalDensity.current) { circleRadius.toPx() }
        LineParametersDefaults.linearGradient(
            strokeWidth = 3.dp,
            startColor = (getIconStrokeColor(currentStage)?.color ?: getIconColor(currentStage)),
            endColor = (getIconStrokeColor(nextStage)?.color ?: getIconColor(items[index + 1])),
            startY = circleRadiusInPx * 2
        )
    } else {
        null
    }
}

private fun getIconColor(stage: HiringStage): Color {
    return when (stage.status) {
        HiringStageStatus.FINISHED -> Green500
        HiringStageStatus.CURRENT -> Orange500
        HiringStageStatus.UPCOMING -> Color.White
    }
}

private fun getIconStrokeColor(stage: HiringStage): StrokeParameters? {
    return if (stage.status == HiringStageStatus.UPCOMING) {
        StrokeParameters(color = Gray200, width = 2.dp)
    } else {
        null
    }
}

@Composable
private fun getIcon(stage: HiringStage): Int? {
    return if (stage.status == HiringStageStatus.CURRENT) {
        R.drawable.ic_bubble_warning_16
    } else {
        null
    }
}

private fun mapToTimelineNodePosition(index: Int, collectionSize: Int) = when (index) {
    0 -> TimelineNodePosition.FIRST
    collectionSize - 1 -> TimelineNodePosition.LAST
    else -> TimelineNodePosition.MIDDLE
}