package com.kobe.mimaa.ui.view.settingsScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kobe.mimaa.R

//sealed class RowIcon {
//    data class Resource(val id: Int) : RowIcon()
//    data class Painter(val painter: Painter) : RowIcon()
//    data class Vector(val imageVector: ImageVector) : RowIcon()
//}

data class RowContentItem(
    val title: String,
    val icon: Int,
    val onClick: () -> Unit
)

@Composable
fun RowContent(
    item: RowContentItem,
    titleColor: Color = Color.Unspecified,
    iconColor: Color = Color.Unspecified,
    trailingIcon: @Composable () -> Unit = {
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Aller à",
            tint = iconColor.takeOrElse { Color.Unspecified },
            modifier = Modifier.size(26.dp)
        )
    }
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { item.onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.title,
            tint = iconColor.takeOrElse { Color.Unspecified },
            modifier = Modifier.size(26.dp)
        )

        Text(
            text = item.title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
            ),
            color = titleColor.takeOrElse { Color.Unspecified },
        )

        Spacer(modifier = Modifier.weight(1f))
        trailingIcon()
    }
}

@Preview(showBackground = true)
@Composable
fun RowContentPreview() {
    MaterialTheme {
        Column {
            RowContent(
                item = RowContentItem(
                    title = "Apparence",
                    icon = R.drawable.p_icn,
                    onClick = {}
                )
            )
            RowContent(
                item = RowContentItem(
                    title = "Notifications",
                    icon = R.drawable.p_icn,
                    onClick = { println("Notifications cliquées") }
                )
            )
        }
    }
}