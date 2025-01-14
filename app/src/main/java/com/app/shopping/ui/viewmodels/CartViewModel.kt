package com.app.shopping.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.shopping.data.local.entities.CartEntity
import com.app.shopping.data.local.entities.ProductEntity
import com.app.shopping.data.repository.CartRepository
import com.app.shopping.ui.views.UiState
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
                    println("Updated Cart Items Count: ${cartItems.size}")
                    _uiState.value = UiState.Success(cartItems)
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error<String>("Error in fetching cart details")
            }
        }
    }

    val cartItems: LiveData<List<CartEntity>> = repository.getCartItems()

    fun updateCartItem(cartItem: CartEntity) {
        viewModelScope.launch {
            repository.updateCartItem(cartItem)
        }
    }

    fun deleteCartItem(cartItem: CartEntity) {
        viewModelScope.launch {
            repository.deleteCartItem(cartItem)
            getCartData()
        }
    }
}