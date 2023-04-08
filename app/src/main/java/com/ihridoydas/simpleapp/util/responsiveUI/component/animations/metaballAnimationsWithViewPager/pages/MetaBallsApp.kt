@file:OptIn(ExperimentalPagerApi::class)

package com.ihridoydas.simpleapp.util.responsiveUI.component.animations.metaballAnimationsWithViewPager.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.metaballAnimationsWithViewPager.*


//Reference -> https://www.sinasamaki.com/5-metaball-animations-in-jetpack-compose/
//With ViewPager

class Page(val content: @Composable () -> Unit)

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
val pages = listOf(
    Page { SwipeDismiss() },
    Page { BottomNavigationAnimation() },
    Page { Switch() },
    Page { ProgressLoader() },
    Page { TextChange() },
    Page { ExpandableFAB() },
)


@OptIn(ExperimentalPagerApi::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MetaBallsApp() {
    HorizontalPager(count = pages.size) { page ->
        pages[page].content()
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview
@Composable
fun APPPreview() {
    SimpleAppTheme {
        MetaBallsApp()
    }
    
}