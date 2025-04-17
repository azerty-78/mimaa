package com.kobe.mimaa.ui.view.myBottomAppBar

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kobe.mimaa.R
import com.kobe.mimaa.presentation.navgraph.Routes
import okhttp3.Route

@Composable
fun MyBottomAppBar(
    navController: NavController,
) {
    val currentRoute = currentRoute(navController)
    var selectedIndex by remember { mutableStateOf(0) }

    selectedIndex = when(currentRoute){
        Routes.Screen.HomeScreen.route -> 0
        Routes.Screen.CommunityScreen.route -> 1
        Routes.Screen.SettingsScreen.route -> 2
        Routes.Screen.ProfileScreen.route -> 3
        else -> 0
    }

    NavigationBar {
        bottomNavItem.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedIndex,
                onClick = {
                    navController.navigate(item.route){
                        popUpTo(Routes.Screen.HomeScreen.route){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    BadgedBox(
                        badge = {
                            if (item.badge != 0) Badge { Text(text = item.badge.toString()) }
                            else if (item.hasNews) Badge()
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = if(index == selectedIndex) item.selectedIcon else item.unselecetedIcon),
                            contentDescription = item.title
                        )
                    }
                },
                label = { Text(text = item.title) },
            )
        }

    }

}

val bottomNavItem = listOf(
    BottomNavItem(
        title = "Home",
        route = Routes.Screen.HomeScreen.route,
        selectedIcon = (R.drawable.home_filled_icn),
        unselecetedIcon = (R.drawable.home_unfilled_icn),
        hasNews = false,
        badge = 0
    ),
    BottomNavItem(
        title = "Community",
        route = Routes.Screen.CommunityScreen.route,
        selectedIcon = (R.drawable.community_filled_icn),
        unselecetedIcon = (R.drawable.community_unfilled_icn),
        hasNews = false,
        badge = 0
    ),
    BottomNavItem(
        title = "Settings",
        route = Routes.Screen.SettingsScreen.route,
        selectedIcon = (R.drawable.setting_filled_icn),
        unselecetedIcon = (R.drawable.settings_unfilled_icn),
        hasNews = false,
        badge = 0
    ),
    BottomNavItem(
        title = "Profile",
        route = Routes.Screen.ProfileScreen.route,
        selectedIcon = (R.drawable.person_filled_icn),
        unselecetedIcon = (R.drawable.person_unfilled_icn),
        hasNews = false,
        badge = 0
    ),

)

data class BottomNavItem(
    val title : String,
    val route: String,
    val selectedIcon : Int,
    val unselecetedIcon : Int,
    val hasNews: Boolean,
    val badge : Int
)

@Composable
fun currentRoute(
    navController: NavController
): String?{
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}