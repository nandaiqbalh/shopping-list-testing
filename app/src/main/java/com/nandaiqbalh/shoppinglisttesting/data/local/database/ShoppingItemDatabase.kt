package com.nandaiqbalh.shoppinglisttesting.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nandaiqbalh.shoppinglisttesting.data.local.database.dao.ShoppingItemDao
import com.nandaiqbalh.shoppinglisttesting.data.local.database.entity.ShoppingItemEntity


@Database(
	entities = [ShoppingItemEntity::class],
	version = 1
)
abstract class ShoppingItemDatabase : RoomDatabase(){

	abstract fun shoppingItemDao() : ShoppingItemDao
}