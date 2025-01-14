package com.app.shopping.ui.views.cart

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.shopping.data.local.entities.CartEntity
import com.app.shopping.data.local.entities.ProductEntity

@Composable
fun CartListing(
    modifier: Modifier = Modifier,
    cartProducts: List<CartEntity>,
    onAddProductToCart: (ProductEntity) -> Unit,
    onDeleteCartData: (CartEntity) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(cartProducts) { product ->
            CartItem(data = product, onDeleteItem = {
                onDeleteCartData(product)
            })
        }
    }
}