package com.ihridoydas.simpleapp.features.qrCodeAndBarCode.scannercode.scanner

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.features.qrCodeAndBarCode.scannercode.ui.components.CameraDialog
import com.ihridoydas.simpleapp.features.qrCodeAndBarCode.scannercode.ui.components.CameraPreviewLayout
import com.ihridoydas.simpleapp.features.qrCodeAndBarCode.scannercode.ui.components.ScanSheet
import com.ihridoydas.simpleapp.ui.theme.BottomSheetShape
import com.ihridoydas.simpleapp.util.permission.doesUserHavePermission
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScannerPage(
    onBackPress: () -> Unit,
    viewModel: ScannerViewModel
) {
    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val scanState by viewModel.scannedValues.collectAsState()
    val currentScanValue = viewModel.scanValue.collectAsState(1).value
    val predefinedKeyValues = viewModel.predefinedKeys

    val hapticFeedback = LocalHapticFeedback.current
    val activity = remember(context) {
        context as Activity
    }

    BackHandler(enabled = bottomSheetState.isVisible) {
        coroutineScope.launch {
            bottomSheetState.hide()
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            if (granted) {
                viewModel.onEvent(ScannerEvent.CreatePreviewView(lifecycleOwner))
            } else {
                viewModel.onEvent(ScannerEvent.CameraRequiredDialogVisibility(show = true))
            }
        }
    )

    LaunchedEffect(key1 = bottomSheetState.currentValue) {
        if (uiState.previewView != null && !bottomSheetState.isVisible) {
            viewModel.onEvent(ScannerEvent.BottomSheetHidden)
        }
    }

    LaunchedEffect(key1 = lifecycleOwner) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            viewModel.onEvent(ScannerEvent.CreatePreviewView(lifecycleOwner))
        } else {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    SideEffect {
        if (uiState.showBottomSheet) {
            hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
            coroutineScope.launch { if (!bottomSheetState.isVisible) bottomSheetState.show() }
            viewModel.onEvent(ScannerEvent.BottomSheetShown)
        }
    }
    val settingBtnClicked= remember { mutableStateOf(true) }

    val permissionCheck = remember {
        mutableStateOf(false)
    }
    permissionCheck.value = doesUserHavePermission()
    settingBtnClicked.value = false

    if (uiState.showCameraRequiredDialog) {
        CameraDialog(
            onContinue = {
                viewModel.onEvent(ScannerEvent.CameraRequiredDialogVisibility(show = false))
                permissionLauncher.launch(Manifest.permission.CAMERA)
                settingBtnClicked.value = true
                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.parse("package:" + context.packageName)
                    context.startActivity(this)
                }
            },
            onBack = {
                viewModel.onEvent(ScannerEvent.CameraRequiredDialogVisibility(show = false))
                //activity.finish()
                onBackPress()
            }
        )
    }
    ScannerPage(
        bottomSheetState = bottomSheetState,
        uiState = uiState,
        context = context,
        scanState = scanState,
        scanValue = currentScanValue,
        predefinedKeys = predefinedKeyValues
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ScannerPage(
    bottomSheetState: ModalBottomSheetState,
    uiState: ScannerUiState,
    context: Context,
    scanState: Map<Int, String>,
    scanValue : Int?,
    predefinedKeys: List<Int>
) {
    val clipboardManager = LocalClipboardManager.current
    val uriHandler = LocalUriHandler.current

    ModalBottomSheetLayout(
        modifier = Modifier,
        sheetState = bottomSheetState,
        sheetShape = BottomSheetShape,
        scrimColor = Color.Transparent,
        sheetContent = {
            uiState.scan?.let {
                ScanSheet(
                    scan = it,
                    onShareClicked = {
                        context.startActivity(
                            Intent.createChooser(
                                Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, it.displayValue)
                                    type = "text/plain"
                                },
                                context.getString(R.string.scan_share_value)
                            )
                        )
                    },
                    onCopyClicked = {
                        clipboardManager.setText(AnnotatedString(it.displayValue))
                        Toast.makeText(context, context.getText(R.string.scan_value_copied), Toast.LENGTH_SHORT).show()
                    },
                    onWebClicked = {
                        uriHandler.openUri(it.displayValue)
                    },
                    scanState = scanState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            } ?: Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold
            )
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black)
            ) {
                if (uiState.previewView != null) {
                    AndroidView(
                        factory = { uiState.previewView },
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Surface(
                    color = Color.Black.copy(alpha = 0.7f),
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                ) {
                    CameraPreviewLayout(scanValue = scanValue,scanState = scanState,predefinedKeys = predefinedKeys)
                }

            }
        }
    }
}