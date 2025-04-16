package com.kobe.mimaa.presentation.navgraph


object Routes{
    const val AUTH_GRAPHROUTE = "auth_graph"
    const val HOME_GRAPHROUTE = "home_graph"
    const val SETTINGS_GRAPHROUTE = "settings_graph"
    const val PROFILE_GRAPHROUTE = "profile_graph"
    const val COMMUNITY_GRAPHROUTE = "community_graph"

    sealed class Screen(
        val route: String
    ){
        //route du graph authentification
        object LoginScreen : Screen("login_screen")
        object SingUpScreen : Screen("singup_screen")
        object ForgotPasswordScreen : Screen("forgotpassword_screen")

        //route du graph home
        object HomeScreen : Screen("home_screen")

        //route du graph community
        object CommunityScreen : Screen("home_screen")

        //route du graph settings
        object SettingsScreen : Screen("home_screen")

        //route du graph profile
        object ProfileScreen : Screen("home_screen")
    }
}


