package com.ihridoydas.simpleapp.features.qrCodeAndBarCode.scannercode.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.features.qrCodeAndBarCode.scannercode.scanner.ScannerViewModel
import com.ihridoydas.simpleapp.navigation.animationNavHost.ScreenDestinations
import kotlinx.coroutines.launch

@Composable
fun ScannerChooser(
    viewModel: ScannerViewModel,
    scannerDialog: MutableState<Boolean>,
    navController:NavController
) {
    val scope = rememberCoroutineScope()
    if (scannerDialog.value) {
        Dialog(
            onDismissRequest = { scannerDialog.value = false }
        ) {
            Card(
                modifier = Modifier
                    .wrapContentHeight()
                    .width(450.dp),
                shape = RoundedCornerShape(size = 8.dp),
                backgroundColor = MaterialTheme.colors.background
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    IconButton(
                        onClick = { scannerDialog.value = false },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Close dialog"
                        )
                    }

                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 10.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        scope.launch {
                            viewModel.saveScanCode(1)
                            scannerDialog.value = false
                            navController?.navigate(ScreenDestinations.BarCodeViewScreen.route) {
                                popUpTo(ScreenDestinations.ViewScreen.route) {
                                    inclusive = false
                                }
                            }
                        }
                    }) {
                        Text(text = stringResource(id = R.string.scan_value) + "1")
                    }
                    Button(onClick = {
                        scope.launch {
                            viewModel.saveScanCode(5)
                            scannerDialog.value = false
                            navController?.navigate(ScreenDestinations.BarCodeViewScreen.route) {
                                popUpTo(ScreenDestinations.ViewScreen.route) {
                                    inclusive = false
                                }
                            }
                        }
                    }) {
                        Text(text = stringResource(id = R.string.scan_value) + "5")
                    }
                }
            }
        }
    }

}