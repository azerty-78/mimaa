package com.kobe.mimaa.ui.view.homescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.kobe.mimaa.data.source.local.User
import com.kobe.mimaa.ui.view.myBottomAppBar.MyBottomAppBar

@Composable
fun HomeScreen(
    navController: NavController,
    currentUser: User? = null
) {
    val user = currentUser ?: return
    Scaffold(
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
            Text(text = "Bonjour ${user.email} comment vas tu ?",)
        }
    }


}