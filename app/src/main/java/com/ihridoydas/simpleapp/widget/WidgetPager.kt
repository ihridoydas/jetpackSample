package com.ihridoydas.simpleapp.widget

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
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.widget.glance.DayaGlanceApp


//List of Screen
val listOfPager = listOf("WarOfDays")


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WarOfDaysGlanceContent(pagerState: PagerState) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White
    ) {
        HorizontalPager(state = pagerState) { page ->
            when (page) {
                0 -> DayaGlanceApp()
            }

        }

    }

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun WidgetPager(
    onBackPress: () -> Unit,
) {
    Scaffold(
        drawerShape = RoundedCornerShape(topEnd = 23.dp, bottomEnd = 23.dp),
        topBar = {
            TopAppBar(
                title = { Text(text = "Widget Glance") },
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
                WarOfDaysGlanceContent(pagerState = pagerState)
            }
        }
    )
}