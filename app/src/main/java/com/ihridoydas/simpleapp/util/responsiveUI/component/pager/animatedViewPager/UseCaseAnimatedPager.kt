package com.ihridoydas.simpleapp.util.responsiveUI.component.pager.animatedViewPager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import com.ihridoydas.simpleapp.util.responsiveUI.component.pager.animatedViewPager.components.AnimatedViewPager

@Preview(
    showSystemUi = true,
    showBackground = true,
)
@Composable
internal fun AnimaterPagerScreen(modifier: Modifier = Modifier) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val pageWidth = (screenWidth / 3f).dp
    val drawables = listOf(
        R.drawable.greggs1,
        R.drawable.greggs2,
        R.drawable.greggs3,
        R.drawable.greggs4,
        R.drawable.greggs5,
        R.drawable.greggs6,
        R.drawable.greggs7,
    )

    SimpleAppTheme {
        Surface(
            modifier = modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
            ) {
                AnimatedViewPager(
                    modifier = Modifier.fillMaxWidth(),
                    pageSize = pageWidth, // Page is in square shape
                    drawables = drawables,
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AnimaterPagerScreenPreview() {
    SimpleAppTheme {
        AnimaterPagerScreen(modifier = Modifier.fillMaxSize())
    }

}