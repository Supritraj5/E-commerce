package com.app.shopping.data.repository

import androidx.lifecycle.LiveData
import com.app.shopping.data.local.dao.CartDAO
import com.app.shopping.data.local.entities.CartEntity
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val cartDao: CartDAO
) {

    fun getCartItems(): LiveData<List<CartEntity>> = cartDao.getCartItems()

    suspend fun addToCart(cartItem: CartEntity) {
        cartDao.addToCart(cartItem)
        getCartItems().observeForever { cartItems ->
            println("Updated Cart Items Count: ${cartItems.size}")
        }
    }

    suspend fun updateCartItem(cartItem: CartEntity) {
        cartDao.updateCartItem(cartItem)
    }

    suspend fun deleteCartItem(cartItem: CartEntity) {
        cartDao.deleteCartItem(cartItem)
    }

    suspend fun clearCart() {
        cartDao.clearCart()
    }
}