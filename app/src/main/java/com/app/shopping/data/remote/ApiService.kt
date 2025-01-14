package com.app.shopping.data.remote

import com.app.shopping.data.remote.models.ProductResponse
import com.app.shopping.utils.Constants.ENDPOINT_PRODUCTS
import retrofit2.http.GET

interface ApiService {
    @GET(ENDPOINT_PRODUCTS)
    suspend fun fetchProducts(): ProductResponse
}