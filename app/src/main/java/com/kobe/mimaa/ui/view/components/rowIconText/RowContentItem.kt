package com.kobe.mimaa.ui.view.components.rowIconText

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import com.kobe.mimaa.R


//sealed class RowIcon {
//    data class Resource(val id: Int) : RowIcon()
//    data class Painter(val painter: Painter) : RowIcon()
//    data class Vector(val imageVector: ImageVector) : RowIcon()
//}

data class RowContentItem(
    val title: String? = null,
    val value: String,
    @DrawableRes val icon: Int,
    val keyboardType: KeyboardType = KeyboardType.Text,
    val onClick: (RowContentItem) -> Unit,
    val onLongClick: ((RowContentItem) -> Unit)? = null
)



val myList1 = listOf(
    RowContentItem(
        value = "Apparence",
        icon = R.drawable.p_icn,
        onClick = { println("Notifications cliquées") }
    ),
    RowContentItem(
        value = "Apparence",
        icon = R.drawable.p_icn,
        onClick = { println("Notifications cliquées") }
    ),
    RowContentItem(
        value = "Apparence",
        icon = R.drawable.p_icn,
        onClick = { println("Notifications cliquées") }
    ),
    RowContentItem(
        value = "Apparence",
        icon = R.drawable.p_icn,
        onClick = { println("Notifications cliquées") }
    )
)
