package com.kobe.mimaa.presentation.navgraph

sealed class Routes(
    val routes: String
){
    object HomeScreen : Routes("home_screen")
    object CommunityScreen : Routes("home_screen")
    object SettingsScreen : Routes("home_screen")
    object ProfileScreen : Routes("home_screen")
}

