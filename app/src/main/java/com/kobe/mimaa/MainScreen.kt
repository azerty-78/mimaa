package com.kobe.mimaa

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kobe.mimaa.presentation.navgraph.Routes
import com.kobe.mimaa.ui.view.communityScreen.CommunityScreen
import com.kobe.mimaa.ui.view.homescreen.HomeScreen
import com.kobe.mimaa.ui.view.profileScreen.ProfileScreen
import com.kobe.mimaa.ui.view.settingsScreen.SettingsScreen


@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HomeScreen.routes
    ){
        //home screen
        composable(Routes.HomeScreen.routes){
            HomeScreen(navController = navController)
        }

        //community screen
        composable(Routes.CommunityScreen.routes){
            CommunityScreen(navController = navController)
        }

        //settings screen
        composable(Routes.SettingsScreen.routes){
            SettingsScreen(navController = navController)
        }

        //profile screen
        composable(Routes.ProfileScreen.routes){
            ProfileScreen(navController = navController)
        }
    }

}