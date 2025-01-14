package com.app.shopping.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.shopping.data.local.entities.CartEntity
import com.app.shopping.data.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository
) : ViewModel() {
    val cartItems: LiveData<List<CartEntity>> = repository.getCartItems()

    fun addToCart(cartItem: CartEntity) {
        viewModelScope.launch {
            repository.addToCart(cartItem)
        }
    }

    fun updateCartItem(cartItem: CartEntity) {
        viewModelScope.launch {
            repository.updateCartItem(cartItem)
        }
    }

    fun deleteCartItem(cartItem: CartEntity) {
        viewModelScope.launch {
            repository.clearCart()
        }
    }
}