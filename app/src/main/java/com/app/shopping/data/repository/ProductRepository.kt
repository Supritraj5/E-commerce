package com.app.shopping.data.repository

import com.app.shopping.data.local.dao.ProductDAO
import com.app.shopping.data.local.entities.ProductEntity
import com.app.shopping.data.remote.ApiService
import com.app.shopping.utils.toEntity
import javax.inject.Inject

//product repo
class ProductRepository @Inject constructor(
    private val productDao: ProductDAO,
    private val apiService: ApiService
) {
    fun getAllProducts(): List<ProductEntity> = productDao.getAllProducts()

    suspend fun insertProducts(products: List<ProductEntity>) {
        productDao.insertProducts(products) //not required but thought
        // to implement a function that will get the data if user is offline
    }

    suspend fun fetchProductsFromRemote(): List<ProductEntity> {
        val productResponse = apiService.fetchProducts()
        return productResponse.map { it.toEntity() }
    }
}