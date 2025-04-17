package com.kobe.mimaa.ui.view.myBottomAppBar

import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

}

//val bottomNavItem = listOf(
//    BottomNavItem(
//        title = "Home",
//        route = Routes.Screen.HomeScreen.route,
//        selectedIcon = painterResource(R.drawable.home_filled_icn),
//        unselecetedIcon = painterResource(R.drawable.home_unfilled_icn),
//        hasNews = false,
//        badge = 0
//    )
//)

data class BottomNavItem(
    val title : String,
    val route: String,
    val selectedIcon : Painter,
    val unselecetedIcon : Painter,
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