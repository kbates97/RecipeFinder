package dev.kylecode.recipefinder.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import dev.kylecode.recipefinder.R
import dev.kylecode.recipefinder.model.Recipe
import kotlinx.android.synthetic.main.item_recipe_card.view.*
import org.w3c.dom.Text

class RecipeAdapter(
    private var recipes: MutableList<Recipe>,
    private var onRecipeClick: (recipe: Recipe) -> Unit,
    private var context: Context
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>(){

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recipeImage:ImageView = itemView.findViewById(R.id.imgRecipe)
        private val recipeName:TextView = itemView.findViewById(R.id.txtViewTitle)
        private val recipeTime:TextView = itemView.findViewById(R.id.txtViewRecipeContent)

        fun bind(recipe: Recipe){
            val imageUrl = recipe.image

            Glide.with(itemView)
                .load(imageUrl)
                .transform(CenterCrop())
                .into(recipeImage)

            recipeName.text = recipe.title
            if (recipe.readyInMinutes >= 60)
            {
                val hours = recipe.readyInMinutes / 60.0
                recipeTime.text = "${context.getString(R.string.time_to_make)} $hours ${context.getString(R.string.hours)}"
            }else{
                recipeTime.text = "${context.getString(R.string.time_to_make)} ${recipe.readyInMinutes} ${context.getString(R.string.minutes)}"
            }
            recipeTime.text = "${context.getString(R.string.time_to_make)} ${recipe.readyInMinutes} ${context.getString(R.string.minutes)}"
            itemView.setOnClickListener{
                onRecipeClick.invoke(recipe)
            }
        }
    }

    fun appendRecipes(recipes: List<Recipe>){
        this.recipes.addAll(recipes)
        notifyItemRangeInserted(this.recipes.size, recipes.size)
    }

    fun setRecipes(recipes: List<Recipe>){
        this.recipes = recipes as MutableList<Recipe>
        notifyItemRangeChanged(0, recipes.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_recipe_card, parent, false)
        return RecipeViewHolder(view)
    }

    override fun getItemCount(): Int = recipes.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

}