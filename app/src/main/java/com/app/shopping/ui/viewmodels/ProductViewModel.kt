package com.app.shopping.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.shopping.data.local.entities.ProductEntity
import com.app.shopping.data.repository.CartRepository
import com.app.shopping.data.repository.ProductRepository
import com.app.shopping.ui.views.UiState
import com.app.shopping.utils.toCartEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState<List<ProductEntity>>>()
    val uiState: LiveData<UiState<List<ProductEntity>>> = _uiState

    init {
        fetchProductsFromApi()
    }

    fun fetchProductsFromApi() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val products = repository.fetchProductsFromRemote()
                repository.insertProducts(products)
                _uiState.value = UiState.Success(products)
            } catch (e: Exception) {
                _uiState.value = UiState.Error<String>("Failed to fetch products")
            }
        }
    }

    fun getProductsFromDb() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val products = repository.getAllProducts()
                _uiState.value = UiState.Success(products)
            } catch (e: Exception) {
                _uiState.value = UiState.Error<String>("Failed to fetch products from DB")
            }
        }
    }

    suspend fun addProductToCart(product: ProductEntity){
        cartRepository.addToCart(cartItem = product.toCartEntity())
    }
}