
package com.ihridoydas.simpleapp.features.newTonsTimer.timer

import androidx.activity.result.launch
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ihridoydas.simpleapp.features.newTonsTimer.balls.SHADOW_TOP_OFFSET
import com.ihridoydas.simpleapp.features.newTonsTimer.balls.SwingingBallsContainer
import com.ihridoydas.simpleapp.features.newTonsTimer.sinDegree
import com.ihridoydas.simpleapp.features.newTonsTimer.timer.TimerViewModel.Companion.MAX_ANGLE
import com.ihridoydas.simpleapp.features.newTonsTimer.ui.isLandscape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewtonsTimerScreen(onBackPress: ()-> Unit) {
    val timerViewModel: TimerViewModel = viewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "NewTon Timer") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackPress()
                        },
                        modifier = Modifier
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
        drawerShape = RoundedCornerShape(topEnd = 23.dp, bottomEnd = 23.dp),
        content = {
            BoxWithConstraints (modifier = Modifier.padding(it)){
                if (isLandscape) {
                    NewtonsTimerLandscape(timerViewModel)
                } else {
                    NewtonsTimerPortrait(timerViewModel)
                }
            }
        }
    )
}

@Composable
private fun NewtonsTimerPortrait(viewModel: TimerViewModel) {
    Column {
        Column(
            modifier = Modifier
                .animateContentSize()
                .weight(0.88f)
        ) {
            val ballsOuterRatio by animateFloatAsState(
                when (viewModel.isConfigured) {
                    true -> 1.1f * sinDegree(MAX_ANGLE) + BALLS_INNER_ASPECT_RATIO_PORTRAIT
                    else -> sinDegree(MAX_ANGLE) + (BALLS_INNER_ASPECT_RATIO_PORTRAIT / 2f)
                }, label = ""
            )
            SwingingBallsContainer(
                viewModel = viewModel,
                ballsInnerRatio = BALLS_INNER_ASPECT_RATIO_PORTRAIT,
                modifier = Modifier.aspectRatio(ballsOuterRatio)
            )
            Display(
                viewModel = viewModel,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            )
        }
        ButtonsBar(
            viewModel = viewModel,
            state = viewModel.state,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .weight(0.1f)
        )
    }
}

@Composable
private fun NewtonsTimerLandscape(viewModel: TimerViewModel) {
    Row {
        Column(
            modifier = Modifier
                .animateContentSize()
                .weight(1.2f)
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.weight(0.25f))
            Display(
                viewModel = viewModel,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.weight(1f))
            ButtonsBar(
                viewModel = viewModel,
                state = viewModel.state,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.weight(0.3f))
        }

        SwingingBallsContainer(
            viewModel = viewModel,
            ballsInnerRatio = BALLS_INNER_ASPECT_RATIO_LANDSCAPE,
            modifier = Modifier
                .weight(1f)
                .padding(bottom = SHADOW_TOP_OFFSET + 24.dp)
        )
    }
}

private const val BALLS_INNER_ASPECT_RATIO_PORTRAIT = 0.5f
private const val BALLS_INNER_ASPECT_RATIO_LANDSCAPE = 0.55f
