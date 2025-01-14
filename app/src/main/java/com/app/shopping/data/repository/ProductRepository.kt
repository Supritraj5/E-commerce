package com.app.shopping.data.repository

import androidx.lifecycle.LiveData
import com.app.shopping.data.local.dao.ProductDAO
import com.app.shopping.data.local.entities.ProductEntity
import com.app.shopping.data.remote.ApiService
import com.app.shopping.utils.toEntity
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productDao: ProductDAO,
    private val apiService: ApiService
) {
    fun getAllProducts(): List<ProductEntity> = productDao.getAllProducts()

    suspend fun insertProducts(products: List<ProductEntity>) {
        productDao.insertProducts(products)
    }

    suspend fun fetchProductsFromRemote(): List<ProductEntity> {
        val productResponse = apiService.fetchProducts()
        return productResponse.map { it.toEntity() }
    }
}