package com.ihridoydas.simpleapp.features.timeLineCompose

import androidx.lifecycle.ViewModel
import com.ihridoydas.simpleapp.features.timeLineCompose.entities.HiringStage
import com.ihridoydas.simpleapp.features.timeLineCompose.entities.HiringStageStatus
import com.ihridoydas.simpleapp.features.timeLineCompose.entities.MessageSender
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class TimeLineViewModel @Inject constructor() : ViewModel() {

    val hiringProcessState: Flow<Array<HiringStage>> = flowOf(
        TEST_DATA
    )

    companion object {
        val TEST_DATA = arrayOf(
            HiringStage(
                date = LocalDate.now(),
                initiator = MessageSender.Candidate(
                    "VS",
                    "Hi! I will be glad to join DreamCompany team. I've sent you my CV."
                ),
                status = HiringStageStatus.FINISHED
            ),
            HiringStage(
                date = LocalDate.now(),
                initiator = MessageSender.HR(
                    "JD",
                    "Hi! Let's have a short call to discuss your expectations and experience."
                ),
                status = HiringStageStatus.FINISHED
            ),
            HiringStage(
                date = LocalDate.now().plusDays(1),
                initiator = MessageSender.System("Screening call with Jane Doe."),
                status = HiringStageStatus.FINISHED
            ),
            HiringStage(
                date = LocalDate.now().plusDays(1),
                initiator = MessageSender.System("We are waiting for your test task. It should be completed at least one day before the technical interview."),
                status = HiringStageStatus.FINISHED
            ),
            HiringStage(
                date = LocalDate.now().plusDays(7),
                initiator = MessageSender.System("Technical interview."),
                status = HiringStageStatus.FINISHED
            ),
            HiringStage(
                date = null,
                initiator = MessageSender.System("Bar raiser interview with the team."),
                status = HiringStageStatus.CURRENT
            ),
            HiringStage(
                date = null,
                initiator = MessageSender.System("Offer proposal."),
                status = HiringStageStatus.UPCOMING
            )
        )
    }
}
