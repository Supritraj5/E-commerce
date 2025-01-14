package com.app.shopping.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.shopping.data.local.dao.CartDAO
import com.app.shopping.data.local.dao.ProductDAO
import com.app.shopping.data.local.entities.CartEntity
import com.app.shopping.data.local.entities.ProductEntity

//Room DB
@Database(entities = [ProductEntity::class, CartEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun productDao(): ProductDAO
    abstract fun cartDao(): CartDAO
}