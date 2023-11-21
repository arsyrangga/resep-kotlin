package com.rangga.resepin.ui.data.retrofit

import com.rangga.resepin.ui.data.response.RecipeDetail
import com.rangga.resepin.ui.data.response.RecipeList
import retrofit2.http.Query
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {


    @GET("recipes")
    fun getListRecipe(
    ): Call<RecipeList>

    @GET("recipe/{key}")
    fun getRecipeDetail( @Path("key") key: String?
    ): Call<RecipeDetail>


}