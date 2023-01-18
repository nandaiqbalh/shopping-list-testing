package com.nandaiqbalh.shoppinglisttesting.di

import android.content.Context
import androidx.room.Room
import com.nandaiqbalh.shoppinglisttesting.data.DefaultShoppingRepository
import com.nandaiqbalh.shoppinglisttesting.data.local.database.ShoppingItemDatabase
import com.nandaiqbalh.shoppinglisttesting.data.local.database.dao.ShoppingItemDao
import com.nandaiqbalh.shoppinglisttesting.data.remote.service.PixabayAPI
import com.nandaiqbalh.shoppinglisttesting.data.repositories.ShoppingRepository
import com.nandaiqbalh.shoppinglisttesting.other.Constants.BASE_URL
import com.nandaiqbalh.shoppinglisttesting.other.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

	@Singleton
	@Provides
	fun provideShoppingItemDatabase(
		@ApplicationContext context: Context
	) = Room.databaseBuilder(context, ShoppingItemDatabase::class.java, DATABASE_NAME).build()

	@Singleton
	@Provides
	fun provideDefaultShoppingRepository(
		dao: ShoppingItemDao,
		api: PixabayAPI
	) = DefaultShoppingRepository(dao, api) as ShoppingRepository


	@Singleton
	@Provides
	fun provideShoppingDao(
		database: ShoppingItemDatabase
	) = database.shoppingItemDao()

	@Singleton
	@Provides
	fun providePixabayApi(): PixabayAPI {
		return Retrofit.Builder()
			.addConverterFactory(GsonConverterFactory.create())
			.baseUrl(BASE_URL)
			.build()
			.create(PixabayAPI::class.java)
	}
}