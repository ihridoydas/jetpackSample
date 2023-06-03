package com.ihridoydas.simpleapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * An 8dp grid system. Smaller components can align to a 2dp 'sub' grid.
 */
//object Dimens {
//    val grid_0_25 = 2.dp
//    val grid_0_5 = 4.dp
//    val grid_1 = 8.dp
//    val grid_1_5 = 12.dp
//    val grid_2 = 16.dp
//    val grid_2_5 = 20.dp
//    val grid_3 = 24.dp
//    val grid_3_5 = 28.dp
//    val grid_4 = 32.dp
//    val grid_4_5 = 36.dp
//    val grid_5 = 40.dp
//    val grid_5_5 = 44.dp
//    val grid_6 = 48.dp
//}


class Dimensions(
    val grid_0_25: Dp,
    val grid_0_5: Dp,
    val grid_1: Dp,
    val grid_1_5: Dp,
    val grid_2: Dp,
    val grid_2_5: Dp,
    val grid_3: Dp,
    val grid_3_5: Dp,
    val grid_4: Dp,
    val grid_4_5: Dp,
    val grid_5: Dp,
    val grid_5_5: Dp,
    val grid_6: Dp,
    val plane_0: Dp,
    val plane_1: Dp,
    val plane_2: Dp,
    val plane_3: Dp,
    val plane_4: Dp,
    val plane_5: Dp,
    val minimum_touch_target: Dp = 48.dp,
)

val smallDimensions = Dimensions(
    grid_0_25 = 1.5f.dp,
    grid_0_5 = 3.dp,
    grid_1 = 6.dp,
    grid_1_5 = 9.dp,
    grid_2 = 12.dp,
    grid_2_5 = 15.dp,
    grid_3 = 18.dp,
    grid_3_5 = 21.dp,
    grid_4 = 24.dp,
    grid_4_5 = 27.dp,
    grid_5 = 30.dp,
    grid_5_5 = 33.dp,
    grid_6 = 36.dp,
    plane_0 = 0.dp,
    plane_1 = 1.dp,
    plane_2 = 2.dp,
    plane_3 = 3.dp,
    plane_4 = 6.dp,
    plane_5 = 12.dp,
)

val sw360Dimensions = Dimensions(
    grid_0_25 = 2.dp,
    grid_0_5 = 4.dp,
    grid_1 = 8.dp,
    grid_1_5 = 12.dp,
    grid_2 = 16.dp,
    grid_2_5 = 20.dp,
    grid_3 = 24.dp,
    grid_3_5 = 28.dp,
    grid_4 = 32.dp,
    grid_4_5 = 36.dp,
    grid_5 = 40.dp,
    grid_5_5 = 44.dp,
    grid_6 = 48.dp,
    plane_0 = 0.dp,
    plane_1 = 1.dp,
    plane_2 = 2.dp,
    plane_3 = 4.dp,
    plane_4 = 8.dp,
    plane_5 = 16.dp,
)


//------------------------====
//For Composible Sheep

object Grid {
    val Single = 1.dp
    val Quarter = 2.dp
    val Half = 4.dp
    val One = 8.dp
    val OneHalf = 12.dp
    val Two = 16.dp
    val TwoHalf = 20.dp
    val Three = 24.dp
    val Four = 32.dp
    val Five = 40.dp
    val Six = 48.dp
    val Seven = 54.dp
    val Eight = 72.dp
    val Ten = 80.dp
}

object Dimens {
    val Three = 3.dp
    val Four = 4.dp
}

object ZIndex {
    const val Back = -1f
    const val None = 0f
    const val Front = 1f
    const val Top = 2f
}

object Elevation {
    val None = 0.dp
    val Front = 8.dp
    val Top = 16.dp
}

object TextUnit {
    val Twelve = 12.sp
    val Fourteen = 14.sp
    val Sixteen = 16.sp
    val Eighteen = 18.sp
    val Twenty = 20.sp
    val TwentyTwo = 22.sp
    val TwentyFour = 24.sp
    val TwentySix = 26.sp
    val Thirty = 30.sp
    val ThirtyFour = 34.sp
}

val GridCellMinWidth = 70.dp
val GridCellWidth = 64.dp
val FabSize = 72.dp

//------------------------====

@Composable
fun ProvideDimens(
    dimensions: Dimensions,
    content: @Composable () -> Unit
) {
    val dimensionSet = remember { dimensions }
    CompositionLocalProvider(LocalAppDimens provides dimensionSet, content = content)
}

val LocalAppDimens = staticCompositionLocalOf {
    smallDimensions
}

