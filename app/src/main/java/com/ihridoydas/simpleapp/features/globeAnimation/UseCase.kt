package com.ihridoydas.simpleapp.features.globeAnimation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme


@Composable
fun GloveAnimation() {
    val context = LocalContext.current

    val defaultPath = PathParser().parsePathString(context.getString(R.string.default_path))
        .toPath()
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.Black
    ) {
        GlovoLikeAnimation(
            onGoalClick = { item ->
                Log.d("Glovo Item", item.title)
            },
            mainItem = GlovoItem("Main", defaultPath),
            items = listOf(
                GlovoItem("Secondary 1", defaultPath),
                GlovoItem("Secondary 2", defaultPath),
                GlovoItem("Secondary 3", defaultPath),
                GlovoItem("Secondary 4", defaultPath),
                GlovoItem("Secondary 5", defaultPath),
            )
        )

    }


}


@Preview
@Composable
private fun GloveAnimationPreview() {
    SimpleAppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = Color.Black
        ) {
            GloveAnimation()
        }
    }
}
