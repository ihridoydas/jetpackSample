package com.ihridoydas.simpleapp.util.responsiveUI.component.lazyColumnWithScrollbar

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.ihridoydas.simpleapp.features.quiz.QuizApp
import com.ihridoydas.simpleapp.util.responsiveUI.component.lazyColumnScrollbar.useCase.ColumnView
import com.ihridoydas.simpleapp.util.responsiveUI.component.lazyColumnScrollbar.useCase.LazyColumnView
import com.ihridoydas.simpleapp.util.responsiveUI.component.lazyColumnScrollbar.useCase.lazyGridView
import com.ihridoydas.simpleapp.util.responsiveUI.component.lazyColumnWithScrollbar.data.getTheData
import com.ihridoydas.simpleapp.util.responsiveUI.component.lazyColumnWithScrollbar.data.getTheIndexedData
import com.ihridoydas.simpleapp.util.responsiveUI.component.lazyColumnWithScrollbar.doubleheader.ExampleDoubleHeaderList
import com.ihridoydas.simpleapp.util.responsiveUI.component.lazyColumnWithScrollbar.indexed.ExampleIndexedLazyColumn
import com.ihridoydas.simpleapp.util.responsiveUI.component.lazyColumnWithScrollbar.scrollBar.ExampleLazyColumnWithScrollbar


//List of Screen
val listOfPager = listOf("doubleHeader","indexLazyColumn","lazyColumnWithScrollBar","LazyColumn","LazyGird","ColumnView")


@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class,
    ExperimentalCoilApi::class, ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class
)
@Composable
fun ScrollBarsContent(pagerState: PagerState) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White
    ) {
        HorizontalPager(state = pagerState) { page ->
            when (page) {
                0 -> ExampleDoubleHeaderList(getTheData())
                1 -> ExampleIndexedLazyColumn(getTheIndexedData())
                2 -> ExampleLazyColumnWithScrollbar(data = listOf(1,2,3,4,6,78,9,34,23,23,32,44,32,545,22,232,54,32,23,523,2,23,23,4,355,3456,878,9976,65,65,87,56,565,4,5,454,76,565,86,6,565,445,35))
                3 -> LazyColumnView()
                4 -> lazyGridView()
                5 -> ColumnView()
            }

        }

    }

}

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
fun ScrollBarScreen(
    onBackPress: () -> Unit,
) {


    Scaffold(
        drawerShape = RoundedCornerShape(topEnd = 23.dp, bottomEnd = 23.dp),
        topBar = {
            TopAppBar(
                title = { Text(text = "Scroll Bar") },
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
                ScrollBarsContent(pagerState = pagerState)
            }
        }
    )
}
