package dev.kylecode.recipefinder.data

import android.util.Log
import dev.kylecode.recipefinder.api.GetRecipeResponse
import dev.kylecode.recipefinder.api.RecipeAPI
import dev.kylecode.recipefinder.model.Recipe
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RecipeRepository {

    private val recipeApi: RecipeAPI

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        recipeApi = retrofit.create(RecipeAPI::class.java)
    }

    fun getRecipes(
        limit: Int = 5,
        tags: String,
        onSuccess: (recipes: List<Recipe>) -> Unit,
        onError: () -> Unit
    ){
        recipeApi.getRandomRecipes(limit, tags)
            .enqueue(object : Callback<GetRecipeResponse>{
                override fun onFailure(call: Call<GetRecipeResponse>?, t: Throwable?) {
                    Log.d("RecipeRepository", t?.message.toString())
                    onError.invoke()
                }

                override fun onResponse(
                    call: Call<GetRecipeResponse>?,
                    response: Response<GetRecipeResponse>?
                ) {
                    if (response!!.isSuccessful){
                        val responseBody = response?.body()

                        if (responseBody != null){
                            onSuccess.invoke(responseBody.recipes)
                        } else {
                            onError.invoke()
                            Log.d("RecipeRepository", "Response has no body.")
                        }
                    }
                }

            })
    }

}