package com.ihridoydas.simpleapp.util.responsiveUI.component.drawerNavigation


import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.util.responsiveUI.component.drawerNavigation.navoption.listOfNavOptionHorizontalSource
import com.ihridoydas.simpleapp.util.responsiveUI.component.drawerNavigation.navoption.listOfNavOptionSource
import com.ihridoydas.simpleapp.util.responsiveUI.component.drawerNavigation.navoption.ui.NavOptionHorizontalUI
import com.ihridoydas.simpleapp.util.responsiveUI.component.drawerNavigation.navoption.ui.NavOptionUI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NavDrawer(state: ScaffoldState, coroutineScope: CoroutineScope) {
    Column {
        Header()
        NavOption()
        Divider()
        Text(
            text = "Favourite Memories...",
            modifier = Modifier.padding(start = 15.dp, top = 15.dp),
            style = MaterialTheme.typography.body2 + TextStyle(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colors.secondaryVariant
        )
        NavHorizontalOption()
        Logout(state, coroutineScope)
    }
}

@Composable
fun Header() {
    Image(
        painter = painterResource(id = R.drawable.user_one),
        contentDescription = null,
        modifier = Modifier
            .height(300.dp)
            .padding(5.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(23.dp)),
        contentScale = ContentScale.Crop
    )
}


@Composable
fun NavOption() {
    Column(
        modifier = Modifier
            .padding(5.dp)
            .verticalScroll(rememberScrollState())
    ) {
        listOfNavOptionSource().forEach {
            NavOptionUI(data = it)
        }

    }
}


@Composable
fun NavHorizontalOption() {
    Row(
        modifier = Modifier
            .padding(5.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        listOfNavOptionHorizontalSource().forEach { data ->
            NavOptionHorizontalUI(data = data)
        }
    }
}


@Composable
fun Logout(state: ScaffoldState, coroutineScope: CoroutineScope) {
    Button(
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondaryVariant),
        onClick = { coroutineScope.launch { state.drawerState.close() } },
        modifier = Modifier
            .padding(all = 10.dp)
            .fillMaxWidth(),
    )
    {
        Text(text = "Logout", color = Color.White)
    }
}