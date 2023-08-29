package com.ihridoydas.simpleapp.ar.arEcommerce.productdescription.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.ar.arEcommerce.virtualtryon.presentation.VirtualTryOnUIEvent
import com.ihridoydas.simpleapp.ar.arEcommerce.virtualtryon.presentation.onUserTap
import io.github.sceneview.ar.ARScene
import java.math.BigDecimal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDescriptionScreen(
    productId: Int,
    navController: NavHostController,
    productViewModel: ProductDescriptionViewModel,
    onBackPress:()->Unit

) {

    LaunchedEffect(Unit) {
        productViewModel.dispatchEvent(ProductDescriptionUiEvent.FetchProductData(productId))
    }
    val context = LocalContext.current
    val viewState by productViewModel.state.collectAsState()
    val uiAction by productViewModel.uiAction.collectAsState()

    when (uiAction) {
        // UI actions must be run once, thus run them in a Launched Effect
        is ProductDescriptionUIAction.NavigateToAddToCartScreen -> {
            LaunchedEffect(Unit) {
                Toast.makeText(context, "Added to cart!", Toast.LENGTH_SHORT).show()
                productViewModel.onConsumeUIAction()
            }
        }
        is ProductDescriptionUIAction.NavigateToVirtualTryOnScreen -> {
            LaunchedEffect(Unit) {
                navController.navigate("virtual_try_on/$productId")
                productViewModel.onConsumeUIAction()
            }
        }
        null -> {}
    }
    viewState.product?.let { product ->
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(Color.White),
                    title = { Text(text = "Augmented ECommerce Home" , style = TextStyle(color = Color.Black)) },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                onBackPress()
                            },
                            modifier = Modifier
                        ) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black )
                        }
                    },
                )
            },
            drawerShape = RoundedCornerShape(topEnd = 23.dp, bottomEnd = 23.dp),
            content = {
                Box(modifier = Modifier.fillMaxSize().padding(it)) {
                    Column(
                        modifier =
                        Modifier.verticalScroll(rememberScrollState())
                    ) {
                        ImageCarousel(product.images)
                        Button(
                            onClick = {
                                // If you need the full product data class, extend Product data class to Parceable interface
                                productViewModel.dispatchEvent(ProductDescriptionUiEvent.OnVirtualTryOnTap)
                            }, modifier = Modifier.align(Alignment.CenterHorizontally),
                            shape = RoundedCornerShape(50)
                        ) {
                            Text("Try-on Virtually")
                        }

                        Column(
                            modifier =
                            Modifier.padding(horizontal = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(product.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                            Text(product.color, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Text(product.description, fontSize = 14.sp)
                            Spacer(modifier = Modifier.padding(bottom = 100.dp))
                        }
                    }

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .zIndex(2f)
                    ) {
                        Divider()
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Column(Modifier.weight(2f)) {
                                Text("Amount", fontWeight = FontWeight.Light)
                                Text(product.priceInCents.toDollars(), fontWeight = FontWeight.Bold)
                            }
                            Button(
                                onClick = {

                                    productViewModel.dispatchEvent(ProductDescriptionUiEvent.OnAddToCartTap)
                                }, modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(50)
                            ) {
                                Text("Add To Cart", textAlign = TextAlign.Center)
                            }
                        }
                    }
                }
            }
        )

    }
}

private fun Int.toDollars(): String {
    return "$" + BigDecimal(this).movePointLeft(2).toString()
}