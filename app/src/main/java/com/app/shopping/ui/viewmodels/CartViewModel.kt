package com.app.shopping.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.shopping.data.local.entities.CartEntity
import com.app.shopping.data.repository.CartRepository
import com.app.shopping.ui.views.UiState
import com.app.shopping.ui.views.cart.CartFunction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState<List<CartEntity>>>()
    val uiState: LiveData<UiState<List<CartEntity>>> = _uiState

    init {
        getCartData()
    }

    private fun getCartData() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                repository.getCartItems().observeForever { cartItems ->
                    _uiState.value = UiState.Success(cartItems)
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error<String>("Error in fetching cart details")
            }
        }
    }


    fun updateCartItem(cartItem: CartEntity, cartFunction: CartFunction) {
        viewModelScope.launch {
            if (cartFunction == CartFunction.DECREASE) {
                if (cartItem.quantity == 1) {
                    repository.deleteCartItem(cartItem)
                } else {
                    val updatedItem = cartItem.copy(quantity = cartItem.quantity - 1)
                    repository.updateCartItem(updatedItem)
                }
            } else {
                val updatedItem = cartItem.copy(quantity = cartItem.quantity + 1)
                repository.updateCartItem(updatedItem)
            }
            getCartData()
        }
    }

    fun deleteCartItem(cartItem: CartEntity) {
        viewModelScope.launch {
            repository.deleteCartItem(cartItem)
            getCartData()
        }
    }
}