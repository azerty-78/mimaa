package com.kobe.mimaa.ui.view.communityScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kobe.mimaa.R
import com.kobe.mimaa.presentation.navgraph.Routes
import com.kobe.mimaa.ui.view.myBottomAppBar.MyBottomAppBar

@Composable
fun CommunityScreen(
    navController: NavController
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Routes.Screen.ChatWithAIScreen.route){
                        popUpTo(Routes.Screen.CommunityScreen.route){
                            inclusive = true
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    } 
                },
                shape = RoundedCornerShape(12.dp),
            ){
                Icon(
                    painter = painterResource(R.drawable.chat_with_ai_filled),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        bottomBar = {
            MyBottomAppBar(navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Bonjour Ben comment vas tu dans Community ?")
        }
    }

}