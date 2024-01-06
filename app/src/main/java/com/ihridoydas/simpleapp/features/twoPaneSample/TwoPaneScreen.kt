package com.ihridoydas.simpleapp.features.twoPaneSample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.adaptive.HorizontalTwoPaneStrategy
import com.google.accompanist.adaptive.TwoPane
import com.google.accompanist.adaptive.VerticalTwoPaneStrategy
import com.google.accompanist.adaptive.calculateDisplayFeatures
import com.ihridoydas.simpleapp.ui.MainActivity
import com.ihridoydas.simpleapp.ui.theme.Beige
import com.ihridoydas.simpleapp.ui.theme.Brown
import com.ihridoydas.simpleapp.ui.theme.DarkOrange
import com.ihridoydas.simpleapp.ui.theme.Orange

@Composable
fun TwoPaneScreen(activity : MainActivity) {
    var strategyPct: Float by rememberSaveable { mutableStateOf(0.5f) }
    val updatePct: (Float) -> Unit = { strategyPct = it }

    var strategy: STRATEGY by rememberSaveable { mutableStateOf(STRATEGY.VERTICAL) }
    val updateStrategy: (STRATEGY) -> Unit = { strategy = it }

    var config: CONFIG by rememberSaveable { mutableStateOf(CONFIG.ALL) }
    val updateConfig: (CONFIG) -> Unit = { config = it }


    Scaffold(
        drawerShape = RoundedCornerShape(topEnd = 23.dp, bottomEnd = 23.dp),
        content = {
            Column(
                modifier = Modifier
                    .padding(it),
            ) {
                Box {
                    TwoPane(
                        first = { First() },
                        second = { Second() },
                        strategy = when (strategy) {
                            STRATEGY.HORIZONTAL -> HorizontalTwoPaneStrategy(strategyPct)
                            STRATEGY.VERTICAL -> VerticalTwoPaneStrategy(strategyPct)
                        },
                        displayFeatures = calculateDisplayFeatures(activity = activity),
                        foldAwareConfiguration = config.foldConfig
                    )
                    ConfigControls(strategy, updateStrategy, strategyPct, updatePct, config, updateConfig)
                }
            }
        }
    )


}

@Composable
fun First() {
    Box(
        modifier = Modifier
            .background(Brown)
            .fillMaxSize()
    ) {
        Surface(
            modifier = Modifier
                .align(Alignment.Center)
                .size(400.dp),
            shape = CircleShape,
            color = Beige
        ) {}
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = "First!",
            style = MaterialTheme.typography.h3
        )
    }
}

@Composable
fun Second() {
    Box(
        modifier = Modifier
            .background(Brown)
            .fillMaxSize()
    ) {
        Surface(
            modifier = Modifier
                .align(Alignment.Center)
                .size(400.dp),
            shape = CircleShape,
            color = Orange
        ) {}
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = "Second!",
            style = MaterialTheme.typography.h3
        )
    }
}

@Composable
fun BoxScope.ConfigControls(
    strategy: STRATEGY,
    updateStrategy: (STRATEGY) -> Unit,
    strategyPct: Float,
    updatePct: (Float) -> Unit,
    config: CONFIG,
    updateConfig: (CONFIG) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .align(Alignment.TopStart)
            .padding(top = 20.dp, start = 20.dp)
            .background(Color.Black.copy(alpha = 0.6f))
            .widthIn(max = 400.dp)
            .padding(horizontal = 15.dp)
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Change TwoPane configuration",
                tint = Color.White
            )
        }
        if (expanded) {
            Text("TwoPaneStrategy", color = Color.White)
            Row(verticalAlignment = Alignment.CenterVertically) {
                STRATEGY.values().forEach {
                    RadioButton(
                        selected = strategy == it,
                        onClick = { updateStrategy(it) },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = DarkOrange,
                            unselectedColor = DarkOrange.copy(alpha = 0.6f),
                        )
                    )
                    Text(it.name.lowercase(), color = Color.White)
                }
            }
            Text("TwoPaneStrategy split fraction", color = Color.White)
            Slider(
                value = strategyPct,
                onValueChange = { updatePct(it) },
                colors = SliderDefaults.colors(
                    thumbColor = DarkOrange,
                    activeTrackColor = DarkOrange,
                    inactiveTrackColor = DarkOrange.copy(alpha = SliderDefaults.InactiveTrackAlpha)
                )
            )
            Text("FoldAwareConfiguration", color = Color.White)
            Row(verticalAlignment = Alignment.CenterVertically) {
                CONFIG.values().forEach {
                    RadioButton(
                        selected = config == it,
                        onClick = { updateConfig(it) },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = DarkOrange,
                            unselectedColor = DarkOrange.copy(alpha = 0.6f),
                        )
                    )
                    Text(it.name.lowercase(), color = Color.White)
                }
            }
        }
    }
}