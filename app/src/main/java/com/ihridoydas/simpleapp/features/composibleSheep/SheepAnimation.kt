package com.ihridoydas.simpleapp.features.composibleSheep

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
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
import com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.screens.AllInChaosScreen
import com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.screens.BlinkSheepScreen
import com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.screens.GroovySheepScreen
import com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.screens.SimpleColorScreen
import com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.screens.SimpleJumpScreen
import com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.screens.SimpleMoveScreen
import com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.screens.SimpleSizeScreen
import com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.screens.SimpleVisibilityScreen
import com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.screens.canvas.CircleAnimationScreen
import com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.screens.coroutine.CoroutinesJumpScreen
import com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.screens.gesture.ChasingSheep
import com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.screens.gesture.DraggingSheep
import com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.screens.gesture.FlyingSheep
import com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.screens.gesture.TransformingSheep
import com.ihridoydas.simpleapp.features.composibleSheep.sheepanimations.screens.transition.TransitionsScreen
import com.ihridoydas.simpleapp.ui.theme.Grid

private enum class ScreenAni {
    SIMPLE_SIZE,
    SIMPLE_COLOR,
    GROOVY_COLOR,
    SIMPLE_VISIBILITY,
    BLINK_VISIBILITY,
    SIMPLE_MOVE,
    SIMPLE_JUMP,
    ALL_IN_CHAOS,
    TRANSITIONS_JUMP,
    COROUTINES_JUMP,
    CHASING_SHEEP_GESTURES,
    DRAGGING_SHEEP_GESTURES,
    FLYING_SHEEP_GESTURES,
    TRANSFORMING_SHEEP_GESTURES,
    CIRCLE_ANIMATION,
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainSheepAnimation(onBackPress: ()->Unit) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = isSystemInDarkTheme().not()
    val surfaceColor = MaterialTheme.colorScheme.surface

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = surfaceColor, darkIcons = useDarkIcons
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(Color.White),
                title = { androidx.compose.material.Text(text = "Sheep animation by Nicole Terc" , style = TextStyle(color = Color.Black)) },
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
    ) {
        var expanded by remember { mutableStateOf(false) }
        var selectedScreen by remember { mutableStateOf(ScreenAni.CHASING_SHEEP_GESTURES) }

        Column(
            modifier = Modifier
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
                    ScreenAni.values().forEach { screen ->
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

            Crossfade(
                targetState = selectedScreen,
                animationSpec = tween(durationMillis = 500)
            ) { screen ->
                when (screen) {
                    ScreenAni.SIMPLE_SIZE -> SimpleSizeScreen()
                    ScreenAni.SIMPLE_COLOR -> SimpleColorScreen()
                    ScreenAni.GROOVY_COLOR -> GroovySheepScreen()
                    ScreenAni.SIMPLE_VISIBILITY -> SimpleVisibilityScreen()
                    ScreenAni.BLINK_VISIBILITY -> BlinkSheepScreen()
                    ScreenAni.SIMPLE_MOVE -> SimpleMoveScreen()
                    ScreenAni.SIMPLE_JUMP -> SimpleJumpScreen()
                    ScreenAni.ALL_IN_CHAOS -> AllInChaosScreen()
                    ScreenAni.TRANSITIONS_JUMP -> TransitionsScreen()
                    ScreenAni.COROUTINES_JUMP -> CoroutinesJumpScreen()
                    ScreenAni.CHASING_SHEEP_GESTURES -> ChasingSheep()
                    ScreenAni.DRAGGING_SHEEP_GESTURES -> DraggingSheep()
                    ScreenAni.FLYING_SHEEP_GESTURES -> FlyingSheep()
                    ScreenAni.TRANSFORMING_SHEEP_GESTURES -> TransformingSheep()
                    ScreenAni.CIRCLE_ANIMATION -> CircleAnimationScreen()
                }
            }
        }
    }

}