package com.app.shopping.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartEntity (
    @PrimaryKey val id: Int,
    val title: String,
    val price: Double,
    val quantity: Int
)