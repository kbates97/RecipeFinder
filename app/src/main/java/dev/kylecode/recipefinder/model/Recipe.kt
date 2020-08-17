package dev.kylecode.recipefinder.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Recipe(
    @SerializedName("aggregateLikes")
    val aggregateLikes: Int,
    @SerializedName("analyzedInstructions")
    val analyzedInstructions: List<AnalyzedInstruction>,
    @SerializedName("cheap")
    val cheap: Boolean,
    @SerializedName("creditsText")
    val creditsText: String,
    @SerializedName("cuisines")
    val cuisines: List<Any>,
    @SerializedName("dairyFree")
    val dairyFree: Boolean,
    @SerializedName("diets")
    val diets: List<String>,
    @SerializedName("dishTypes")
    val dishTypes: List<String>,
    @SerializedName("extendedIngredients")
    val extendedIngredients: List<ExtendedIngredient>,
    @SerializedName("gaps")
    val gaps: String,
    @SerializedName("glutenFree")
    val glutenFree: Boolean,
    @SerializedName("healthScore")
    val healthScore: Double,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("imageType")
    val imageType: String,
    @SerializedName("instructions")
    val instructions: String,
    @SerializedName("license")
    val license: String,
    @SerializedName("lowFodmap")
    val lowFodmap: Boolean,
    @SerializedName("occasions")
    val occasions: List<Any>,
    @SerializedName("originalId")
    val originalId: Any,
    @SerializedName("pricePerServing")
    val pricePerServing: Double,
    @SerializedName("readyInMinutes")
    val readyInMinutes: Int,
    @SerializedName("servings")
    val servings: Int,
    @SerializedName("sourceName")
    val sourceName: String,
    @SerializedName("sourceUrl")
    val sourceUrl: String,
    @SerializedName("spoonacularScore")
    val spoonacularScore: Double,
    @SerializedName("spoonacularSourceUrl")
    val spoonacularSourceUrl: String,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("sustainable")
    val sustainable: Boolean,
    @SerializedName("title")
    val title: String,
    @SerializedName("vegan")
    val vegan: Boolean,
    @SerializedName("vegetarian")
    val vegetarian: Boolean,
    @SerializedName("veryHealthy")
    val veryHealthy: Boolean,
    @SerializedName("veryPopular")
    val veryPopular: Boolean,
    @SerializedName("weightWatcherSmartPoints")
    val weightWatcherSmartPoints: Int,
    @SerializedName("winePairing")
    val winePairing: WinePairing
) : Serializable {
    companion object{
        fun from(map: MutableMap<String, Any>?) = object {
            val aggregateLikes: Int by map
            val analyzedInstructions: List<AnalyzedInstruction> by map
            val cheap: Boolean by map
            val creditsText: String by map
            val cuisines: List<Any> by map
            val dairyFree: Boolean by map
            val diets: List<String> by map
            val dishTypes: List<String> by map
            val extendedIngredients: List<ExtendedIngredient> by map
            val gaps: String by map
            val glutenFree: Boolean by map
            val healthScore: Double by map
            val id: Int by map
            val image: String by map
            val imageType: String by map
            val instructions: String by map
            val license: String by map
            val lowFodmap: Boolean by map
            val occasions: List<Any> by map
            val originalId: Any by map
            val pricePerServing: Double by map
            val readyInMinutes: Int by map
            val servings: Int by map
            val sourceName: String by map
            val sourceUrl: String by map
            val spoonacularScore: Double by map
            val spoonacularSourceUrl: String by map
            val summary: String by map
            val sustainable: Boolean by map
            val title: String by map
            val vegan: Boolean by map
            val vegetarian: Boolean by map
            val veryHealthy: Boolean by map
            val veryPopular: Boolean by map
            val weightWatcherSmartPoints: Int by map
            val winePairing: WinePairing by map

            val data = Recipe(aggregateLikes, analyzedInstructions, cheap, creditsText, cuisines, dairyFree, diets, dishTypes, extendedIngredients, gaps, glutenFree, healthScore, id, image, imageType,
            instructions, license, lowFodmap, occasions, originalId, pricePerServing, readyInMinutes, servings, sourceName, sourceUrl, spoonacularScore, spoonacularSourceUrl, summary, sustainable, title,
            vegan, vegetarian, veryHealthy, veryPopular, weightWatcherSmartPoints, winePairing)
        }.data
    }
}