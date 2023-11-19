package com.ihridoydas.simpleapp.features.koreography


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.features.koreography.useCases.FloatingMachineAnimation
import com.ihridoydas.simpleapp.features.koreography.useCases.LoadingAnimation
import com.ihridoydas.simpleapp.features.koreography.useCases.MeditationAnimation
import com.ihridoydas.simpleapp.features.koreography.useCases.NFCAnimation
import com.ihridoydas.simpleapp.features.koreography.useCases.RangoliAnimation
import com.ihridoydas.simpleapp.features.koreography.useCases.SatelliteAnimation
import com.ihridoydas.simpleapp.ui.theme.ThemeColor


//List of Screen
val list = listOf("FloatingMachineAnimation","NFCAnimation","LoadingAnimation",
    "SatelliteAnimation","MeditationAnimation","RangoliAnimation")


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun KoreoraphyContent(pagerState: PagerState) {

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = ThemeColor
    ) {
        Box {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                pageSpacing = 16.dp
            ) {
                when (it) {
                    0 -> FloatingMachineAnimation()
                    1 -> NFCAnimation()
                    2 -> LoadingAnimation()
                    3 -> SatelliteAnimation()
                    4 -> MeditationAnimation()
                    5 -> RangoliAnimation()
                }
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun KoreoraphyScreen(onBackPress: () -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    ThemeColor
                ),
                title = {
                    Text(
                        text = "KoreoGraphy Animations",
                        style = TextStyle(color = Color.White)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackPress()
                        },
                        modifier = Modifier
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
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
                val pagerState = rememberPagerState(0, pageCount = { list.size })
                KoreoraphyContent(pagerState = pagerState)
            }
        }
    )
}

