package com.app.shopping.ui.views

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.app.shopping.data.local.entities.ProductEntity

@Composable
fun ProductItem(modifier: Modifier = Modifier, data: ProductEntity, onAddToCart: ()->Unit) {

    val context = LocalContext.current
    val imageRequest = ImageRequest.Builder(context)
        .data(data.image)
        .diskCachePolicy(CachePolicy.DISABLED)
        .memoryCachePolicy(CachePolicy.DISABLED)
        .build()

    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Blue
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(5.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            SubcomposeAsyncImage(
                model = imageRequest,
                loading = {
                    CircularProgressIndicator()
                },
                error = {
                    Text("Error in loading image");
                },
                contentDescription = "Product image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(100.dp)
                    .weight(0.2f)
            )
            Column(
                modifier = Modifier.weight(0.5f).padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.Start
            ) {
                Text(data.title)
                Text(data.price.toString())
            }

            Button(onClick = {onAddToCart()}, modifier = Modifier.weight(0.3f)) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = "Shopping icon"
                )
                Text("Add")
            }
        }
    }
}

@Preview
@Composable
private fun PreviewProductItem() {
    ProductItem(
        modifier = Modifier,
        data = ProductEntity(id = 9, title = "My name", image = "", price = 2.99),
        onAddToCart = {}
    )
}