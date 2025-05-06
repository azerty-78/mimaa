package com.kobe.mimaa.presentation.navgraph

import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kobe.mimaa.ui.view.communityScreen.aiOption.chat.ChatWithAIScreen
import com.kobe.mimaa.ui.view.authentification.ForgotPasswordScreen
import com.kobe.mimaa.ui.view.authentification.SingInScreen
import com.kobe.mimaa.ui.view.authentification.SignUpScreen
import com.kobe.mimaa.ui.view.authentification.state.Auth_event
import com.kobe.mimaa.ui.view.authentification.state.Auth_viewModel
import com.kobe.mimaa.ui.view.communityScreen.CommunityScreen
import com.kobe.mimaa.ui.view.homescreen.HomeScreen
import com.kobe.mimaa.ui.view.homescreen.TopicDetailScreen
import com.kobe.mimaa.ui.view.homescreen.TopicListScreen
import com.kobe.mimaa.ui.view.profileScreen.ProfileScreen
import com.kobe.mimaa.ui.view.settingsScreen.SettingsScreen


//authentification graph
fun NavGraphBuilder.authGraph(navController: NavController){
    navigation(
        startDestination = Routes.Screen.SignInScreen.route,
        route = Routes.AUTH_GRAPHROUTE
    ){
        composable(route = Routes.Screen.SignInScreen.route){
            val viewModel: Auth_viewModel = hiltViewModel()

            // Gérer la navigation après connexion réussie
            LaunchedEffect(viewModel.uiState.value.successSignIn) {
                if (viewModel.uiState.value.successSignIn) {
                    navController.navigate(Routes.HOME_GRAPHROUTE) {
                        popUpTo(Routes.AUTH_GRAPHROUTE) { inclusive = true }
                    }
                }
            }

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == RESULT_OK) {
                        viewModel.onEvent(Auth_event.OnGoogleSignInResult(result.data))
                    }
                }
            )

            SingInScreen(
                navController = navController,
                onSignUpClick = {
                    navController.navigate(Routes.Screen.SingUpScreen.route) {
                        popUpTo(Routes.Screen.SingUpScreen.route) { inclusive = true }
                    }
                },
                onForgotPwdClick = {
                    navController.navigate(Routes.Screen.ForgotPasswordScreen.route){
                        popUpTo(Routes.Screen.ForgotPasswordScreen.route) { inclusive = true }
                    }
                },
                onSignInSuccess = {
//                    navController.navigate(Routes.HOME_GRAPHROUTE){
//                        popUpTo(Routes.AUTH_GRAPHROUTE)
//                    }
                },
                onSignInWithGoogle = {
                    viewModel.onEvent(Auth_event.OnSignInWithGoogle { intentSender ->
                        launcher.launch(IntentSenderRequest.Builder(intentSender).build())
                    })
                }
            )
        }
        composable(Routes.Screen.SingUpScreen.route){
            SignUpScreen(
                navController = navController,
                onSignInClick = {
                    navController.navigate(Routes.Screen.SignInScreen.route) {
                        popUpTo(Routes.Screen.SignInScreen.route) { inclusive = true }
                    }
                },
                onSignUpSuccess = {
                    navController.navigate(Routes.Screen.SignInScreen.route){
                        popUpTo(Routes.AUTH_GRAPHROUTE){ inclusive = true }
                    }
                }
            )
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
            val viewModel: Auth_viewModel = hiltViewModel()

            LaunchedEffect(Unit) {
                viewModel.getSignedUser()
            }

            HomeScreen(
                navController = navController,
                currentUser = viewModel.uiState.value.currentUser
            )
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
            val viewModel: Auth_viewModel = hiltViewModel()
            LaunchedEffect(Unit) {
                viewModel.getSignedUser()
            }
            CommunityScreen(navController = navController)
        }
        composable(
            route = Routes.Screen.ChatWithAIScreen.route,
            arguments = listOf()
        ) {
            ChatWithAIScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
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
            val viewModel: Auth_viewModel = hiltViewModel()
            LaunchedEffect(Unit) {
                viewModel.getSignedUser()
            }

            SettingsScreen(
                navController = navController,
                onLogout = {
                    viewModel.onEvent(Auth_event.OnLogout)
                    navController.navigate(Routes.AUTH_GRAPHROUTE) {
                        popUpTo(0)
                    }
                }
            )
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
            val viewModel: Auth_viewModel = hiltViewModel()
            LaunchedEffect(Unit) {
                viewModel.getSignedUser()
            }

            ProfileScreen(navController = navController)
        }
        //more...
    }
}

