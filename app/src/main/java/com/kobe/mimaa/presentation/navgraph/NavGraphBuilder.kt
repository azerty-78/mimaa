package com.kobe.mimaa.presentation.navgraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kobe.mimaa.ui.view.authentification.ForgotPasswordScreen
import com.kobe.mimaa.ui.view.authentification.LoginScreen
import com.kobe.mimaa.ui.view.authentification.SignUpScreen
import com.kobe.mimaa.ui.view.communityScreen.CommunityScreen
import com.kobe.mimaa.ui.view.homescreen.HomeScreen
import com.kobe.mimaa.ui.view.homescreen.TopicDetailScreen
import com.kobe.mimaa.ui.view.homescreen.TopicListScreen
import com.kobe.mimaa.ui.view.profileScreen.ProfileScreen
import com.kobe.mimaa.ui.view.settingsScreen.SettingsScreen


//authentification graph
fun NavGraphBuilder.authGraph(navController: NavController){
    navigation(
        startDestination = Routes.Screen.LoginScreen.route,
        route = Routes.AUTH_GRAPHROUTE
    ){
        composable(Routes.Screen.LoginScreen.route){
            LoginScreen(navController = navController)
        }
        composable(Routes.Screen.SingUpScreen.route){
            SignUpScreen(navController = navController)
        }
        composable(Routes.Screen.ForgotPasswordScreen.route){
            ForgotPasswordScreen(navController = navController)
        }
    }
}

//home graph
fun NavGraphBuilder.homeGraph(navController: NavController){
    navigation(
        startDestination =Routes.Screen.HomeScreen.route,
        route = Routes.HOME_GRAPHROUTE
    ){
        composable(Routes.Screen.HomeScreen.route){
            HomeScreen(navController = navController)
        }
        composable(Routes.Screen.TopicList.route){
            TopicListScreen(navController = navController)
        }
        composable(Routes.Screen.TopicDetail.route){navBackStackEntry ->
            val topicId = navBackStackEntry.arguments?.getString("topicId")
            TopicDetailScreen(
                topicId = topicId,
                navController = navController
            )
        }
        //more...
    }
}

//community graph
fun NavGraphBuilder.communityGraph(navController: NavController){
    navigation(
        startDestination = Routes.Screen.CommunityScreen.route,
        route = Routes.COMMUNITY_GRAPHROUTE
    ){
        composable(Routes.Screen.CommunityScreen.route){
            CommunityScreen(navController = navController)
        }
        //more...
    }
}

//settings graph
fun NavGraphBuilder.settingsGraph(navController: NavController){
    navigation(
        startDestination = Routes.Screen.SettingsScreen.route,
        route = Routes.SETTINGS_GRAPHROUTE
    ){
        composable(Routes.Screen.SettingsScreen.route){
            SettingsScreen(navController = navController)
        }
        //more...
    }
}

//profile graph
fun NavGraphBuilder.profileGraph(navController: NavController){
    navigation(
        startDestination = Routes.Screen.ProfileScreen.route,
        route = Routes.PROFILE_GRAPHROUTE
    ){
        composable(Routes.Screen.ProfileScreen.route){
            ProfileScreen(navController = navController)
        }
        //more...
    }
}

