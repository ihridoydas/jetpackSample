@file:Suppress("UNUSED_EXPRESSION")

package com.ihridoydas.simpleapp.ar.arEcommerce.virtualtryon.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.R
import io.github.sceneview.ar.ARScene
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.ArNode


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VirtualTryOnScreen(
    productId: Int,
    virtualTryOnViewModel: VirtualTryOnViewModel,
    onBackPress:()->Unit
) {
    val nodes = remember { mutableStateListOf<ArNode>() }

    LaunchedEffect(Unit) {
        virtualTryOnViewModel.dispatchEvent(VirtualTryOnUIEvent.FetchAsset(productId))
    }

    val context = LocalContext.current
    var sceneView by remember { mutableStateOf<ArSceneView?>(null) }
    val viewState by virtualTryOnViewModel.state.collectAsState()
    val uiAction by virtualTryOnViewModel.uiAction.collectAsState()
    var modelNode by remember { mutableStateOf<ArModelNode?>(null) }

    when (uiAction) {
        is VirtualTryOnUIAction.ShowModalPlaced -> {
            LaunchedEffect(Unit) {
                Toast.makeText(context, "Placed model!", Toast.LENGTH_SHORT).show()
                virtualTryOnViewModel.onConsumedUiAction()
            }
        }
        null -> {}
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(Color.Black),
                title = { androidx.compose.material.Text(text = "Augmented ECommerce Product View" , style = TextStyle(color = Color.White)) },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackPress()
                        },
                        modifier = Modifier
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White )
                    }
                },
            )
        },
        drawerShape = RoundedCornerShape(topEnd = 23.dp, bottomEnd = 23.dp),
        content = {
            Box(modifier = Modifier.fillMaxSize().padding(it)) {
                ARScene(
                    modifier = Modifier.fillMaxSize(),
                    nodes = nodes,
                    planeRenderer = true,
                    onCreate = { arSceneView ->
                        // Apply your configuration
                        sceneView = arSceneView
                    },
                    onSessionCreate = { session ->
                        // Configure the ARCore session if you need augmented faces, images, etc
                    },
                    onFrame = { arFrame ->
                        // Update planes state to determine whether or not to UI message
                        // WARNING: DO NOT PASS ARSceneView/ARFrame TO ViewModel to avoid memory leaks
                        virtualTryOnViewModel.dispatchEvent(VirtualTryOnUIEvent.OnPlanesUpdated(arFrame.updatedPlanes))
                    },
                    onTap = { hitResult ->
                        // User tapped in the AR view
                        sceneView?.let {
                            modelNode = onUserTap(it, viewState)

                        }
                    },
                    onTrackingFailureChanged = { trackingFailureReason ->
                        """
                 You can also show a tracking failure message if needed
                    
                 virtualTryOnViewModel.dispatchEvent(
                      VirtualTryOnUIEvent.OnTrackingFailure(
                            trackingFailureReason
                    )
                 )
                """
                    }
                )


                if (viewState.downloadingAsset) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                } else {
                    if (!viewState.modelPlaced) {
                        if (viewState.readyToPlaceModel) {

                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(16.dp, bottom = 100.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(colorResource(id = R.color.translucent)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    "Ready to place model!",
                                    modifier = Modifier.padding(16.dp),
                                    textAlign = TextAlign.Center,
                                    color = Color.White
                                )
                            }
                        } else {
                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(16.dp, bottom = 100.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(colorResource(id = R.color.translucent)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    "Move your phone to place model",
                                    modifier = Modifier.padding(16.dp),
                                    textAlign = TextAlign.Center,
                                    color = Color.White
                                )
                            }
                        }
                    }

                    if (!viewState.modelPlaced) {
                        modelNode?.let {
                            sceneView?.planeRenderer?.isVisible = true
                            sceneView?.addChild(it)
                            sceneView?.selectedNode = it
                            virtualTryOnViewModel.dispatchEvent(VirtualTryOnUIEvent.ModelPlaced)
                        }
                    }
                    if (viewState.modelPlaced && modelNode != null) {


                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(16.dp, bottom = 100.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(colorResource(id = R.color.translucent)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Use one finger to move and two fingers to rotate the handbag!",
                                modifier = Modifier.padding(16.dp),
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )
                        }
                    }


                }
            }
        }
    )

}


fun onUserTap(sceneView: ArSceneView, viewState: VirtualTryOnViewState): ArModelNode {
    // Try to avoid placing 3d models in ViewModel to avoid memory leaks since ARNodes contains context
    return ArModelNode(
        sceneView.engine,
    ).apply {
        viewState.modelAsset?.let {
            loadModelGlbAsync(
                glbFileLocation = it,
            )
        }
    }
}

