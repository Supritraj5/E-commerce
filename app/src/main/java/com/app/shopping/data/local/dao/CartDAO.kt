package com.app.shopping.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.app.shopping.data.local.entities.CartEntity

@Dao
interface CartDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(cartItem: CartEntity)

    @Query("SELECT * FROM cart")
    fun getCartItems(): LiveData<List<CartEntity>>

    @Update
    suspend fun updateCartItem(cartItem: CartEntity)

    @Delete
    suspend fun deleteCartItem(cartItem: CartEntity)

    @Query("DELETE FROM cart")
    suspend fun clearCart()
}