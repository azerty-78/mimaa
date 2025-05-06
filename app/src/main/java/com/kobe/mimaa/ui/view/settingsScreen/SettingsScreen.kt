package com.kobe.mimaa.ui.view.settingsScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.PageSize
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
import com.kobe.mimaa.R
import com.kobe.mimaa.ui.view.myBottomAppBar.MyBottomAppBar
import com.kobe.mimaa.ui.view.myBottomAppBar.MyNavigationOnRail
import com.kobe.mimaa.ui.view.settingsScreen.components.CardContent
import com.kobe.mimaa.ui.view.settingsScreen.components.RowContentItem

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun SettingsScreen(
    navController: NavController,
    onLogout: () -> Unit
) {
    val settingsItems = listOf(
        listOf(
            RowContentItem("Mes informations", R.drawable.p_icn) {},
            RowContentItem("Vérification", R.drawable.p_icn) {},
            RowContentItem("Mode Premium", R.drawable.p_icn) {},
            RowContentItem("Service client", R.drawable.p_icn) {}
        ),

        listOf(
            RowContentItem("Thème", R.drawable.p_icn) {},
            RowContentItem("Changer de mot de passe", R.drawable.p_icn) {},
            RowContentItem("Notifications", R.drawable.p_icn) {},
            RowContentItem("Langue", R.drawable.p_icn) {}
        ),

        listOf(
            RowContentItem("Thème", R.drawable.p_icn) {},
            RowContentItem("Changer de mot de passe", R.drawable.p_icn) {},
            RowContentItem("Notifications", R.drawable.p_icn) {},
            RowContentItem("Langue", R.drawable.p_icn) {}
        ),

        listOf(
            RowContentItem("Inviter un proche", R.drawable.p_icn) {},
            RowContentItem("Politique de confidentialité", R.drawable.p_icn) {},
            RowContentItem("Termes et conditions", R.drawable.p_icn) {},
            RowContentItem("Déconnexion", R.drawable.p_icn, { onLogout() })
        )
    )


    BoxWithConstraints(modifier = Modifier.fillMaxSize()){
        if(maxWidth > 600.dp){
            Row(modifier = Modifier.fillMaxSize()){
                MyNavigationOnRail(navController = navController)

                // Contenu principal de l'écran
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp))
                {
                    val columnCount = when {
                        maxWidth > 1200.dp -> 4
                        else -> 2
                    }
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(columnCount),
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(settingsItems.size) { index ->
                            val cardItems = settingsItems[index]
                            CardContent(
                                listRowContent = cardItems,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }else{
            Scaffold(
                bottomBar = {
                    MyBottomAppBar(navController = navController)
                }
            ) { paddingValues ->
                BoxWithConstraints(modifier = Modifier.padding(paddingValues)) {
                    val columnCount = when {
                        maxWidth > 1200.dp -> 4// pc/web
                        maxWidth > 600.dp -> 2//tablette
                        else -> 1//smartphone
                    }

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(columnCount),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(settingsItems.size) { index ->  // Utilisation de .size et index
                            val cardItems = settingsItems[index]
                            CardContent(
                                listRowContent = cardItems,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))


                }
            }
        }
    }

}