package com.ihridoydas.simpleapp.features.bottomSheets

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.ihridoydas.simpleapp.features.bottomSheets.material3Sheet.BottomSheet
import com.ihridoydas.simpleapp.features.bottomSheets.sheetCore.PeekHeight
import com.ihridoydas.simpleapp.features.bottomSheets.sheetCore.BottomSheetState
import com.ihridoydas.simpleapp.features.bottomSheets.sheetCore.rememberBottomSheetState
import com.ihridoydas.simpleapp.features.bottomSheets.uitils.IntentPickerSheetContent
import com.ihridoydas.simpleapp.features.bottomSheets.uitils.ListSheetContent
import com.ihridoydas.simpleapp.features.bottomSheets.uitils.M3SimpleSheetContent
import com.ihridoydas.simpleapp.features.bottomSheets.uitils.SimpleSheetContent
import com.ihridoydas.simpleapp.features.bottomSheets.uitils.TextFieldSheetContent
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


@Composable
fun BottomSheetSampleApp() {
    var dark by remember { mutableStateOf(false) }

    val window = (LocalContext.current as Activity).window
    val backgroundColor = if (dark) Color(0xff121212) else Color.White
    val view = LocalView.current
    LaunchedEffect(dark, backgroundColor) {
        window.statusBarColor = backgroundColor.toArgb()
        WindowCompat.getInsetsController(
            window,
            view
        ).isAppearanceLightStatusBars = !dark
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            SimpleAppTheme(darkTheme = dark) {
                Surface(color = backgroundColor) {
                    Sheets(dark)
                }
            }

            MaterialTheme(
                colorScheme = if (dark) {
                    darkColorScheme()
                } else {
                    lightColorScheme()
                },
            ) {
                androidx.compose.material3.Surface(
                    color = backgroundColor,
                    contentColor = contentColorFor(
                        MaterialTheme.colorScheme.background
                    ),
                ) {
                    M3Sheets()
                }
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = "Dark", color = if (dark) Color.White else Color.Black)
            Spacer(modifier = Modifier.width(4.dp))
            Switch(
                checked = dark,
                onCheckedChange = { dark = it }
            )
        }
    }
}

@Composable
private fun Sheets(dark:Boolean,modifier: Modifier = Modifier,) {
    val scope = rememberCoroutineScope()

    val simpleSheetState = rememberBottomSheetState()
    val listSheetState = rememberBottomSheetState()
    val intentPickerSheetState = rememberBottomSheetState()
    val editSheetState = rememberBottomSheetState()

    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { scope.launch { simpleSheetState.expand() } }) {
            Text("Simple", color = if (dark) Color.White else Color.Black)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { scope.launch { listSheetState.peek() } }) {
            Text("List", color = if (dark) Color.White else Color.Black)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { scope.launch { intentPickerSheetState.peek() } }) {
            Text("Intent Picker", color = if (dark) Color.White else Color.Black)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { scope.launch { editSheetState.expand() } }) {
            Text("Text fields", color = if (dark) Color.White else Color.Black)
        }
    }

    SimpleBottomSheet(state = simpleSheetState)

    ListBottomSheet(state = listSheetState)

    IntentPickerBottomSheet(state = intentPickerSheetState)

    TextFieldBottomSheet(state = editSheetState)
}

@Composable
private fun M3Sheets(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()

    val md3SimpleSheetState = rememberBottomSheetState()

    Column(modifier = modifier) {
        Button(
            onClick = { scope.launch { md3SimpleSheetState.expand() } }
        ) {
            Text("Material 3")
        }

        Spacer(modifier = Modifier.height(8.dp))
    }

    Md3SimpleBottomSheet(state = md3SimpleSheetState)
}

@Composable
fun SimpleBottomSheet(
    state: BottomSheetState,
    modifier: Modifier = Modifier
) {
    BottomSheet(
        state = state,
        modifier = modifier,
        skipPeeked = true,
    ) {
        SimpleSheetContent(state)
    }
}


@Composable
fun Md3SimpleBottomSheet(
    state: BottomSheetState,
    modifier: Modifier = Modifier
) {
    BottomSheet(
        state = state,
        modifier = modifier,
        skipPeeked = true,
    ) {
        M3SimpleSheetContent(state)
    }
}

@Composable
private fun ListBottomSheet(
    state: BottomSheetState,
    modifier: Modifier = Modifier
) {
    var sheetShape by remember {
        mutableStateOf(RoundedCornerShape(0f))
    }

    LaunchedEffect(state) {
        snapshotFlow { state.dragProgress }
            .distinctUntilChanged()
            .collect {
                sheetShape = RoundedCornerShape(16.dp * it)
            }
    }

    BottomSheet(
        state = state,
        modifier = modifier
            .fillMaxHeight(0.8f)
            .heightIn(min = 300.dp),
        peekHeight = PeekHeight.fraction(0.6f),
        shape = sheetShape,
    ) {
        ListSheetContent()
    }
}

@Composable
private fun IntentPickerBottomSheet(
    state: BottomSheetState,
    modifier: Modifier = Modifier,
) {
    BottomSheet(
        state = state,
        modifier = modifier,
        peekHeight = PeekHeight.dp(420),
    ) {
        IntentPickerSheetContent(state)
    }
}

@Composable
private fun TextFieldBottomSheet(
    state: BottomSheetState,
    modifier: Modifier = Modifier,
) {
    BottomSheet(
        state = state,
        modifier = modifier,
        skipPeeked = true,
    ) {
        TextFieldSheetContent(state = state)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BottomSheetSampleApp()
}