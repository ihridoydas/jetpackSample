package com.ihridoydas.simpleapp.features.layoutScaffold


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.features.twoPaneSample.TwoPaneScreen
import com.ihridoydas.simpleapp.ui.MainActivity
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import com.ihridoydas.simpleapp.ui.theme.ThemeColor


//List of Screen
val list = listOf("LayoutScaffold","TwoPaneScreen")


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LayoutScaffoldContent(pagerState: PagerState,activity : MainActivity) {

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
                    0 -> LayoutScaffoldApp()
                    1 -> TwoPaneScreen(activity)
                }
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun LayoutScaffoldScreen(onBackPress: () -> Unit,activity : MainActivity) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    ThemeColor
                ),
                title = {
                    Text(
                        text = "LayoutScaffold",
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
                LayoutScaffoldContent(pagerState = pagerState,activity)
            }
        }
    )
}


@Preview(device=Devices.TABLET)
@Composable
fun LayoutScaffoldApp() {
    SimpleAppTheme {
        LayoutScaffold(
            portraitNavigationBar = {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(16.dp)
                ) {
                    Text(text = "Navbar", color = MaterialTheme.colorScheme.onSurface)
                }
            },
            landscapeNavigationBar = {
                Column(modifier = Modifier
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(16.dp)
                ) {
                    Text(text = "Navbar", color = MaterialTheme.colorScheme.onSurface)
                }
            }
        ){isTablet, inLandscape ->
            Column(modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp)
            ) {

                Text(text = "Is Tablet: $isTablet", color = MaterialTheme.colorScheme.onSurface)
                Text(text = "In Landscape: $inLandscape", color = MaterialTheme.colorScheme.onSurface)
            }
        }

    }

}