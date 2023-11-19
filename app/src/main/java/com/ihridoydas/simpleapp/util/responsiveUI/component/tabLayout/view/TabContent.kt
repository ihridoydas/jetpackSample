package com.ihridoydas.simpleapp.util.responsiveUI.component.tabLayout.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuOpen
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import com.ihridoydas.simpleapp.ui.theme.seed
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar() {
    TopAppBar(
        title = { Text(text = "Tab Layout", color = Color.White) },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.MenuOpen,
                contentDescription = null,
                tint = Color.White
            )
        },
    )
}

//List of Screen
val list = listOf("Home", "About", "Portfolio")
val lists = listOf(
    Icons.Filled.Home,
    Icons.Filled.Person,
    Icons.Filled.Work,
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsContent(pagerState: PagerState) {
    HorizontalPager(state = pagerState) {
        when (it) {
            0 -> TabScreenOne(tabName = "This is a Home Layout")
            1 -> TabScreenTwo(tabName = "This is a About Layout")
            2 -> TabScreenThree(tabName = "This is a Portfolio Layout")

        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabScreen() {
    val pagerState = rememberPagerState {
        list.size
    }
    Column(modifier = Modifier.background(Color.White)) {
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState)
    }

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tabs(pagerState: PagerState) {

    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        containerColor = seed,
        contentColor = Color.White,
        indicator = { tabpositions ->
            SecondaryIndicator(
                Modifier.tabIndicatorOffset(tabpositions[pagerState.currentPage]),
                height = 3.dp,
                color = Color.Red

            )
        }
    ) {
        list.forEachIndexed { index, _ ->
            Tab(
                text = {
                    Text(
                        text = list[index],
                        color = if (pagerState.currentPage == index) Color.White else Color.LightGray
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                icon = {
                    Icon(imageVector = lists[index], contentDescription = null)
                }
            )

        }


    }

}


@Preview(showBackground = true)
@Composable
fun TabLayoutActivityPreview() {
    SimpleAppTheme() {
        Toolbar()
        TabScreen()

    }
}