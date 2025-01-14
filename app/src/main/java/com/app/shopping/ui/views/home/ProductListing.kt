package com.app.shopping.ui.views.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.shopping.data.local.entities.ProductEntity

@Composable
fun ProductListing(modifier: Modifier = Modifier, products: List<ProductEntity>, onAddProductToCart:(ProductEntity)->Unit) {

    LazyColumn(
        modifier = modifier
    ) {
        items(products){ product->
            ProductItem(data = product, onAddToCart = {
                onAddProductToCart(product)
            })
        }
    }

}