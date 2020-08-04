package dev.kylecode.recipefinder.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import dev.kylecode.recipefinder.R
import dev.kylecode.recipefinder.model.Recipe
import kotlinx.android.synthetic.main.activity_recipe_view.*

class RecipeView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_view)

        val recipe = intent.getSerializableExtra("recipe") as Recipe

        Glide.with(this)
            .load(recipe.image)
            .centerCrop()
            .into(imgRecipe)

        txtViewTitle.text = recipe.title

        if (recipe.readyInMinutes > 60)
            txtViewTime.text = "${this.getString(R.string.time_to_make)} ${recipe.readyInMinutes/60} ${this.getString(R.string.hours_and)} ${recipe.readyInMinutes%60} ${this.getString(R.string.minutes)}"
        else
            txtViewTime.text = "${this.getString(R.string.time_to_make)} ${recipe.readyInMinutes} ${this.getString(R.string.minutes)}"

        var ingredients = ""
        for (ingredient in recipe.extendedIngredients){
            ingredients = ingredients.plus("• ${ingredient.original}\n")
        }
        txtViewIngredientsList.text = ingredients

        txtViewStepsList.text = HtmlCompat.fromHtml(recipe.instructions, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}