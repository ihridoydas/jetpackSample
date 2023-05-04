
package com.ihridoydas.simpleapp.newtonstimer

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ihridoydas.simpleapp.features.newTonsTimer.timer.ButtonsBar
import com.ihridoydas.simpleapp.features.newTonsTimer.timer.TimerState
import com.ihridoydas.simpleapp.features.newTonsTimer.timer.TimerViewModel
import com.ihridoydas.simpleapp.features.newTonsTimer.ui.theme.MyTheme
import com.ihridoydas.simpleapp.ui.MainActivity
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ButtonsBarScreenshotTests : ScreenshotTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var timerViewModel: TimerViewModel

    @Test
    fun rendersTheComponentInNoConfiguredState() {
        renderComponent(TimerState.NotConfigured())

        compareScreenshot(composeTestRule)
    }

    @Test
    fun rendersTheComponentInPausedState() {
        renderComponent(TimerState.Configured.Paused(1000L))

        compareScreenshot(composeTestRule)
    }

    @Test
    fun rendersTheComponentInRunningState() {
        renderComponent(TimerState.Configured.Running(1000L))

        compareScreenshot(composeTestRule)
    }

    private fun renderComponent(state: TimerState) {
        composeTestRule.setContent {
            timerViewModel = viewModel()
            MyTheme(darkMode = timerViewModel.darkMode) {
                ButtonsBar(viewModel = timerViewModel, state = state)
            }
        }
    }
}
