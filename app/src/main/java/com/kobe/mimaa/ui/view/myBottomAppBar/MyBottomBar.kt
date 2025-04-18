package com.kobe.mimaa.ui.view.myBottomAppBar

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kobe.mimaa.R
import com.kobe.mimaa.presentation.navgraph.Routes

@Composable
fun MyBottomAppBar(
    navController: NavController
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar {
        bottomNavItems.forEach { item ->
            val isSelected = currentRoute == item.route

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    BadgedBox(
                        badge = {
                            if (item.badge > 0) Badge { Text(item.badge.toString()) }
                            else if (item.hasNews) Badge()
                        }
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (isSelected) item.selectedIcon
                                else item.unselectedIcon
                            ),
                            contentDescription = item.title
                        )
                    }
                },
                label = { Text(item.title) }
            )
        }
    }
}

private val bottomNavItems = listOf(
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

private data class BottomNavItem(
    val title: String,
    val route: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val hasNews: Boolean = false,
    val badge: Int = 0
)