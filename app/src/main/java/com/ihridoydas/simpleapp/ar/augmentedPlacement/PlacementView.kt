package com.ihridoydas.simpleapp.ar.augmentedPlacement


import android.app.Activity
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ihridoydas.simpleapp.ar.augmentedImage.setupAvConfigurations
import com.ihridoydas.simpleapp.ar.augmentedImage.setupTrackingFailureChanged
import com.ihridoydas.simpleapp.ar.augmentedModelView.StatusOfScreen
import com.ihridoydas.simpleapp.ar.augmentedModelView.anchorOrMove
import com.ihridoydas.simpleapp.ar.augmentedModelView.checkIsSupportedDeviceOrFinish
import com.ihridoydas.simpleapp.ar.augmentedModelView.lightEstimate
import com.ihridoydas.simpleapp.navigation.animationNavHost.ScreenDestinations
import com.ihridoydas.simpleapp.navigation.animationNavHost.navigateTo
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.dynamicIsland.NotSupportScreen
import io.github.sceneview.ar.ARScene
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.ArNode
import io.github.sceneview.ar.node.CursorNode
import io.github.sceneview.ar.node.PlacementMode
import io.github.sceneview.math.Position
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlacementView(onBackPress:()->Unit,activity: Activity,navController:NavHostController) {
    val _node = MutableStateFlow<ArNode?>(null)
    val nodes = _node.asStateFlow()
    val node by  nodes.collectAsState()

    lateinit var arModelNode: ArModelNode
    lateinit var cursorNode: CursorNode
    lateinit var sceneView: ArSceneView


    //Context
    val context= LocalContext.current
    //Global AR Screen View
     sceneView  = remember { ArSceneView(context) }

    //Coroutine State
    val scope = rememberCoroutineScope()

    //Screen Status
    var status by remember { mutableStateOf("") }

    //Scroll State
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(Color.Black),
                title = { androidx.compose.material.Text(text = "Augmented Placement View" , style = TextStyle(color = Color.White)) },
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
            Box(
                modifier = Modifier.padding(it).fillMaxSize(),
            ) {
                //check Device Support or not
                if (!checkIsSupportedDeviceOrFinish(activity)) {
                    NotSupportScreen(onBackPress = {
                        navController.navigateTo(ScreenDestinations.ViewScreen.route)
                    })
                }
                ARScene(
                    modifier = Modifier,
                    nodes = node?.let { listOf(it) } ?: emptyList(),//nodes,//
                    planeRenderer = true,
                    onCreate = { arSceneView ->
                        // Apply your configuration
                        arSceneView.planeRenderer.isVisible = true
                        sceneView = arSceneView
                        arSceneView.setupAvConfigurations()
                        arSceneView.isDepthOcclusionEnabled = false

                        //Light Estimate
                        scope.launch {
                            arSceneView?.apply {
                                planeRenderer.isVisible = true
                                onArSessionFailed = { _: Exception ->
                                    // If AR is not available or the camara permission has been denied, we add the model
                                    // directly to the scene for a fallback 3D only usage
                                    arModelNode.centerModel(origin = Position(x = 0.0f, y = 0.0f, z = 0.0f))
                                    arModelNode.scaleModel(units = 1.0f)
                                    arSceneView.addChild(arModelNode)
                                }
                                onTapAr = { hitResult, _ ->
                                    anchorOrMove(hitResult.createAnchor(), arModelNode = arModelNode, arSceneView = arSceneView)
                                }

                            }
                        }

                        arModelNode =
                            ArModelNode(placementMode = PlacementMode.BEST_AVAILABLE).apply {

                                loadModelGlbAsync(
                                    glbFileLocation = "sceneview/models/cursor.glb",
                                    autoAnimate = true,
                                    scaleToUnits = null,
                                    // Center the model horizontally and vertically
                                    // centerOrigin = Position(x = 0.0f, y = 0.0f, z = 0.0f)
                                ) {
                                    applyPoseRotation = true
                                    arSceneView.planeRenderer.isVisible = true
                                }
                                onAnchorChanged = { anchor ->

                                }
                                onHitResult = { node, _ ->

                                }
                            }
                        arSceneView.addChild(arModelNode!!)
                    },
                    onSessionCreate = { session -> },
                    onFrame = { arFrame -> },
                    onTap = { hitResult ->},
                    onTrackingFailureChanged = {
                        //Tracking ちゃんとできない場合
                        onArTrackingFailureChanged = { reason ->
                            status = reason?.apply {
                                setupTrackingFailureChanged(trackingFailureReason = this)
                            }.toString()

                        }

                    }
                )


                //Middle Section
                StatusOfScreen(status)
                //Bottom Section
                Column (modifier = Modifier.fillMaxSize(),
                    Arrangement.Bottom,Alignment.CenterHorizontally){
                    Row(modifier = Modifier
                        .horizontalScroll(state = scrollState)
                        .padding(5.dp)
                        .padding(bottom = 2.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Button(onClick = {
                            arModelNode.apply {
                                arModelNode.anchor()
                                arModelNode.detachAnchor()
                                arModelNode.takeIf { !it.isAnchored }?.let {
                                    sceneView.removeChild(it)
                                    it.destroy()
                                }
                            }
                        }, modifier = Modifier) {
                            Text(text = "Reset")
                        }
                    }
                }
            }
        }
    )


}