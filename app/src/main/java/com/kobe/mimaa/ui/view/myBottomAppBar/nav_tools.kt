package com.kobe.mimaa.ui.view.myBottomAppBar

import com.kobe.mimaa.R
import com.kobe.mimaa.presentation.navgraph.Routes

val bottomNavItems = listOf(
    BottomNavItem(
        title = "Home",
        route = Routes.Screen.HomeScreen.route,
        selectedIcon = R.drawable.home_filled_icn,
        unselectedIcon = R.drawable.home_unfilled_icn
    ),
    BottomNavItem(
        title = "Community",
        route = Routes.Screen.CommunityScreen.route,
        selectedIcon = R.drawable.community_filled_icn,
        unselectedIcon = R.drawable.community_unfilled_icn
    ),
    BottomNavItem(
        title = "Settings",
        route = Routes.Screen.SettingsScreen.route,
        selectedIcon = R.drawable.setting_filled_icn,
        unselectedIcon = R.drawable.settings_unfilled_icn
    ),
    BottomNavItem(
        title = "Profile",
        route = Routes.Screen.ProfileScreen.route,
        selectedIcon = R.drawable.person_filled_icn,
        unselectedIcon = R.drawable.person_unfilled_icn
    )
)

data class BottomNavItem(
    val title: String,
    val route: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val hasNews: Boolean = false,
    val badge: Int = 0
)