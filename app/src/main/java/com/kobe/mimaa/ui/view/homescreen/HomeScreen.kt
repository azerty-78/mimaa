package com.kobe.mimaa.ui.view.homescreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kobe.mimaa.data.source.model.User
import com.kobe.mimaa.ui.view.components.myBottomAppBar.MyBottomAppBar
import com.kobe.mimaa.ui.view.components.myBottomAppBar.MyNavigationOnRail
import com.kobe.mimaa.ui.view.components.myTopAppBar.MyTopBar

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun HomeScreen(
    navController: NavController,
    currentUser: User? = null
) {
    val user = currentUser ?: return

    BoxWithConstraints(modifier = Modifier.fillMaxSize()){
        if(maxWidth > 600.dp){
            Row(modifier = Modifier.fillMaxSize()){
                MyNavigationOnRail(navController = navController)
                //other things
                MyTopBar(
                    user = user,
                    modifier = Modifier,
                    onNotificationClick = { /*--Rien pour le moment--*/ },
                    onSearchClick = { /*--Rien pour le moment--*/  }
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }else{

            Scaffold(
                topBar = {
                    MyTopBar(
                        user = user,
                        modifier = Modifier,
                        onNotificationClick = { /*--Rien pour le moment--*/ },
                        onSearchClick = { /*--Rien pour le moment--*/  }
                    )
                },
                bottomBar = {
                    MyBottomAppBar(navController = navController)
                },
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                    ,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = "Bonjour ${user.email} comment vas tu ?",)
                }
            }
        }
    }
}