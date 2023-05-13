package com.ihridoydas.simpleapp.util.responsiveUI.component.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ihridoydas.simpleapp.navigation.animationNavHost.ScreenDestinations

@Composable
fun ListScreen(items: List<String>, onItemClick: (String) -> Unit) {
    LazyColumn {
        items(items) { item ->
            Card(
                modifier = Modifier
                    .clickable { onItemClick(item) }
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = item,
                    style = TextStyle(fontSize = 16.sp),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun DetailScreen(item: String) {
    val context = LocalContext.current
    val navController = NavHostController(context)
    navController.navigate(item){
        popUpTo(ScreenDestinations.HomeScreen.route) {
            inclusive = true
        }
    }
}

@Composable
fun CardMainScreen() {
    val items = listOf("Item 1", "Item 2", "Item 3")
    val selectedItem = remember { mutableStateOf<String?>(null) }

    if (selectedItem.value != null) {
        DetailScreen(item = selectedItem.value!!)
    } else {
        ListScreen(items = items) { item ->
            selectedItem.value = item
        }
    }
}