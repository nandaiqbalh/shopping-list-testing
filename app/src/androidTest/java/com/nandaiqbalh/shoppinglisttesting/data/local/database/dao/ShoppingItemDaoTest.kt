package com.nandaiqbalh.shoppinglisttesting.data.local.database.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.nandaiqbalh.shoppinglisttesting.data.local.database.ShoppingItemDatabase
import com.nandaiqbalh.shoppinglisttesting.data.local.database.entity.ShoppingItemEntity
import com.nandaiqbalh.shoppinglisttesting.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@Suppress("DEPRECATION")
@RunWith(AndroidJUnit4::class)
@SmallTest
class ShoppingItemDaoTest {

	// tell JUnit to run all these function per item (one after another)
	@get:Rule
	var instantTaskExecutorRule = InstantTaskExecutorRule()

	private lateinit var database: ShoppingItemDatabase
	private lateinit var dao: ShoppingItemDao

	/**
	 * DOCUMENTATION
	 *
	 * use inMemoryDatabaseBuilder: because we want the database is only running on our RAM (not in our real memory)
	 * use allowMainThreadQueries: because we want the test is executed sequentially (berurutan)...
	 * ... this is so that the data does not collide with each other when tested
	 */

	@Before
	fun setup(){

		database = Room.inMemoryDatabaseBuilder(
			ApplicationProvider.getApplicationContext(),
			ShoppingItemDatabase::class.java
		).allowMainThreadQueries().build()

		dao = database.shoppingItemDao()
	}

	@After
	fun teardown(){

		// closing database after testing the database
		database.close()
	}

	@Test
	fun insertShoppingItem() = runBlockingTest {

		// insert test data
		val shoppingItemEntity = ShoppingItemEntity(1, "name", 2, 1F, "url")
		dao.insertShoppingItem(shoppingItemEntity)

		// get all data
		val allShoppingItems = dao.observeAllShoppingItems().getOrAwaitValue()

		// check is contain or not
		assertThat(allShoppingItems).contains(shoppingItemEntity)

	}


	@Test
	fun deleteShoppingItem() = runBlockingTest {
		// insert test data
		val shoppingItemEntity = ShoppingItemEntity(1, "name", 2, 1F, "url")
		dao.insertShoppingItem(shoppingItemEntity)

		// delete data
		dao.deleteShoppingItem(shoppingItemEntity)

		// get all data
		val allShoppingItems = dao.observeAllShoppingItems().getOrAwaitValue()

		// check is contain or not
		assertThat(allShoppingItems).doesNotContain(shoppingItemEntity)
	}

	@Test
	fun observeTotalPriceSum() = runBlockingTest {
		// insert test data
		val shoppingItemEntity1 = ShoppingItemEntity(1, "name", 2, 10F, "url")
		val shoppingItemEntity2 = ShoppingItemEntity(2, "name", 2, 100F, "url")
		dao.insertShoppingItem(shoppingItemEntity1)
		dao.insertShoppingItem(shoppingItemEntity2)

		// get total
		val totalPriceSum = dao.observeTotalPrice().getOrAwaitValue()

		// check is equal or not
		assertThat(totalPriceSum).isEqualTo(2 * 10F + 2 * 100F)

	}
}