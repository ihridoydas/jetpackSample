package com.ihridoydas.simpleapp.util.responsiveUI.component.animations.dragAndDrop.businessCardDesign

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.dragAndDrop.businessCardDesign.utils.BonusCardDesign
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.dragAndDrop.businessCardDesign.utils.CardDecoration
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.dragAndDrop.businessCardDesign.utils.DragTarget
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.dragAndDrop.businessCardDesign.utils.DragTargetInfo
import com.ihridoydas.simpleapp.util.responsiveUI.component.animations.dragAndDrop.businessCardDesign.utils.LongPressDraggable


@Composable
fun BusinessCardDragAndDropContent() {
    val bonusCardDesign = remember { BonusCardDesign() }
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        LongPressDraggable(modifier = Modifier.fillMaxSize()) {
            lateinit var bonusCardRect: Rect
            BonusCard(Modifier.onGloballyPositioned {
                it.boundsInParent().let { rect ->
                    bonusCardRect = rect
                }
            }, bonusCardDesign)
            StickersRow(
                stickersRes = listOf(
                    R.drawable.cat,
                    R.drawable.otter,
                    R.drawable.dog,
                    R.drawable.unicorn,
                    R.drawable.fox,
                    R.drawable.racoon,
                    R.drawable.hamster,
                    R.drawable.tiger,
                ),
                onDragEnd = {
                    if (bonusCardRect.contains(it.getNewPosition())) {
                        bonusCardDesign.decorations.add(
                            CardDecoration(
                                it.dataToDrop as Int,
                                it.getNewPosition()
                            )
                        )
                    }
                }
            )
            bonusCardDesign.decorations.forEach {
                Image(
                    painter = painterResource(id = it.drawableRes),
                    contentDescription = null,
                    modifier = Modifier
                        .wrapContentSize()
                        .offset { IntOffset(it.position.x.toInt(), it.position.y.toInt()) }
                )
            }
        }
    }
}

@Composable
fun BoxScope.StickersRow(
    stickersRes: List<Int>,
    onDragEnd: (dragTargetInfo: DragTargetInfo) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxHeight(0.3f)
            .fillMaxWidth()
            .background(
                Color.LightGray,
                shape = RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp)
            )
            .padding(vertical = 10.dp)
            .align(Alignment.BottomCenter),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        items(items = stickersRes) { res ->
            StickerItem(res, onDragEnd)
        }
    }
}

@Composable
fun BoxScope.BonusCard(modifier: Modifier, design: BonusCardDesign) {
    Card(
        elevation = 10.dp,
        backgroundColor = design.background.value,
        shape = RoundedCornerShape(24.dp),
        modifier = modifier
            .width(250.dp)
            .height(400.dp)
            .align(Alignment.TopCenter)
            .padding(top = 16.dp),
        content = {}
    )
}

@Composable
fun StickerItem(@DrawableRes stickerRes: Int, onDragEnd: (dragTargetInfo: DragTargetInfo) -> Unit) {
    Box(
        Modifier
            .padding(8.dp)
            .border(BorderStroke(2.dp, Color.Black), RoundedCornerShape(16.dp))
            .background(Color.White, RoundedCornerShape(16.dp))
    ) {
        DragTarget(
            modifier = Modifier,
            dataToDrop = stickerRes,
            onDragEnd = onDragEnd,
            content = {
                Image(
                    painter = painterResource(id = stickerRes),
                    contentDescription = null,
                    modifier = Modifier.wrapContentSize()
                )
            }
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DefaultPreview() {
    SimpleAppTheme {
        BusinessCardDragAndDropContent()
    }
}