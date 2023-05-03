package com.ihridoydas.simpleapp.util.showcase.onBoarding


import android.net.wifi.hotspot2.pps.HomeSp
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ihridoydas.simpleapp.navigation.animationNavHost.ScreenDestinations


@Composable
fun BottomSection(
    size: Int,
    index: Int,
    onNextClicked:()->Unit
) {
    Box(
        modifier= Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ){

        //indicators
        Indicators(size = size, index = index)

        //next button
        FloatingActionButton(
            onClick =onNextClicked,
            modifier=Modifier.align(CenterEnd),
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
        ) {
            Icon(Icons.Outlined.KeyboardArrowRight,null)
        }

    }
}

@Composable
fun BoxScope.Indicators(size:Int,index:Int){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.align(Alignment.CenterStart)
    ) {
        repeat(size){
            Indicator(isSelected = it == index)
        }
    }
}


@Composable
fun Indicator(isSelected:Boolean){
    val width= animateDpAsState(
        targetValue = if (isSelected) 25.dp else 10.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )

    Box(
        modifier = Modifier
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if (isSelected) MaterialTheme.colors.primary
                else MaterialTheme.colors.onBackground.copy(alpha = 0.5f)
            )
    ){

    }
}

@Composable
fun OnBoardingItem(
    item:OnBoardingItem
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(painter = painterResource(item.image), contentDescription = null)

        Text(
            text = stringResource(item.title),
            fontSize = 24.sp,
            color=MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = stringResource(item.text),
            color=MaterialTheme.colors.onBackground.copy(alpha=0.8f),
            textAlign= TextAlign.Center
        )
    }
}


@Composable
fun TopSection(navController: NavController) {
    Box(
        modifier= Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ){

        //back button
        IconButton(
            onClick = { },
            modifier=Modifier.align(Alignment.CenterStart)
        ) {
            Icon(Icons.Outlined.KeyboardArrowLeft,null)
        }

        //skip button
        TextButton(
            onClick = {
                navController?.navigate(ScreenDestinations.StartShowCaseScreen.route) {
                    popUpTo(ScreenDestinations.HomeScreen.route) {
                        inclusive = true
                    }
                }
            },
            modifier=Modifier.align(CenterEnd)
        ) {
            Text("Skip",color=MaterialTheme.colors.onBackground)
        }

    }
}