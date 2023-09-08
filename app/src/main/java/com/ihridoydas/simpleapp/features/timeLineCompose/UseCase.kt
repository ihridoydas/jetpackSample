package com.ihridoydas.simpleapp.features.timeLineCompose

import android.app.Activity
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ihridoydas.simpleapp.features.timeLineCompose.TimeLineViewModel.Companion.TEST_DATA
import com.ihridoydas.simpleapp.features.timeLineCompose.composables.LazyTimeline
import com.ihridoydas.simpleapp.features.timeLineCompose.entities.HiringStage
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeLineComposableScreen(
    onBackPress:()->Unit, activity: Activity, navController: NavHostController,
    timeLineViewModel: TimeLineViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(Color.Black),
                title = { Text(text = "TimeLine Compose By Job Hire" , style = TextStyle(color = Color.White)) },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackPress()
                        },
                        modifier = Modifier
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White )
                    }
                },
            )
        },
        drawerShape = RoundedCornerShape(topEnd = 23.dp, bottomEnd = 23.dp),
        content = {
            Box(modifier = Modifier.fillMaxSize().padding(it)){
                val screenState = timeLineViewModel.hiringProcessState.collectAsState(initial = arrayOf())
                SampleScreenContent(screenState.value)

            }
        }
    )

}

@Composable
fun SampleScreenContent(timelineStages: Array<HiringStage>) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyTimeline(timelineStages)
    }
}

@Preview(showBackground = true)
@Composable
private fun TimelineUsagePreview() {
    SimpleAppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LazyTimeline(TEST_DATA)
        }
    }
}