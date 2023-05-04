package com.ihridoydas.simpleapp.navigation.animationNavHost

import androidx.navigation.NavHostController

fun NavHostController.navigateTo(route: String) = navigate(route) {
    popUpTo(route)
    launchSingleTop = true
}