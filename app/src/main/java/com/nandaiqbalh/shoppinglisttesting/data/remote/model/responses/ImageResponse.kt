package com.nandaiqbalh.shoppinglisttesting.data.remote.model.responses

data class ImageResponse(
	val hits: List<ImageResult>,
	val total: Int,
	val totalHits: Int
)