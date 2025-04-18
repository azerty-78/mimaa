package com.kobe.mimaa

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kobe.mimaa.presentation.navgraph.Routes
import com.kobe.mimaa.presentation.navgraph.authGraph
import com.kobe.mimaa.presentation.navgraph.communityGraph
import com.kobe.mimaa.presentation.navgraph.homeGraph
import com.kobe.mimaa.presentation.navgraph.profileGraph
import com.kobe.mimaa.presentation.navgraph.settingsGraph
import com.kobe.mimaa.ui.view.communityScreen.CommunityScreen
import com.kobe.mimaa.ui.view.homescreen.HomeScreen
import com.kobe.mimaa.ui.view.profileScreen.ProfileScreen
import com.kobe.mimaa.ui.view.settingsScreen.SettingsScreen


@Composable
fun MainScreen(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.AUTH_GRAPHROUTE
    ){
        authGraph(navController = navController)
        homeGraph(navController = navController)
        communityGraph(navController = navController)
        settingsGraph(navController = navController)
        profileGraph(navController = navController)
    }
}