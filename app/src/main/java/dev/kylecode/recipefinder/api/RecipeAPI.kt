package dev.kylecode.recipefinder.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeAPI {

    @GET("recipes/random/")
    fun getRandomRecipes(
        @Query("number") limit : Int = 5,
        @Query("tags") tags : String,
        @Query("apiKey") apiKey : String = "f50eaf6f19144d4b9b274018c28c2ee3"
    ): Call<GetRecipeResponse>
}