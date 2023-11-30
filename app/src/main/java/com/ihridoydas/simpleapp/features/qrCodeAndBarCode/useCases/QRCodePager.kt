package com.ihridoydas.simpleapp.features.qrCodeAndBarCode.useCases

import android.app.Activity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.features.barCodeScanner.BarCodeScreen
import com.ihridoydas.simpleapp.features.qrCodeAndBarCode.scannercode.scanner.ScannerPage
import com.ihridoydas.simpleapp.features.qrCodeAndBarCode.scannercode.scanner.ScannerViewModel
import com.ihridoydas.simpleapp.ui.theme.DarkText
import com.ihridoydas.simpleapp.ui.theme.LightText


//List of Screen
val listOfPager = listOf("QrWithBarcode", "BarCode")


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QrContent(pagerState: PagerState, onBackPress: () -> Unit,viewModel: ScannerViewModel) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White
    ) {
        HorizontalPager(state = pagerState) { page ->
            when (page) {
                0 -> ScannerPage(onBackPress,viewModel)
                1 -> BarCodeScreen()
            }

        }

    }

}

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
fun ScannerUIScreen(
    onBackPress: () -> Unit,
    viewModel: ScannerViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val activity = remember(context) {
        context as Activity
    }
    val dialogExpanded = remember { mutableStateOf(false) }
    val menuExpanded = remember { mutableStateOf(false) }
    val currentScanValue = viewModel.scanValue.collectAsState(initial = 1)

    if (dialogExpanded.value) {
        val uriHandler = LocalUriHandler.current

        Dialog(
            onDismissRequest = { dialogExpanded.value = false }
        ) {
            Card(
                modifier = Modifier
                    .wrapContentHeight()
                    .height(470.dp)
                    .width(450.dp),
                shape = RoundedCornerShape(size = 8.dp),
                backgroundColor = MaterialTheme.colors.background
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    IconButton(
                        onClick = { dialogExpanded.value = false },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Close dialog"
                        )
                    }
                    Card(
                        modifier = Modifier
                            .height(400.dp)
                            .width(450.dp)
                            .padding(start = 15.dp, end = 15.dp, bottom = 15.dp),
                        shape = RoundedCornerShape(8.dp),
//                        backgroundColor = MaterialTheme.colors.onBackground,
                        backgroundColor = Color.White,
                        elevation = 10.dp
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = R.string.app_name),
                                color = if (isSystemInDarkTheme()) LightText else DarkText,
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = "Logo",
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .border(
                                        width = 1.dp,
                                        color = if (isSystemInDarkTheme()) LightText else DarkText,
                                        shape = CircleShape
                                    )
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Divider(
                                color = if (isSystemInDarkTheme()) LightText else DarkText,
                                thickness = 2.dp
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                            Text(
                                text = "My GitHub",
                                color = if (isSystemInDarkTheme()) LightText else DarkText,
                                fontWeight = FontWeight.Bold,
                                fontSize = 30.sp
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                IconButton(
                                    onClick = { uriHandler.openUri("https://github.com/ihridoydas") },
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.github_logo),
                                        contentDescription = "Open Github",
                                        colorFilter = ColorFilter.tint(if (isSystemInDarkTheme()) LightText else DarkText)
                                    )
                                }
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 10.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Developed by Hridoy Chandra Das",
                        color = if (isSystemInDarkTheme()) DarkText else LightText,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                colors = TopAppBarColors(
                    containerColor = Color.Black,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White,
                    scrolledContainerColor = Color.White,
                    actionIconContentColor = Color.White
                    ),
                title = { Text(
                    text = "Scanner Screen",
                    color = Color.White
                ) },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackPress()
                        },
                        modifier = Modifier
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White,)
                    }
                },
                actions = {
                    Text(
                        text = "Scan Code ${currentScanValue.value}",
                        modifier = Modifier,
                        color = Color.White
                    )
                        IconButton(
                            onClick = {
                                menuExpanded.value = true
                            }) {
                            Icon(
                                Icons.Filled.MoreVert,
                                contentDescription = "Menu",
                                tint = Color.White
                            )
                        }

                    Column(
                        modifier = Modifier.wrapContentSize(Alignment.TopStart)
                    ) {
                        DropdownMenu(
                            expanded = menuExpanded.value,
                            onDismissRequest = {
                                menuExpanded.value = false
                            },
                            modifier = Modifier
                                .width(150.dp)
                                .wrapContentSize(Alignment.TopStart)
                        ) {
                            DropdownMenuItem(onClick = {
                                dialogExpanded.value = true
                            }) {
                                Text(text = stringResource(id = R.string.developer))
                            }
                            DropdownMenuItem(onClick = { activity.finish() }) {
                                Text(text = stringResource(id = R.string.exit))
                            }
                        }
                    }
                }

            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            ) {
                val pagerState =
                    rememberPagerState(
                        initialPage = 0,
                        initialPageOffsetFraction = 0f,
                        pageCount = { listOfPager.size })
                QrContent(pagerState = pagerState, onBackPress,viewModel=viewModel)
            }
        }
    )
}
