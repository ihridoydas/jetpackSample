package com.ihridoydas.simpleapp.util.responsiveUI.component.animations.othersAnimation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.ihridoydas.simpleapp.ui.theme.ThemeColor
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.waveTimerAnimation.WavesTimerAnimation
import com.ihridoydas.simpleapp.util.responsiveUI.component.shape.ticketShape.AllShape


//List of Screen
val list = listOf("ArcRotation", "CircleOffset", "ClockLoading",
    "HeartAnimation","PacmanAnimation","ProgressAnimation","RotationDotAnimation",
    "RotationTwoDotAnimation","RotatingCircle","RotatingSquare","SquareFillLoaderAnimation",
    "StepperAnimation","ThreeBounceAnimation","TwinCircleAnimation","WaveAnimation","WavesTimerAnimation","AllShape","UndoRedoAnimation")

@Composable
fun AnimationTabsContent(pagerState: PagerState) {

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = ThemeColor
    ) {
        HorizontalPager(state = pagerState, count = list.size) { page ->
            when (page) {
                0 -> ArcRotationAnimation()
                1 -> CircleOffsetAnimation()
                2 -> ClockLoading()
                3 -> HeartAnimation()
                4 -> PacmanAnimation()
                5 -> ProgressAnimation()
                6 -> RotateDotAnimation()
                7 -> RotateTwoDotsAnimation()
                8 -> RotatingCircle()
                9 -> RotatingSquare()
                10 -> SquareFillLoaderAnimation()
                11 -> StepperAnimation(Modifier)
                12 -> ThreeBounceAnimation()
                13 -> TwinCircleAnimation()
                14 -> WavesAnimation()
                15 -> WavesTimerAnimation()
                16 -> AllShape()
                17 -> UndoRedoAnimation()

            }

        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreativeAnimations(onBackPress: ()-> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(ThemeColor),
                title = { Text(text = "Creative Animations" , style = TextStyle(color = Color.White)) },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackPress()
                        },
                        modifier = Modifier
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White )
                    }
                },
            )
        },
        drawerShape = RoundedCornerShape(topEnd = 23.dp, bottomEnd = 23.dp),
        content = {
            Column(
                modifier = Modifier
                    .padding(it),
            ) {
                val pagerState = rememberPagerState(0)
                AnimationTabsContent(pagerState = pagerState)
            }
        }
    )
}

