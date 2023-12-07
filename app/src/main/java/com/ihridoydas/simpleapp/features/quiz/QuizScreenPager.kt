package com.ihridoydas.simpleapp.features.quiz

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.twotone.MoreVert
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ihridoydas.simpleapp.features.pendulum.Pendulum
import com.ihridoydas.simpleapp.features.pendulum.PendulumApp
import com.ihridoydas.simpleapp.features.stepperCompose.StepperComposablePreview
import com.ihridoydas.simpleapp.features.timeLineCompose.HireingScreenContent
import com.ihridoydas.simpleapp.features.timeLineCompose.TimeLineViewModel
import com.ihridoydas.simpleapp.util.responsiveUI.component.casecade.use.Menu
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

//List of Screen
val listOfPager = listOf("QuizApp1","Pendulum")


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizContent(pagerState: PagerState) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White
    ) {
        HorizontalPager(state = pagerState) { page ->
            when (page) {
                0 -> QuizApp()
                1 -> PendulumApp()
            }

        }

    }

}

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun QuizScreen(
    onBackPress: () -> Unit,
) {


    Scaffold(
        drawerShape = RoundedCornerShape(topEnd = 23.dp, bottomEnd = 23.dp),
        topBar = {
            TopAppBar(
                title = { Text(text = "Quiz Screen") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackPress()
                        },
                        modifier = Modifier
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it),
            ) {
                val pagerState =
                    rememberPagerState(
                        initialPage = 0,
                        initialPageOffsetFraction = 0f,
                        pageCount = { listOfPager.size })
                QuizContent(pagerState = pagerState)
            }
        }
    )
}
