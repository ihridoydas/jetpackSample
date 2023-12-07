package com.ihridoydas.simpleapp.util.responsiveUI.component.horizontalPagerIndicator

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerStepThree() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val pageCount = 5
        val pagerState = rememberPagerState(
            pageCount = { pageCount },
        )
        val indicatorScrollState = rememberLazyListState()

        LaunchedEffect(key1 = pagerState.currentPage, block = {
            val currentPage = pagerState.currentPage
            val size = indicatorScrollState.layoutInfo.visibleItemsInfo.size
            val lastVisibleIndex =
                indicatorScrollState.layoutInfo.visibleItemsInfo.last().index
            val firstVisibleItemIndex = indicatorScrollState.firstVisibleItemIndex

            if (currentPage > lastVisibleIndex - 1) {
                indicatorScrollState.animateScrollToItem(currentPage - size + 2)
            } else if (currentPage <= firstVisibleItemIndex + 1) {
                indicatorScrollState.animateScrollToItem(Math.max(currentPage - 1, 0))
            }
        })
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Page $it")
            }
        }
        LazyRow(
            state = indicatorScrollState,
            modifier = Modifier
                .height(50.dp)
                .width(((6 + 16) * 2 + 3 * (10 + 16)).dp), // I'm hard computing it to simplify
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                item(key = "item$iteration") {
                    val currentPage = pagerState.currentPage
                    val firstVisibleIndex by remember { derivedStateOf { indicatorScrollState.firstVisibleItemIndex } }
                    val lastVisibleIndex = indicatorScrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                    val size by animateDpAsState(
                        targetValue = if (iteration == currentPage) {
                            10.dp
                        } else if (iteration in firstVisibleIndex + 1..lastVisibleIndex - 1) {
                            10.dp
                        } else {
                            6.dp
                        }, label = ""
                    )
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .background(color, CircleShape)
                            .size(
                                size
                            )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HorizontalPagerWithIndicatorPreview() {
    SimpleAppTheme {
        PagerStepThree()
    }

}