package com.app.shopping.ui.views.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.app.shopping.data.local.entities.CartEntity

@Composable
fun CartItem(modifier: Modifier = Modifier, data: CartEntity, onDeleteItem: () -> Unit) {
    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Blue
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.Start
            ) {
                Text(data.title)
                Text(data.price.toString())
            }

            Button(onClick = { onDeleteItem() }, modifier = Modifier.weight(0.3f)) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "delete cart item"
                )
                Text("Delete")
            }
        }
    }
}