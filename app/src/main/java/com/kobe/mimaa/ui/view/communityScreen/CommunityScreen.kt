package com.kobe.mimaa.ui.view.communityScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.kobe.mimaa.ui.view.myBottomAppBar.MyNavigationOnRail

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun CommunityScreen(
    navController: NavController
) {

    BoxWithConstraints(modifier = Modifier.fillMaxSize()){
        if(maxWidth > 600.dp){
            Row(modifier = Modifier.fillMaxSize()){
                MyNavigationOnRail(navController = navController)

                //other things
            }
        }else{

            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            navController.navigate(Routes.Screen.ChatWithAIScreen.route){
                                popUpTo(Routes.Screen.CommunityScreen.route){
                                    saveState = false
                                }
                                launchSingleTop = true
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
    }
}