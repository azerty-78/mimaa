package com.kobe.mimaa.ui.view.settingsScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kobe.mimaa.R
import com.kobe.mimaa.data.source.model.User
import com.kobe.mimaa.ui.view.components.myBottomAppBar.MyBottomAppBar
import com.kobe.mimaa.ui.view.components.myBottomAppBar.MyNavigationOnRail
import com.kobe.mimaa.ui.view.components.myTopAppBar.MyTopBar
import com.kobe.mimaa.ui.view.settingsScreen.components.ContenairSettingsItems
import com.kobe.mimaa.ui.view.components.rowIconText.RowContentItem

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun SettingsScreen(
    navController: NavController,
    currentUser: User? = null,
    onLogout: () -> Unit
) {
    val user = currentUser?: return
    val settingsItems = listOf(
        listOf(
            RowContentItem(title = null, value = "Mes informations", icon = R.drawable.p_icn, onClick =  {  }),
            RowContentItem(title = null, value = "Mes informations", icon = R.drawable.p_icn, onClick =  {  }),
            RowContentItem(title = null, value = "Mes informations", icon = R.drawable.p_icn, onClick =  {  }),
            RowContentItem(title = null, value = "Mes informations", icon = R.drawable.p_icn, onClick =  {  }),
        ),

        listOf(
            RowContentItem(title = null, value = "Mes informations", icon = R.drawable.p_icn, onClick =  {  }),
            RowContentItem(title = null, value = "Mes informations", icon = R.drawable.p_icn, onClick =  {  }),
            RowContentItem(title = null, value = "Mes informations", icon = R.drawable.p_icn, onClick =  {  }),
            RowContentItem(title = null, value = "Mes informations", icon = R.drawable.p_icn, onClick =  {  }),
        ),

        listOf(
            RowContentItem(title = null, value = "Thème", icon = R.drawable.p_icn, onClick =  {  }),
            RowContentItem(title = null, value = "Changer de mot de passe", icon = R.drawable.p_icn, onClick =  {  }),
            RowContentItem(title = null, value = "Notifications", icon = R.drawable.p_icn, onClick =  {  }),
            RowContentItem(title = null, value = "Langue", icon = R.drawable.p_icn, onClick =  {  }),
        ),

        listOf(
            RowContentItem(title = null, value = "Inviter un proche", icon = R.drawable.p_icn, onClick =  {  }),
            RowContentItem(title = null, value = "Politique de confidentialité", icon = R.drawable.p_icn, onClick =  {  }),
            RowContentItem(title = null, value = "Termes et conditions", icon = R.drawable.p_icn, onClick =  {  }),
            RowContentItem(title = null, value = "Déconnexion", icon = R.drawable.p_icn, onClick =  { onLogout() }),
        )
    )


    BoxWithConstraints(modifier = Modifier.fillMaxSize()){
        if(maxWidth > 600.dp){
            Row(modifier = Modifier.fillMaxSize()){
                MyNavigationOnRail(navController = navController)

                // Contenu principal de l'écran
                BoxWithConstraints(modifier = Modifier.fillMaxSize())
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
                            ContenairSettingsItems(
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
                }
            ) { paddingValues ->
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    modifier = Modifier.fillMaxSize().padding(paddingValues),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(settingsItems.size) { index ->  // Utilisation de .size et index
                        val cardItems = settingsItems[index]
                        ContenairSettingsItems(
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