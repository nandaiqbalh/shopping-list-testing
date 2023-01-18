package com.nandaiqbalh.shoppinglisttesting.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_items")
data class ShoppingItemEntity(
	@PrimaryKey(autoGenerate = true)
	val id: Int? = null,
	@ColumnInfo(name = "name")
	var name: String,
	@ColumnInfo(name = "amount")
	var amount: Int,
	@ColumnInfo(name = "price")
	var price: Float,
	@ColumnInfo(name = "imageUrl")
	var imageUrl: String
)