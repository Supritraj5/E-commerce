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
    onDeleteCartData: (CartEntity) -> Unit,
    onIncrease: (CartEntity) -> Unit,
    onDecrease: (CartEntity) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(cartProducts) { product ->
            CartItem(data = product, onDeleteItem = {
                onDeleteCartData(product)
            }, onDecrease = {
                onDecrease(product)
            }, onIncrease = {
                onIncrease(product)
            })
        }
    }
}