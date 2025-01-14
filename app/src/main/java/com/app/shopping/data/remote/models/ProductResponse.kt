package com.app.shopping.data.remote.models


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

class ProductResponse : ArrayList<ProductResponse.ProductResponseItem>(){
    @Parcelize
    data class ProductResponseItem(
        @SerializedName("category")
        val category: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("image")
        val image: String,
        @SerializedName("price")
        val price: Double,
        @SerializedName("rating")
        val rating: Rating,
        @SerializedName("title")
        val title: String
    ) : Parcelable {
        @Parcelize
        data class Rating(
            @SerializedName("count")
            val count: Int,
            @SerializedName("rate")
            val rate: Double
        ) : Parcelable
    }
}