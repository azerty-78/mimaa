package com.kobe.mimaa.ui.view.components.myBottomAppBar

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MyBottomAppBar(
    navController: NavController
) {
    val navBackSactEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackSactEntry?.destination?.route
    val isMainRoute = bottomNavItems.any { it.route == currentRoute } // Filtre pour ne garder que les routes principales

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground
    ) {
        bottomNavItems.forEach { item ->
            val isSelected = currentRoute == item.route

            NavigationBarItem(
                selected = isSelected && isMainRoute,
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
                            contentDescription = item.title,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                label = {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            )
        }
    }
}