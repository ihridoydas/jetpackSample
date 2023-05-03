package com.ihridoydas.simpleapp.ui.screens.boardingScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.navigation.animationNavHost.ScreenDestinations
import com.ihridoydas.simpleapp.util.showcase.IntroShowCaseScaffold
import com.ihridoydas.simpleapp.util.showcase.ShowcaseStyle
import com.ihridoydas.simpleapp.util.showcase.onBoarding.BottomSection
import com.ihridoydas.simpleapp.util.showcase.onBoarding.OnBoardingItem
import com.ihridoydas.simpleapp.util.showcase.onBoarding.OnBoardingViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingScreen(windowSizeClass: WindowSizeClass, navController: NavController, onBackPress : ()->Unit) {

    // スクロールの行動 (scrollBehavior)
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)

    val scope= rememberCoroutineScope()
    //OnBoarding ViewModel
    val onBoardingViewModel : OnBoardingViewModel = hiltViewModel()

    var showSkipAppIntro by remember {
        mutableStateOf(true)
    }
    val showSkipShowOrNot = runBlocking {
        onBoardingViewModel.getOnSkipCaseCompleted.first()
    }
    IntroShowCaseScaffold(
        showIntroShowCase = showSkipShowOrNot ?: false,
        onShowCaseCompleted = {
            //App Intro finished!!
            showSkipAppIntro = false
        },
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    modifier = Modifier,
                    title = { },
                    backgroundColor = Color.Transparent,
                    elevation = 0.dp,
                    navigationIcon = {
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .introShowCaseTarget(
                                    index = 0,
                                    style = ShowcaseStyle.Default.copy(
                                        backgroundColor = Color(0xFF7C99AC), // specify color of background
                                        backgroundAlpha = 0.98f, // specify transparency of background
                                        targetCircleColor = Color.White // specify color of target circle
                                    ),
                                    content = {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Image(
                                                painterResource(id = R.drawable.go_back),
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .size(100.dp)
                                                    .padding(top = 10.dp)
                                            )
                                            Column {
                                                Text(
                                                    text = "Go back!!",
                                                    color = Color.White,
                                                    fontSize = 24.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                                Text(
                                                    modifier = Modifier
                                                        .padding(vertical = 10.dp),
                                                    text = "You can go back by clicking here.",
                                                    color = Color.White,
                                                    fontSize = 16.sp
                                                )
                                            }
                                        }
                                    },
                                )
                        ) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Search")
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                onBoardingViewModel.setOnSkipCaseCompleted(false)
                                navController?.navigate(ScreenDestinations.ViewScreen.route) {
                                    popUpTo(ScreenDestinations.HomeScreen.route) {
                                        inclusive = true
                                    }
                                }
                            },
                            modifier = Modifier
                                .introShowCaseTarget(
                                    index = 1,
                                    style = ShowcaseStyle.Default.copy(
                                        backgroundColor = Color(0xFF9AD0EC), // specify color of background
                                        backgroundAlpha = 0.98f, // specify transparency of background
                                        targetCircleColor = Color.White // specify color of target circle
                                    ),
                                    content = {
                                        Column {
                                            Image(
                                                painterResource(id = R.drawable.search_example),
                                                contentDescription = null,
                                                modifier = Modifier.size(100.dp)
                                            )

                                            Text(
                                                text = "Search anything!!",
                                                color = Color.Black,
                                                fontSize = 24.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(
                                                text = "You can search anything by clicking here.",
                                                color = Color.Black,
                                                fontSize = 16.sp
                                            )
                                        }
                                    }
                                )
                        ) {
                            Icon(Icons.Filled.Search, contentDescription = "Search")
                        }
                    }
                )
            },
            content = {
                Column(Modifier.padding(it).fillMaxSize()) {

                    val items=OnBoardingItem.get()
                    val state= rememberPagerState(initialPage = 0)

                    HorizontalPager(
                        count = items.size,
                        state = state,
                        modifier= Modifier
                            .fillMaxSize()
                            .weight(0.8f)
                    ) {page->

                        OnBoardingItem(items[page])

                    }

                    BottomSection(size = items.size, index = state.currentPage) {
                        if (state.currentPage+1 <items.size)
                            scope.launch {
                                state.scrollToPage(state.currentPage+1)
                            }
                    }

                }
            }
        )
    }
}

