package com.ihridoydas.simpleapp.util.showcase.onBoarding


import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.ihridoydas.simpleapp.navigation.HomeScreenSpec
import com.ihridoydas.simpleapp.navigation.ProfileScreenSpec
import com.ihridoydas.simpleapp.navigation.WebViewSpec
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun OnBoarding(onBackPress : ()->Unit,navController: NavController) {

    val scope= rememberCoroutineScope()

    Column(Modifier.fillMaxSize()) {
        TopSection(onBackPress,navController)

        val items=OnBoardingItem.get()
        val state= rememberPagerState(initialPage = 0)

        HorizontalPager(
            count = items.size,
            state = state,
            modifier= Modifier
                .fillMaxSize()
                .weight(0.8f)
        ) {page->

            OnBoardingItem(items[page])

        }

        BottomSection(size = items.size, index = state.currentPage) {
            if (state.currentPage+1 <items.size)
                scope.launch {
                    state.scrollToPage(state.currentPage+1)
                }
        }

    }

}

@Composable
fun TopSection(onBackPress : ()->Unit,navController: NavController) {
    Box(
        modifier= Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ){

        //back button
        IconButton(
            onClick = {onBackPress() },
            modifier=Modifier.align(Alignment.CenterStart)
        ) {
            Icon(Icons.Outlined.KeyboardArrowLeft,null)
        }

        //skip button
        TextButton(
            onClick = {
                navController?.navigate(WebViewSpec.requestNavigationRoute()) {
                    popUpTo(HomeScreenSpec.route) {
                        inclusive = true
                    }
                }
            },
            modifier=Modifier.align(Alignment.CenterEnd)
        ) {
            Text("Skip",color=MaterialTheme.colors.onBackground)
        }

    }
}

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