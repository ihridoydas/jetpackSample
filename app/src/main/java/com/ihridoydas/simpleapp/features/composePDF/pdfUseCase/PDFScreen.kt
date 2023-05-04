package com.ihridoydas.simpleapp.features.composePDF.pdfUseCase

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.material3.TopAppBar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.features.composePDF.pdf.HorizontalPDFReader
import com.ihridoydas.simpleapp.features.composePDF.pdf.HorizontalPdfReaderState
import com.ihridoydas.simpleapp.features.composePDF.pdf.VerticalPDFReader
import com.ihridoydas.simpleapp.features.composePDF.pdf.VerticalPdfReaderState
import com.ihridoydas.simpleapp.ui.MainActivity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    val context = LocalContext.current

    TopAppBar(
        title = { Text(text = "Compose PDF Viewer") },
        navigationIcon = {
            IconButton(
                onClick = {
                    context.startActivity(Intent(context, MainActivity::class.java))
                },
                modifier = Modifier
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            IconButton(onClick = {

            }) {
                Icon(Icons.Default.Menu, contentDescription = null)
            }
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectionElement(
    title: String,
    text: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = title,
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 4.dp
                ),
                style = MaterialTheme.typography.body1
            )
            Text(
                text = text,
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 4.dp,
                    bottom = 16.dp
                ),
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@Composable
fun PDFView(
    pdfState: VerticalPdfReaderState,
    scaffoldState: ScaffoldState
) {
    Box(
        contentAlignment = Alignment.TopStart
    ) {
        VerticalPDFReader(
            state = pdfState,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Gray)
        )
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            LinearProgressIndicator(
                progress = pdfState.loadPercent / 100f,
                color = Color.Red,
                backgroundColor = Color.Green,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colors.surface.copy(alpha = 0.5f),
                            shape = MaterialTheme.shapes.medium
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Page: ${pdfState.currentPage}/${pdfState.pdfPageCount}",
                        modifier = Modifier.padding(
                            start = 8.dp,
                            end = 8.dp,
                            bottom = 4.dp,
                            top = 8.dp
                        )
                    )
                    Text(
                        text = if (pdfState.isScrolling) {
                            "Scrolling"
                        } else {
                            "Stationary"
                        },
                        color = if (pdfState.isScrolling) {
                            MaterialTheme.colors.onSurface
                        } else {
                            MaterialTheme.colors.error
                        },
                        modifier = Modifier.padding(
                            start = 8.dp,
                            end = 8.dp,
                            bottom = 8.dp,
                            top = 4.dp
                        )
                    )
                }
            }
        }
        LaunchedEffect(key1 = pdfState.error) {
            pdfState.error?.let {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = it.message ?: "Error"
                )
            }
        }
    }
}

@Composable
fun HPDFView(
    pdfState: HorizontalPdfReaderState,
    scaffoldState: ScaffoldState
) {
    Box(
        contentAlignment = Alignment.TopStart
    ) {
        HorizontalPDFReader(
            state = pdfState,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Gray)
        )
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            LinearProgressIndicator(
                progress = pdfState.loadPercent / 100f,
                color = Color.Red,
                backgroundColor = Color.Green,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colors.surface.copy(alpha = 0.5f),
                            shape = MaterialTheme.shapes.medium
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Page: ${pdfState.currentPage}/${pdfState.pdfPageCount}",
                        modifier = Modifier.padding(
                            start = 8.dp,
                            end = 8.dp,
                            bottom = 4.dp,
                            top = 8.dp
                        )
                    )
                    Text(
                        text = if (pdfState.isScrolling) {
                            "Scrolling"
                        } else {
                            "Stationary"
                        },
                        color = if (pdfState.isScrolling) {
                            MaterialTheme.colors.onSurface
                        } else {
                            MaterialTheme.colors.error
                        },
                        modifier = Modifier.padding(
                            start = 8.dp,
                            end = 8.dp,
                            bottom = 8.dp,
                            top = 4.dp
                        )
                    )
                    Text(text = "${pdfState.scale}")
                }
            }
        }
        LaunchedEffect(key1 = pdfState.error) {
            pdfState.error?.let {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = it.message ?: "Error"
                )
            }
        }
    }
}
