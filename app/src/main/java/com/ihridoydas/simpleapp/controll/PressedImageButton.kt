package com.ihridoydas.simpleapp.controll

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun PressedImageButton(
    modifier: Modifier = Modifier,
    upImageId: Int,
    downImageId: Int? = null,
    disabledId: Int? = null,
    enabled: Boolean = true,
    hidden: Boolean = false,
    contentDesc: String,
    onClick: () -> Unit = { },
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    var alpha = if ((isPressed && downImageId==null) || !enabled) 0.4f else 1f
    if (hidden) alpha = 0f

    IconButton(onClick =  if (hidden) {{}} else onClick, interactionSource = interactionSource, modifier = modifier, enabled = enabled) {
        painterResource(
            id = if (isPressed && enabled) downImageId ?: upImageId else
                (if (enabled || disabledId == null) upImageId else disabledId)
        ).run {
            Image(modifier = Modifier.height(44.dp).width(76.dp).alpha(alpha), painter = this, contentDescription = contentDesc)
        }
    }
}

//Uses this function like this

/**

PressedImageButton(
    onClick = { onClickCameraCloseBtn() },
    modifier = Modifier
    .testTag(TestTags.CAMERA_CLOSE_BUTTON)
    .fillMaxWidth()
    .alpha(cancelAlpha),
    upImageId = R.drawable.ic_camera_close,
    downImageId = R.drawable.ic_camera_close_active,
    enabled = !isDisabledUI,
    contentDesc = context.getString(R.string.close_button) + if (cancelAlpha == 1f) " visible" else " hide"
)
 */
