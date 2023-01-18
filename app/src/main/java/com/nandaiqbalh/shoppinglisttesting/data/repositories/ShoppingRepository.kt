package com.nandaiqbalh.shoppinglisttesting.data.repositories

import androidx.lifecycle.LiveData
import com.nandaiqbalh.shoppinglisttesting.data.local.database.entity.ShoppingItemEntity
import com.nandaiqbalh.shoppinglisttesting.data.remote.model.responses.ImageResponse
import com.nandaiqbalh.shoppinglisttesting.other.Resource

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItemEntity)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItemEntity)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItemEntity>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}