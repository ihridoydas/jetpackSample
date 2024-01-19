package com.ihridoydas.simpleapp.util.responsiveUI.component.animations.pullToRefreshLoadingAnimation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import com.ihridoydas.simpleapp.ui.theme.ThemeColor
import com.ihridoydas.simpleapp.util.responsiveUI.component.draggableMenu.DraggableMenuUse
import com.ihridoydas.simpleapp.util.responsiveUI.component.compose_ProgressIndicator.useCases.ProgressIndicatorDemo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//List of Screen
val list = listOf("CustomPull", "FancyPull","DraggableMenu","ProgressIndicatorDemo")

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PullToRefreshAnimationTabsContent(pagerState: PagerState) {

    Surface(
        modifier = Modifier.fillMaxSize(), color = ThemeColor
    ) {
        HorizontalPager(
            modifier = Modifier,
            state = pagerState,
            pageSpacing = 0.dp,
            userScrollEnabled = true,
            reverseLayout = false,
            contentPadding = PaddingValues(0.dp),
            beyondBoundsPageCount = 0,
            pageSize = PageSize.Fill,
            flingBehavior = PagerDefaults.flingBehavior(state = pagerState),
            key = null,
            pageNestedScrollConnection = remember(pagerState) {
                PagerDefaults.pageNestedScrollConnection(pagerState, Orientation.Horizontal)
            },
            pageContent = { page ->
                when (page) {
                    0 -> CustomPullRefresh()
                    1 -> FancyPullToRefresh()
                    2 -> DraggableMenuUse()
                    3 -> Column {
                        ProgressIndicatorDemo()
                    }
                }

            }
        )

    }

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PullRefreshAnimations(onBackPress: () -> Unit) {

    Scaffold(topBar = {
        TopAppBar(
            colors = topAppBarColors(
                Color.White
            ),
            title = {
                Text(
                    text = "Pull To Refresh Animations", style = TextStyle(color = Color.Black)
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        onBackPress()
                    }, modifier = Modifier
                ) {
                    Icon(
                        Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black
                    )
                }
            },
        )
    }, drawerShape = RoundedCornerShape(topEnd = 23.dp, bottomEnd = 23.dp), content = {
        Column(
            modifier = Modifier.padding(it),
        ) {
            val pagerState = rememberPagerState(
                initialPage = 0,
                initialPageOffsetFraction = 0f
            ) {
                // provide pageCount
                list.size
            }
            PullToRefreshAnimationTabsContent(pagerState = pagerState)
        }
    })
}


@ExperimentalFoundationApi
@Composable
fun CustomPullRefresh() {

    val scope = rememberCoroutineScope()
    var isRefreshing by remember { mutableStateOf(false) }
    val refresh = remember {
        {
            scope.launch {
                isRefreshing = true
                delay(3000)
                isRefreshing = false
            }
        }
    }

    CustomPullToRefresh(isRefreshing = isRefreshing, onRefresh = { refresh() }) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 10.dp)
        ) {
            items(100) { index ->
                ListItem(index = index)
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun FancyPullToRefresh() {

    val scope = rememberCoroutineScope()
    var isRefreshing by remember { mutableStateOf(false) }
    val refresh = remember {
        {
            scope.launch {
                isRefreshing = true
                delay(3000)
                isRefreshing = false
            }
        }
    }

    FancyPullToRefresh(isRefreshing = isRefreshing, onRefresh = { refresh() }) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 10.dp)
        ) {
            items(100) { index ->
                ListItem(index = index)
            }
        }
    }
}

@Composable
fun ListItem(index: Int) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {

        Box(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .width(130.dp)
                    .aspectRatio(3 / 2f),
                contentScale = ContentScale.Crop,
                painter = rememberAsyncImagePainter(
                    model = "https://picsum.photos/id/${(index + 2) * 5}/200/300",
                ),
                contentDescription = "image_$index"
            )

            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
            ) {
                if (index % 3 == 0) Text(
                    text = "TOP RATED",
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.primary,
                )
                Text(
                    text = when (index) {
                        5 -> "This is Mambo..."
                        else -> "This is $index"
                    },
                    style = MaterialTheme.typography.h1,
                )
                Text(
                    text = when (index) {
                        5 -> "... number five"
                        else -> "This is the body of the number $index"
                    },
                    style = MaterialTheme.typography.body1,
                )

            }
        }
        Box(modifier = Modifier.height(8.dp))
        Divider()

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun LoadingAnimationAppPreview() {
    SimpleAppTheme {
        FancyPullToRefresh()
    }

}