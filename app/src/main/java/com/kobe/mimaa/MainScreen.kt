package com.kobe.mimaa

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kobe.mimaa.presentation.navgraph.Routes
import com.kobe.mimaa.presentation.navgraph.authGraph
import com.kobe.mimaa.presentation.navgraph.communityGraph
import com.kobe.mimaa.presentation.navgraph.dashboardGraph
import com.kobe.mimaa.presentation.navgraph.homeGraph
import com.kobe.mimaa.presentation.navgraph.profileGraph
import com.kobe.mimaa.presentation.navgraph.settingsGraph


@Composable
fun MainScreen(
    navController: NavHostController
) {
    // Empêche la réinitialisation lors de la rotation
    DisposableEffect(Unit) { onDispose { } }
    NavHost(
        navController = navController,
        startDestination = Routes.AUTH_GRAPHROUTE,
    ){
        authGraph(navController = navController)
        homeGraph(navController = navController)
        dashboardGraph(navController = navController)
        communityGraph(navController = navController)
        settingsGraph(navController = navController)
        profileGraph(navController = navController)
    }
}