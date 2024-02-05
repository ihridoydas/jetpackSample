package com.ihridoydas.simpleapp.util.responsiveUI.component.animations.dragAndDrop

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.remember
import com.ihridoydas.simpleapp.features.chipTextField.ChipTextFieldScreen
import com.ihridoydas.simpleapp.features.spinner.SpinView
import com.ihridoydas.simpleapp.ui.theme.ThemeColor
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.dragAndDrop.businessCardDesign.BusinessCardDragAndDropContent
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.dragAndDrop.simpleDragAndDrop.SimpleDragAndDrop


//List of Screen
val list = listOf("SimpleDragDrop", "BusinessDragAndDrop","ChipInCompose","Spinner")

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DragAndDropTabsContent(pagerState: androidx.compose.foundation.pager.PagerState) {

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = ThemeColor
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
            pageContent = {page->
                when (page) {
                    0 -> SimpleDragAndDrop()
                    1 -> BusinessCardDragAndDropContent()
                    2 -> ChipTextFieldScreen()
                    3 -> SpinView()
                }

            }
        )

    }

}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DragAndDropUseCase(onBackPress: () -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Drag and Drop") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackPress()
                        },
                        modifier = Modifier
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
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
                val pagerState = rememberPagerState(
                    initialPage = 0,
                    initialPageOffsetFraction = 0f
                ) {
                    // provide pageCount
                    list.size
                }
                DragAndDropTabsContent(pagerState = pagerState)
            }

        }
    )


}