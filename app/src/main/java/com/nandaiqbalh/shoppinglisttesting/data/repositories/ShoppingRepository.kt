package com.nandaiqbalh.shoppinglisttesting.data.repositories

import androidx.lifecycle.LiveData
import com.nandaiqbalh.shoppinglisttesting.data.local.database.dao.ShoppingItemDao
import com.nandaiqbalh.shoppinglisttesting.data.local.database.entity.ShoppingItemEntity
import com.nandaiqbalh.shoppinglisttesting.data.remote.model.responses.ImageResponse
import com.nandaiqbalh.shoppinglisttesting.data.remote.service.PixabayAPI
import com.nandaiqbalh.shoppinglisttesting.other.Resource
import javax.inject.Inject

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItemEntity)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItemEntity)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItemEntity>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}

class ShoppingRepositoryImpl @Inject constructor(
    private val shoppingDao: ShoppingItemDao,
    private val pixabayAPI: PixabayAPI
) : ShoppingRepository {

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItemEntity) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItemEntity) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItemEntity>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabayAPI.searchForImage(imageQuery)
            if(response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured", null)
            } else {
                Resource.error("An unknown error occured", null)
            }
        } catch(e: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }
}