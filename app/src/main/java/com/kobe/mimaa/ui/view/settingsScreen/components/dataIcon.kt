package com.kobe.mimaa.ui.view.settingsScreen.components

import androidx.annotation.DrawableRes
import com.kobe.mimaa.R


//sealed class RowIcon {
//    data class Resource(val id: Int) : RowIcon()
//    data class Painter(val painter: Painter) : RowIcon()
//    data class Vector(val imageVector: ImageVector) : RowIcon()
//}

data class RowContentItem(
    val title: String,
    @DrawableRes val icon: Int,
    val onClick: () -> Unit
)



val myList1 = listOf(
    RowContentItem(
        title = "Apparence",
        icon = R.drawable.p_icn,
        onClick = { println("Notifications cliquées") }
    ),
    RowContentItem(
        title = "Apparence",
        icon = R.drawable.p_icn,
        onClick = { println("Notifications cliquées") }
    ),
    RowContentItem(
        title = "Apparence",
        icon = R.drawable.p_icn,
        onClick = { println("Notifications cliquées") }
    ),
    RowContentItem(
        title = "Apparence",
        icon = R.drawable.p_icn,
        onClick = { println("Notifications cliquées") }
    )
)
