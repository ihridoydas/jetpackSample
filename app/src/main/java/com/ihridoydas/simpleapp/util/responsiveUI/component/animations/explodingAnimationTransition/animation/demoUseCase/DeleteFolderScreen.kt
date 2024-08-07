package dev.omkartenkale.explodable.sample

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ihridoydas.simpleapp.R
import androidx.constraintlayout.compose.ConstraintLayout
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.explodingAnimationTransition.animation.Explodable
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.explodingAnimationTransition.animation.rememberExplosionController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview
@Composable
fun DeleteFolderScreen() {
    val coroutineScope = rememberCoroutineScope()
    var folderOptionsVisible by remember { mutableStateOf(false) }
    val explosionController = rememberExplosionController()

    ConstraintLayout(
        modifier = Modifier
            .wrapContentSize()
    ) {
        val (background, folder) = createRefs()

        Image(modifier = Modifier
            .width(300.dp)
            .clip(RoundedCornerShape(12.dp))
            .constrainAs(background) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
            .background(Color.Red),
            painter = painterResource(id = R.drawable.file_explorer_background),
            contentDescription = null,
            contentScale = ContentScale.FillWidth)

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .padding(bottom = 21.dp)
            .constrainAs(folder) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }) {
            Explodable(controller = explosionController, onExplode = {
                coroutineScope.launch {
                    delay(2000)
                    explosionController.reset()
                }
            }) {
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.folder),
                        contentDescription = null,
                        modifier = Modifier
                            .size(36.dp)
                            .clickable {
                                folderOptionsVisible = true
                            })
                    Text(
                        text = "Study material",
                        fontSize = 6.sp,
                        color = Color.White,
                        modifier = Modifier
                            .padding(top = 2.dp)
                            .alpha(0.8f)
                    )
                }
            }
            DropdownMenu(
                expanded = folderOptionsVisible,
                onDismissRequest = { folderOptionsVisible = false }
            ) {
                DropdownMenuItem(onClick = {
                    folderOptionsVisible = false
                    explosionController.explode()
                }) {
                    Text("Delete")
                }
            }
        }
    }
}