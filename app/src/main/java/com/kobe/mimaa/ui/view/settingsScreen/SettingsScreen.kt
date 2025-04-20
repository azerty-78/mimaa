package com.kobe.mimaa.ui.view.settingsScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kobe.mimaa.ui.view.myBottomAppBar.MyBottomAppBar

@Composable
fun SettingsScreen(
    navController: NavController,
    onLogout: () -> Unit = {}
) {
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
            Text(text = "Bonjour Ben comment vas tu dans Settings ?")
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedButton(
                onClick = { onLogout() },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                    disabledBackgroundColor = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 2f
                    )
                ),
                shape = RoundedCornerShape(8.dp),
                elevation = ButtonDefaults.elevation(10.dp),
                modifier = Modifier.fillMaxWidth().height(50.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
            )
            {
                Text(
                    text = "Deconnxion",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }

}