package com.ihridoydas.simpleapp.util.showcase

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.navigation.animationNavHost.ScreenDestinations
import com.ihridoydas.simpleapp.ui.theme.GreenColor
import com.ihridoydas.simpleapp.util.showcase.onBoarding.OnBoardingViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

//https://blog.canopas.com/intro-showcase-view-in-jetpack-compose-ac044cd3bf28

@Composable
fun ShowcaseSample(windowSizeClass: WindowSizeClass, navController: NavController, onBoardingViewModel: OnBoardingViewModel) {
    var showAppIntro by remember {
        mutableStateOf(true)
    }
    val showCaseShowOrNot = runBlocking {
        onBoardingViewModel.getOnShowCaseCompleted.first()
    }

    IntroShowCaseScaffold(
        showIntroShowCase = showCaseShowOrNot ?: false,
        onShowCaseCompleted = {
            //App Intro finished!!
            showAppIntro = false
        },
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { },
                    backgroundColor = Color.Transparent,
                    elevation = 0.dp,
                    navigationIcon = {
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .introShowCaseTarget(
                                    index = 4,
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
                            onClick = {},
                            modifier = Modifier
                                .introShowCaseTarget(
                                index = 2,
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
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {},
                    modifier = Modifier
                        .introShowCaseTarget(
                            index = 1,
                            style = ShowcaseStyle.Default.copy(
                                backgroundColor = Color(0xFF1C0A00), // specify color of background
                                backgroundAlpha = 0.98f, // specify transparency of background
                                targetCircleColor = Color.White // specify color of target circle
                            ),
                            // specify the content to show to introduce app feature
                            content = {
                                Column {
                                    Text(
                                        text = "Check emails",
                                        color = Color.White,
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "Click here to check/send emails",
                                        color = Color.White,
                                        fontSize = 16.sp
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Icon(
                                        painterResource(id = R.drawable.right_arrow),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(80.dp)
                                            .align(Alignment.End),
                                        tint = Color.White
                                    )
                                }
                            }
                        ),
                    backgroundColor = GreenColor,
                    contentColor = Color.White,
                    elevation = FloatingActionButtonDefaults.elevation(3.dp)
                ) {
                    Icon(
                        Icons.Filled.Email,
                        contentDescription = "Email",
                        tint = Color.White
                    )
                }
            }
        ) {paddingValues->
            Box(modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()) {
                Box(modifier = Modifier.fillMaxHeight(0.3f)) {

                    Column(
                        Modifier
                            .align(Alignment.BottomStart)
                            .fillMaxWidth()
                            .padding(16.dp)
                            .height(90.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "Intro Showcase view", fontWeight = FontWeight.Bold,
                            fontSize = 24.sp, color = MaterialTheme.colors.primaryVariant
                        )
                        Text(
                            text = "This is an example of Intro Showcase view",
                            fontSize = 20.sp, color = Color.Black, textAlign = TextAlign.Center
                        )

                    }

                    Image(
                        painter = painterResource(id = R.drawable.ic_unknown_profile),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .clip(CircleShape)
                            .introShowCaseTarget(
                                index = 0, // specify index to show feature in order
                                // ShowcaseStyle is optional
                                style = ShowcaseStyle.Default.copy(
                                    backgroundColor = Color(0xFFFFCC80), // specify color of background
                                    backgroundAlpha = 0.98f, // specify transparency of background
                                    targetCircleColor = Color.White // specify color of target circle
                                ),
                                content = {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 20.dp)
                                    ) {
                                        Text(
                                            text = "User profile",
                                            color = Color.White,
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "Click here to update your profile",
                                            color = Color.White,
                                            fontSize = 16.sp
                                        )
                                    }
                                }
                            )
                    )
                }

                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = GreenColor,
                        contentColor = Color.White
                    ),
                    onClick = {
                        onBoardingViewModel.setOnShowCaseCompleted(false)
                        onBoardingViewModel.setIsStartScreenCover()
                        navController?.navigate(ScreenDestinations.BoardingScreen.route) {
                            popUpTo(ScreenDestinations.HomeScreen.route) {
                                inclusive = true
                            }
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 16.dp, bottom = 16.dp)
                        .introShowCaseTarget(
                            index = 3,
                            content = {
                                Column {
                                    Text(
                                        text = "Follow me",
                                        color = Color.White,
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "Click here to follow",
                                        color = Color.White,
                                        fontSize = 16.sp
                                    )
                                }
                            }
                        )
                ) {
                    Text(text = "Follow")
                }
            }
        }
    }
}
