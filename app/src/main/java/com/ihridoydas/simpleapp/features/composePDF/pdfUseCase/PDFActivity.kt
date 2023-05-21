package com.ihridoydas.simpleapp.features.composePDF.pdfUseCase

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ShareCompat
import androidx.core.content.FileProvider
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.features.composePDF.pdf.HorizontalPdfReaderState
import com.ihridoydas.simpleapp.features.composePDF.pdf.ResourceType
import com.ihridoydas.simpleapp.features.composePDF.pdf.VerticalPdfReaderState
import com.ihridoydas.simpleapp.ui.theme.Green
import com.ihridoydas.simpleapp.ui.theme.GreenColor
import com.ihridoydas.simpleapp.ui.theme.GreenVarient
import com.ihridoydas.simpleapp.ui.theme.Orange
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import com.ihridoydas.simpleapp.util.responsiveUI.component.aGSL_customLayoutAndGraphics.BackgroundByAGSL
import java.io.File

private const val OPEN_DOCUMENT_REQUEST_CODE = 0x33

//Note : In manifest file add provider meta-data file (in Application)
/**
 * P d f activity
 * Compose PDF Render
 * https://github.com/GRizzi91/bouquet
 * @constructor Create empty P d f activity
 */

class PDFActivity : ComponentActivity() {
    //For PDf View Model
    private val viewModel: PDFViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED
        )

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.clearResource()
            }
        })

        setContent {
            SimpleAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PDFApp()
                }
            }
        }
    }

    //---------------------------------------------

    @Composable
    fun PDFApp() {
        val scaffoldState = rememberScaffoldState()
        val state = viewModel.stateFlow.collectAsState()
        Scaffold(
            topBar = {
                TopAppBar()
            },
            scaffoldState = scaffoldState,
            floatingActionButton = {
                state.value?.file?.let {
                    FloatingActionButton(
                        onClick = {
                            shareFile(it)
                        }
                    ) {
                        Icon(
                            Icons.Default.Share,
                            contentDescription = "share"
                        )
                    }
                }
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)
                .BackgroundByAGSL(Green, Orange)) {
                when (val actualState = state.value) {
                    null -> SelectionView()
                    is VerticalPdfReaderState -> PDFView(
                        pdfState = actualState,
                        scaffoldState = scaffoldState
                    )
                    is HorizontalPdfReaderState -> HPDFView(
                        pdfState = actualState,
                        scaffoldState = scaffoldState
                    )
                }
            }
        }

    }

    @Composable
    fun SelectionView() {
        Column(modifier = Modifier.fillMaxSize()) {
            SelectionElement(
                title = "Open Base64",
                text = "Try to open a base64 pdf"
            ) {
                viewModel.openResource(
                    ResourceType.Base64(
                        this@PDFActivity.getString(R.string.base64_pdf)
                    )
                )
            }
            SelectionElement(
                title = "Open Remote file",
                text = "Open a remote file from url"
            ) {
                viewModel.openResource(
                    ResourceType.Remote(
                        url = this@PDFActivity.getString(
                            R.string.pdf_url
                        ),
                        headers = hashMapOf("headerKey" to "headerValue")
                    )
                )
            }
            SelectionElement(
                title = "Open Local file",
                text = "Open a file in device memory"
            ) {
                openDocumentPicker()
            }
            Row(
                modifier = Modifier
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                androidx.compose.material.Text(text = "List view")
                Spacer(modifier = Modifier.width(16.dp))
                Switch(
                    checked = viewModel.switchState.value,
                    onCheckedChange = {
                        viewModel.switchState.value = it
                    },
                    colors = SwitchDefaults.colors(
                        uncheckedThumbColor = androidx.compose.material.MaterialTheme.colors.secondaryVariant,
                        uncheckedTrackColor = androidx.compose.material.MaterialTheme.colors.secondaryVariant,
                        uncheckedTrackAlpha = 0.54f
                    )
                )
                Spacer(modifier = Modifier.width(16.dp))
                androidx.compose.material.Text(text = "Pager view")
            }
        }
    }

    private fun openDocumentPicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            type = "application/pdf"
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        startActivityForResult(intent, OPEN_DOCUMENT_REQUEST_CODE)
    }

    private fun openDocument(documentUri: Uri) {
        documentUri.path?.let {
            viewModel.openResource(
                ResourceType.Local(
                    documentUri
                )
            )
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        if (requestCode == OPEN_DOCUMENT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            resultData?.data?.also { documentUri ->
                contentResolver.takePersistableUriPermission(
                    documentUri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
                openDocument(documentUri)
            }
        }
    }

    private fun shareFile(file: File) {
        val uri: Uri = FileProvider.getUriForFile(
            this,
            "${this.packageName}.fileprovider",
            file
        )
        val intent = ShareCompat.IntentBuilder.from(this)
            .setType("application/pdf")
            .setStream(uri)
            .createChooserIntent()
            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(intent)
    }

//---------------------------------------------

}
