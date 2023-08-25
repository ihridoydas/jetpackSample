package com.ihridoydas.simpleapp.util.responsiveUI.component.lazyColumnWithScrollbar.scrollBar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import com.ihridoydas.simpleapp.util.responsiveUI.component.lazyColumnWithScrollbar.LazyColumnScrollbarSettings
import com.ihridoydas.simpleapp.util.responsiveUI.component.lazyColumnWithScrollbar.LazyColumnWithScrollbar

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ExampleLazyColumnWithScrollbar(data: List<Int>) {
    val scrollbarSettings = remember {
        mutableStateOf(LazyColumnScrollbarSettings())
    }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumnWithScrollbar(
            data = data,
            settings = scrollbarSettings.value,
            modifier = Modifier.height(500.dp)
        ) {
            items(data) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .clickable { },
                    elevation = 10.dp
                ) {
                    Column {
                        Text(
                            text = it.toString(),
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                }
            }
        }

        Row() {
            Button(modifier = Modifier
                .fillMaxWidth(0.5F)
                .padding(4.dp),
                contentPadding = PaddingValues(4.dp),
                onClick = {
                    scrollbarSettings.value = scrollbarSettings.value.copy(
                        thumbColor = Color.Green,
                        trailColor = Color.Transparent,
                        thumbWidth = LazyColumnScrollbarSettings.ThumbWidth.X_LARGE,
                        thumbHeight = LazyColumnScrollbarSettings.ThumbHeight.SMALL
                    )
                }
            ) {
                Text(text = "Green + Small + Thick")
            }

            Button(modifier = Modifier
                .fillMaxWidth(1F)
                .padding(4.dp),
                contentPadding = PaddingValues(4.dp),
                onClick = {
                    scrollbarSettings.value = scrollbarSettings.value.copy(
                        thumbColor = Color.Red,
                        trailColor = Color.Yellow,
                        thumbWidth = LazyColumnScrollbarSettings.ThumbWidth.SMALL,
                        thumbHeight = LazyColumnScrollbarSettings.ThumbHeight.X_LARGE
                    )
                }
            ) {
                Text("Red + Yellow + XL + Thin")
            }
        }
        Button(modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
            contentPadding = PaddingValues(4.dp),
            onClick = {
                scrollbarSettings.value = LazyColumnScrollbarSettings()
            }
        ) {
            Text("Default")
        }
    }
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class,
    ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class
)
@Preview
@Composable
fun LazyColumnScrollBarPreview() {

    SimpleAppTheme {
        ExampleLazyColumnWithScrollbar(data = listOf(1,2,3,4,6,78,9,34,23,23,32,44,32,545,22,232,54,32,23,523,2,23,23,4,355,3456,878,9976,65,65,87,56,565,4,5,454,76,565,86,6,565,445,35))
    }

}