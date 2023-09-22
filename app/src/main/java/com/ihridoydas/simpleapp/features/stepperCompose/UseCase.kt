package com.ihridoydas.simpleapp.features.stepperCompose

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ihridoydas.simpleapp.features.stepperCompose.screens.MaterialStepperScreen
import com.ihridoydas.simpleapp.features.stepperCompose.screens.StateStepperScreen
import com.ihridoydas.simpleapp.features.timeLineCompose.TimeLineViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepperComposableScreen(
    onBackPress:()->Unit, activity: Activity, navController: NavHostController,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(Color.Blue),
                title = { Text(text = "Stepper Composable" , style = TextStyle(color = Color.White)) },
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
                StepperComposablePreview()
            }
        }
    )

}
@Preview(showSystemUi = true)
@Composable
fun StepperComposablePreview() {
    var tabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Material", "State")
    Column {
        TabRow(selectedTabIndex = tabIndex) {
            tabTitles.forEachIndexed { index, title ->
                Tab(selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    text = { Text(text = title) })
            }
        }
        when (tabIndex) {
            0 -> MaterialStepperScreen()
            1 -> StateStepperScreen()
        }
    }
}