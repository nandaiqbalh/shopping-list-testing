package com.nandaiqbalh.shoppinglisttesting.data.remote.service

import com.nandaiqbalh.shoppinglisttesting.BuildConfig
import com.nandaiqbalh.shoppinglisttesting.data.remote.model.responses.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayAPI {


	@GET("/api/")
	suspend fun searchForImage(
		@Query("q") searchQuery: String,
		@Query("key") apiKey: String = BuildConfig.API_KEY
	): Response<ImageResponse>
}