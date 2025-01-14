package com.app.shopping.utils

import com.app.shopping.data.local.entities.CartEntity
import com.app.shopping.data.local.entities.ProductEntity
import com.app.shopping.data.remote.models.ProductResponse

//convert product response to product entity
fun ProductResponse.ProductResponseItem.toEntity(): ProductEntity {
    return ProductEntity(
        id = this.id, title = this.title, price = this.price, image = this.image
    )
}

//convert product entity to cart entity
fun ProductEntity.toCartEntity():CartEntity{
    return CartEntity(
        id = this.id,
        title = this.title,
        price = this.price,
        quantity = 1
    )
}