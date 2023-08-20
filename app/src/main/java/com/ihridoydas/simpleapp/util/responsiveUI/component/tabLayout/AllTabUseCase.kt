package com.ihridoydas.simpleapp.util.responsiveUI.component.tabLayout

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.twotone.MoreVert
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.ihridoydas.simpleapp.ui.theme.ThemeColor
import com.ihridoydas.simpleapp.util.responsiveUI.component.casecade.use.Menu
import com.ihridoydas.simpleapp.util.responsiveUI.component.tabLayout.multiSelector.PreviewMultiSelector
import com.ihridoydas.simpleapp.util.responsiveUI.component.tabLayout.view.TabBar
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

//List of Screen
val listOfPager = listOf("TabBar","MultiSelector")

@Composable
fun TabsLayoutContent(pagerState: PagerState) {

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White
    ) {
        HorizontalPager(state = pagerState, count = listOfPager.size) { page ->
            when (page) {
                0 -> TabBar()
                1 -> PreviewMultiSelector()

            }

        }

    }

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun TabBarsScreen(onBackPress: ()-> Unit) {

    val snackbarHostState = remember { SnackbarHostState() }
    val channel = remember { Channel<String>(Channel.CONFLATED) }
    val (isOpen, setIsOpen) = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = channel) {
        channel.receiveAsFlow().collect {
            snackbarHostState.showSnackbar(it)
        }
    }

    Scaffold(
        scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState),
        drawerShape = RoundedCornerShape(topEnd = 23.dp, bottomEnd = 23.dp),
        topBar = {
            TopAppBar(
                title = { Text(text = "Tab Bar") },
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
                actions = {
                    Menu(isOpen = isOpen, setIsOpen = setIsOpen, itemSelected = {
                        channel.trySend(it)
                        setIsOpen(false)
                    })
                    IconButton(
                        onClick = { setIsOpen(true) }) {
                        Icon(
                            imageVector = Icons.TwoTone.MoreVert,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it),
            ) {
                val pagerState = rememberPagerState(0)
                TabsLayoutContent(pagerState = pagerState)
            }
        }
    )
}

