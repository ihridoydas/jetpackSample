package com.ihridoydas.simpleapp.features.composibleSheep

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ihridoydas.simpleapp.features.composibleSheep.screens.canvasbasics.ArcScreen
import com.ihridoydas.simpleapp.features.composibleSheep.screens.canvasbasics.LineScreen
import com.ihridoydas.simpleapp.features.composibleSheep.screens.canvasbasics.MySuperScreen
import com.ihridoydas.simpleapp.features.composibleSheep.screens.canvasbasics.PointsScreen
import com.ihridoydas.simpleapp.features.composibleSheep.screens.canvasbasics.QuadraticBezierScreen
import com.ihridoydas.simpleapp.features.composibleSheep.screens.canvasbasics.ShapeScreen
import com.ihridoydas.simpleapp.features.composibleSheep.screens.canvasbasics.SimplePathScreen
import com.ihridoydas.simpleapp.features.composibleSheep.screens.sheepscreen.BasicSheepScreen
import com.ihridoydas.simpleapp.features.composibleSheep.screens.sheepscreen.SheepViewerScreen
import com.ihridoydas.simpleapp.ui.theme.Grid

private enum class Screen {
    SHEEP, BASIC_SHEEP, LINE, POINTS, SHAPE, ARC, SUPER, BEZIER, SIMPLE_PATH,
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainSheepCanvas(onBackPress: ()-> Unit) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = isSystemInDarkTheme().not()
    val surfaceColor = MaterialTheme.colorScheme.surface

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = surfaceColor,
            darkIcons = useDarkIcons
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(Color.White),
                title = { androidx.compose.material.Text(text = "Sheep Canvas by Nicole Terc" , style = TextStyle(color = Color.Black)) },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackPress()
                        },
                        modifier = Modifier
                    ) {
                        androidx.compose.material.Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black )
                    }
                },
            )
        },
        modifier = Modifier
            .fillMaxSize(),
    ) {
        var expanded by remember { mutableStateOf(false) }
        var selectedScreen by remember { mutableStateOf(Screen.SHEEP) }

        Column(
            Modifier
                .padding(it)
                .fillMaxSize()
                .padding(Grid.Two)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.TopStart)
            ) {
                Row(modifier = Modifier.clickable { expanded = true }) {
                    Text(
                        text = selectedScreen.name,
                        style = MaterialTheme.typography.headlineSmall,
                    )
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Select Screen"
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    Screen.values().forEach { screen ->
                        DropdownMenuItem(
                            text = { Text(text = screen.name) },
                            onClick = {
                                expanded = false
                                selectedScreen = screen
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.padding(vertical = Grid.One))

            when (selectedScreen) {
                Screen.SHEEP -> SheepViewerScreen()
                Screen.LINE -> LineScreen()
                Screen.POINTS -> PointsScreen()
                Screen.SHAPE -> ShapeScreen()
                Screen.ARC -> ArcScreen()
                Screen.SUPER -> MySuperScreen()
                Screen.BASIC_SHEEP -> BasicSheepScreen()
                Screen.BEZIER -> QuadraticBezierScreen()
                Screen.SIMPLE_PATH -> SimplePathScreen()
            }
        }
    }

}