package com.ihridoydas.simpleapp.util.responsiveUI.component.animations.explodingAnimationTransition.animation.demoUseCase

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import dev.omkartenkale.explodable.sample.DeleteFolderScreen


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ScreenContent() {
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleAppTheme() {
        ScreenContent()
    }
}