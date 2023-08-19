package com.ihridoydas.simpleapp.features.timeLineCompose.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import com.ihridoydas.simpleapp.features.timeLineCompose.entities.HiringStage
import com.ihridoydas.simpleapp.features.timeLineCompose.entities.HiringStageStatus
import com.ihridoydas.simpleapp.features.timeLineCompose.entities.MessageSender
import java.time.LocalDate

@Composable
internal fun Message(
    hiringStage: HiringStage,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.65f)
                .align(getBoxAlign(hiringStage)),
            colors = CardDefaults.cardColors(containerColor = getBackgroundColor(hiringStage))
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(12.dp),
                text = hiringStage.initiator.message,
                textAlign = getTextAlign(hiringStage),
                style = getTextStyle(hiringStage),
                fontWeight = getFontWeight(hiringStage),
                color = getTextColor(hiringStage)
            )
        }
    }
}

@Composable
private fun getBoxAlign(hiringStage: HiringStage) =
    if (hiringStage.initiator is MessageSender.Candidate) {
        Alignment.CenterEnd
    } else {
        Alignment.CenterStart
    }

@Composable
private fun getTextAlign(hiringStage: HiringStage) =
    if (hiringStage.initiator is MessageSender.Candidate) {
        TextAlign.End
    } else {
        TextAlign.Start
    }

@Composable
private fun getBackgroundColor(hiringStage: HiringStage) = when (hiringStage.status) {
    HiringStageStatus.UPCOMING -> MaterialTheme.colorScheme.surface.copy(alpha = 0.3f)
    HiringStageStatus.CURRENT -> MaterialTheme.colorScheme.tertiary.copy(alpha = 0.3f)
    else -> MaterialTheme.colorScheme.surfaceVariant
}

@Composable
private fun getTextColor(hiringStage: HiringStage) =
    if (hiringStage.status == HiringStageStatus.UPCOMING) {
        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.63f)
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

@Composable
private fun getFontWeight(hiringStage: HiringStage) =
    if (hiringStage.status == HiringStageStatus.CURRENT) {
        W500
    } else {
        W400
    }

@Composable
private fun getTextStyle(hiringStage: HiringStage) =
    if (hiringStage.status == HiringStageStatus.CURRENT) {
        MaterialTheme.typography.bodyLarge
    } else {
        MaterialTheme.typography.bodyMedium
    }

@Preview(showBackground = true)
@Composable
private fun MessagePreview() {
    SimpleAppTheme {
        Message(
            hiringStage = HiringStage(
                date = LocalDate.now(),
                initiator = MessageSender.Candidate(
                    initials = "JD",
                    message = "Hi! I will be glad to join DreamCompany team. I've sent you my CV."
                ),
                status = HiringStageStatus.CURRENT
            ),
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UpcomingStageMessagePreview() {
    SimpleAppTheme {
        Message(
            hiringStage = HiringStage(
                date = LocalDate.now(),
                initiator = MessageSender.Candidate(
                    initials = "JD",
                    message = "Coming soon"
                ),
                status = HiringStageStatus.UPCOMING
            ),
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FinishedMessagePreview() {
    SimpleAppTheme {
        Message(
            modifier = Modifier,
            hiringStage = HiringStage(
                date = LocalDate.now(),
                initiator = MessageSender.Candidate(
                    initials = "JD",
                    message = "Hi! I will be glad to join DreamCompany team. I've sent you my CV."
                ),
                status = HiringStageStatus.FINISHED
            )
        )
    }
}