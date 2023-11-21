package com.rangga.resepin.ui.data.response

import com.google.gson.annotations.SerializedName

data class RecipeDetail(

	@field:SerializedName("method")
	val method: String,

	@field:SerializedName("results")
	val results: Results,

	@field:SerializedName("status")
	val status: Boolean
)

data class Results(

	@field:SerializedName("difficulty")
	val difficulty: String,

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("times")
	val times: String,

	@field:SerializedName("ingredient")
	val ingredient: List<String>,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("step")
	val step: List<String>,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("needItem")
	val needItem: List<NeedItemItem>
)

data class NeedItemItem(

	@field:SerializedName("img")
	val img: String,

	@field:SerializedName("item_name")
	val itemName: String
)
