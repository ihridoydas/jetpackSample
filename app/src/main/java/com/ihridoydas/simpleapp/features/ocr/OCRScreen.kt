package com.ihridoydas.simpleapp.features.ocr

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.activity.result.launch
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.ihridoydas.simpleapp.util.permission.ShowGotoSettingsDialog
import com.ihridoydas.simpleapp.util.permission.doesUserHavePermission
import com.ihridoydas.simpleapp.util.responsiveUI.rememberWindowSize

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun OCRScreen(viewModel: OCRScreenViewModel = hiltViewModel(),onBackPress: ()-> Unit) {


    val galleryLauncher = rememberLauncherForActivityResult(
        contract = GetContent(),
        onResult = viewModel::getTextFromSelectedImage
    )

    val cameraLauncherIntent = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = viewModel::getTextFromCapturedImageBitmap
    )

    val extractedText = viewModel.extractedText.collectAsState().value

    val showDialog = remember { mutableStateOf(false) }
    val settingBtnClicked= remember { mutableStateOf(true) }

    val permissionCheck = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    permissionCheck.value = doesUserHavePermission()
    settingBtnClicked.value = false

    if(showDialog.value){
        ShowGotoSettingsDialog(
            rememberWindowSize(),
            title = "You haven't Camera Permission",
            message = "You Need to permission for Use This Application",
            onSettingsTapped = {
                settingBtnClicked.value = true
                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.parse("package:" + context.packageName)
                    context.startActivity(this)
                } },
            onDismiss = { showDialog.value = false }
        )
    }



    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "OCR") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackPress()
                        },
                        modifier = Modifier
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
        drawerShape = RoundedCornerShape(topEnd = 23.dp, bottomEnd = 23.dp),
        content = {
            if(settingBtnClicked.value){
                showDialog.value = false
            }
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {

                Card(
                    elevation = 7.dp
                ) {
                    LazyColumn(modifier = Modifier
                        .fillMaxHeight(0.7f)
                        .fillMaxWidth()) {
                        item {
                            SelectionContainer {
                                Text(text = extractedText, modifier = Modifier.padding(vertical = 3.dp, horizontal = 7.dp))
                            }
                        }
                    }
                }

                Button(
                    onClick = viewModel::copyTextToClipboard,
                    enabled = extractedText.isNotBlank()
                ) {
                    Text(text = "Copy entire text")
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {

                    Button(onClick = {
                        if(permissionCheck.value){
                            showDialog.value = false
                            cameraLauncherIntent.launch()

                        }else{
                            showDialog.value = true
                        }

                    }) {
                        Text(text = "Start camera")
                    }

                    Button(onClick = { galleryLauncher.launch("image/*") }) {
                        Text(text = "Choose from gallery")
                    }


                }


            }
        }
    )

}