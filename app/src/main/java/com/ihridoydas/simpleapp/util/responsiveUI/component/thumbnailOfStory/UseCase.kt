package com.ihridoydas.simpleapp.util.responsiveUI.component.thumbnailOfStory

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme

@Composable
fun StoryThumbnailBasic() {
    SimpleAppTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            StoryThumbnail(
                count = 5,
                seen = 3,
                content = {
                    Image(
                        painter = painterResource(id = R.drawable.user_one),
                        contentDescription = null,
                    )
                },
                modifier = Modifier.size(150.dp),
                onClick = { },
                seenColor = Color.Green,
                unSeenColor = Color.Gray,
                width = 3.dp,
                spacing = 2.dp,
            )
        }
        Button(onClick = { /*TODO*/ }) {

        }
    }
}

@Preview
@Composable
fun StoryThumbnailBasicPreview() {
    SimpleAppTheme {
        StoryThumbnailBasic()
    }

}