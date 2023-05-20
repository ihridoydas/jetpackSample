package com.ihridoydas.simpleapp.util.responsiveUI.component.animations.explodingAnimationTransition.animation.demoUseCase

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import dev.omkartenkale.explodable.sample.DeleteFolderScreen


@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ExplodingAnimationTrasition(onBackPress:()->Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Exploding Animation Transition") },
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
                    .padding(it)
                    .fillMaxSize()
                    .padding(vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                HorizontalPager(count = 5, state = rememberPagerState()) { pageIndex ->
                    when (pageIndex) {
                        0 -> AppUninstallScreen()
                        1 -> DeleteFolderScreen()
                        2 -> RemoveBookmarkScreen()
                        3 -> ManualAnimationControlScreen()
                        4 -> AnimationCustomizationDemoScreen()
                    }
                }

            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleAppTheme() {
        ExplodingAnimationTrasition({})
    }
}