package com.kobe.mimaa.ui.view.profileScreen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kobe.mimaa.R
import com.kobe.mimaa.data.source.model.User
import com.kobe.mimaa.ui.view.components.rowIconText.RowContentItem

@Composable
fun ContenairProfileItem(
    currentUser: User? = null,
    modifier: Modifier = Modifier,
    elevation: Dp = 4.dp,
    contentPadding: PaddingValues = PaddingValues(vertical = 8.dp)
) {
    val user = currentUser?: return

    val profileItems = listOf(
        RowContentItem(
            title = user.username.toString(),
            icon = R.drawable.person_filled_icn,
            onClick = { /*Open bottomSheet for update userInformations*/ }
        ),
        RowContentItem(
            title = user.password,
            icon = R.drawable.lock_filled_icn,
            onClick = { /*Open bottomSheet for update userInformations*/ }
        ),
        RowContentItem(
            title = user.username.toString(),
            icon = R.drawable.person_filled_icn,
            onClick = { /*Open bottomSheet for update userInformations*/ }
        ),
        RowContentItem(
            title = user.username.toString(),
            icon = R.drawable.person_filled_icn,
            onClick = { /*Open bottomSheet for update userInformations*/ }
        ),
        RowContentItem(
            title = user.username.toString(),
            icon = R.drawable.person_filled_icn,
            onClick = { /*Open bottomSheet for update userInformations*/ }
        ),
    )


    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 8.dp) // Ajout de padding pour les bords
        ,
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation)
    ){
        Column(
            modifier = Modifier.padding(contentPadding),
            horizontalAlignment = Alignment.Start,
        ) {


        }
    }

}