package com.app.shopping.ui.views.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.app.shopping.R
import com.app.shopping.data.local.entities.CartEntity

@Composable
fun CartItem(
    modifier: Modifier = Modifier,
    data: CartEntity,
    onDeleteItem: () -> Unit,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    val context = LocalContext.current
    var quantity by remember {
        mutableIntStateOf(data.quantity)
    }

    SideEffect {
        quantity = data.quantity
    }

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
                    .weight(0.4f)
                    .padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.Start
            ) {
                Text(data.title)
                Text(data.price.toString())
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(
                    onClick = {
                        onDecrease() // Prevent decreasing below 1
                    },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_remove),
                        contentDescription = "Decrease Quantity",
                        tint = if (quantity > 1) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.5f
                        )
                    )
                }

                Text(
                    text = "$quantity",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                IconButton(
                    onClick = { onIncrease() }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Increase Quantity",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
            Button(
                onClick = { onDeleteItem() },
                modifier = Modifier
                    .padding(start = 10.dp)
                    .weight(0.2f)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "delete cart item"
                )
            }
        }
    }
}