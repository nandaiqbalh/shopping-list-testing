package com.nandaiqbalh.shoppinglisttesting.data.local.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nandaiqbalh.shoppinglisttesting.data.local.database.entity.ShoppingItemEntity

@Dao
interface ShoppingItemDao {

	@Query("SELECT * FROM SHOPPING_ITEMS")
	fun observeAllShoppingItems(): LiveData<List<ShoppingItemEntity>>

	@Query("SELECT SUM(price * amount) FROM SHOPPING_ITEMS")
	fun observeTotalPrice(): LiveData<Float>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertShoppingItem(shoppingItemEntity: ShoppingItemEntity)

	@Delete
	suspend fun deleteShoppingItem(shoppingItemEntity: ShoppingItemEntity)



}