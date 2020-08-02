package dev.kylecode.recipefinder.api


import com.google.gson.annotations.SerializedName
import dev.kylecode.recipefinder.model.Recipe

data class GetRecipeResponse(
    @SerializedName("recipes")
    val recipes: List<Recipe>
)