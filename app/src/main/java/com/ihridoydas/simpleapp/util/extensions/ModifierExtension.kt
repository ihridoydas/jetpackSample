
package com.ihridoydas.simpleapp.util.extensions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp

fun Modifier.conditional(condition : Boolean, modifier : Modifier.() -> Modifier) : Modifier {
    return if (condition) {
        modifier.invoke(this)
    } else {
        this
    }
}

/**
 * Rippleエフェクト無しクリック
 * @param enabled 有効無効
 * @param onClickLabel ラベル
 * @param role Role
 * @param onClick クリック処理
 * @return Modifier
 */
fun Modifier.clickableNoRipple(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
): Modifier = composed {
    this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
        onClick = onClick
    )
}

//Icons And Image Badge Extension
fun Modifier.badge(toggle:Boolean, radius: Dp, color : Color = Color.Red ,topPadding: Dp, rightPadding : Dp) : Modifier {

    return this.drawWithContent {
        //draw the image itself
        this.drawContent()
        if (toggle) {
            //draw the badge over the image
            this.drawCircle(
                color,
                radius.toPx(),
                Offset(size.width - rightPadding.toPx(),topPadding.toPx())
            )
        }
    }
}
