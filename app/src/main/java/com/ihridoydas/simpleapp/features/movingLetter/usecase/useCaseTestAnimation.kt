package com.ihridoydas.simpleapp.features.movingLetter.usecase

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.features.movingLetter.FadeAnimatedText
import com.ihridoydas.simpleapp.features.movingLetter.JumpAnimatedText
import com.ihridoydas.simpleapp.features.movingLetter.RotateAnimatedText
import com.ihridoydas.simpleapp.features.movingLetter.ScaleInAnimatedText
import com.ihridoydas.simpleapp.features.movingLetter.ScaleOutAnimatedText
import com.ihridoydas.simpleapp.ui.theme.ThemeColor


//List of Screen
val list = listOf("ScaleInAnimatedText","ScaleOutAnimatedText","FadeAnimatedText",
    "JumpAnimatedText","RotateAnimatedText")


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MovingTestContent(pagerState: PagerState) {

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
                    0 -> AnimatedTextScreen(
                        name = "ScaleInAnimatedText",
                        content = { state, text ->
                            ScaleInAnimatedText(
                                state = state,
                                text = text,
                                style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Black),
                                animateOnMount = false
                            )
                        },
                        contentColor = Color(0xFFFFFFFF),
                        containerColor = Color(0xFF9CA4B5)
                    )
                    1 ->  AnimatedTextScreen(
                        name = "ScaleOutAnimatedText",
                        content = { state, text ->
                            ScaleOutAnimatedText(
                                state = state,
                                text = text,
                                style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Black),
                                animateOnMount = false
                            )
                        },
                        contentColor = Color(0xFFFFFFFF),
                        containerColor = Color(0xFFE7C3B9)
                    )
                    2 ->  AnimatedTextScreen(
                        name = "FadeAnimatedText",
                        content = { state, text ->
                            FadeAnimatedText(
                                state = state,
                                text = text,
                                style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Black),
                                animateOnMount = false
                            )
                        },
                        contentColor = Color(0xFFFFFFFF),
                        containerColor = Color(0xFF234A54)
                    )
                    3 -> AnimatedTextScreen(
                        name = "JumpAnimatedText",
                        content = { state, text ->
                            JumpAnimatedText(
                                state = state,
                                text = text,
                                style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Black),
                                animateOnMount = false
                            )
                        },
                        contentColor = Color(0xFFFFFFFF),
                        containerColor = Color(0xFFC1605D)
                    )
                    4 ->  AnimatedTextScreen(
                        name = "RotateAnimatedText",
                        content = { state, text ->
                            RotateAnimatedText(
                                state = state,
                                text = text,
                                style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Black),
                                animateOnMount = false
                            )
                        },
                        contentColor = Color(0xFFFFFFFF),
                        containerColor = Color(0xFF46373C)
                    )
                }
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MovingTextScreen(onBackPress: () -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
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
                MovingTestContent(pagerState = pagerState)
            }
        }
    )
}
