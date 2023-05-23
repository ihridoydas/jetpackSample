package com.ihridoydas.simpleapp.util.responsiveUI.component.galleryTransitionHorizontalPager

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ihridoydas.simpleapp.ar.augmentedModelView.ARModelView

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GalleryTransition(onBackPress:()->Unit) {
    val images = remember {
        mutableStateListOf(
            "https://images.pexels.com/photos/15802380/pexels-photo-15802380/free-photo-of-black-and-white-landscape-field-animal.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            "https://images.pexels.com/photos/15960020/pexels-photo-15960020/free-photo-of-coastal-cornwall-7.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            "https://images.pexels.com/photos/16929691/pexels-photo-16929691/free-photo-of-nevado-huascaran.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            "https://images.pexels.com/photos/16572725/pexels-photo-16572725/free-photo-of-landscape-bird-people-field.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            "https://images.pexels.com/photos/16903397/pexels-photo-16903397/free-photo-of-la-soltera-dominante.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
        )
    }
    val pageState = rememberPagerState()

    androidx.compose.material.Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(Color.White),
                title = {
                    Text(
                        text = "Gallery Transition",
                        style = TextStyle(color = Color.Black)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackPress()
                        },
                        modifier = Modifier
                    ) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
            )
        },
        drawerShape = RoundedCornerShape(topEnd = 23.dp, bottomEnd = 23.dp),
        content = {
            Box(modifier = Modifier.padding(vertical = 48.dp)) {
                HorizontalPager(
                    pageCount = images.size,
                    state = pageState
                ) { index ->
                    val pageOffset = (pageState.currentPage - index) + pageState.currentPageOffsetFraction

                    val imageSize by animateFloatAsState(
                        targetValue = if (pageOffset != 0.0f) 0.75f else 1f,
                        animationSpec = tween(300)

                    )
                    val matrix = remember { ColorMatrix() }

                    LaunchedEffect(key1 = imageSize ){
                        if(pageOffset != 0.0f){
                            matrix.setToSaturation(0f)
                        }else{
                            matrix.setToSaturation(1f)
                        }
                    }

                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(15.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .graphicsLayer {
                                scaleX = imageSize
                                scaleY = imageSize
                            },
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(images[index]).build(), contentDescription = "image",
                        contentScale = ContentScale.Crop,
                        colorFilter = ColorFilter.colorMatrix(matrix)
                    )
                }

            }
        }
    )



}