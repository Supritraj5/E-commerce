package com.app.shopping.data.repository

import androidx.lifecycle.LiveData
import com.app.shopping.data.local.dao.CartDAO
import com.app.shopping.data.local.entities.CartEntity
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val cartDao: CartDAO
) {

    fun getCartItems(): LiveData<List<CartEntity>> = cartDao.getCartItems() //get all the cart items

    suspend fun addToCart(cartItem: CartEntity) {
        cartDao.addToCart(cartItem) //add item to cart
    }

    suspend fun updateCartItem(cartItem: CartEntity) {
        cartDao.updateCartItem(cartItem) //update call
    }

    suspend fun deleteCartItem(cartItem: CartEntity) {
        cartDao.deleteCartItem(cartItem) //delete cart item
    }

    suspend fun clearCart() {
        cartDao.clearCart() //not required for now
    }
}