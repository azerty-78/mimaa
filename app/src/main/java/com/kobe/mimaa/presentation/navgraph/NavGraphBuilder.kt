package com.kobe.mimaa.presentation.navgraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kobe.mimaa.ui.view.authentification.ForgotPasswordScreen
import com.kobe.mimaa.ui.view.authentification.LoginScreen
import com.kobe.mimaa.ui.view.authentification.SignUpScreen


//on definit le graph d'authentification
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