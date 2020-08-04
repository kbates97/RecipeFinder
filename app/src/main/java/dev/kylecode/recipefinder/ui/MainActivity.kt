package dev.kylecode.recipefinder.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.kylecode.recipefinder.R
import dev.kylecode.recipefinder.data.RecipeAdapter
import dev.kylecode.recipefinder.data.RecipeRepository
import dev.kylecode.recipefinder.model.Recipe
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var recipeResults: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var recipeLayoutManager: LinearLayoutManager
    private lateinit var tags: String
    private var currentRecipes: MutableList<Recipe> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recipeResults = findViewById(R.id.recyclerRecipes)
        recipeLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recipeAdapter = RecipeAdapter(currentRecipes, {recipe -> showRecipeDetail(recipe) }, this)

        recipeResults.layoutManager = recipeLayoutManager
        recipeResults.adapter = recipeAdapter

        btnSubmit.setOnClickListener {
            tags = editTextTags.text.toString()
            getRecipeResults(tags)
        }
    }

    private fun showRecipeDetail(recipe: Recipe) {
        val intent = Intent(this, RecipeView::class.java).apply {
            putExtra("recipe", recipe)
        }
        startActivity(intent)
    }

    private fun getRecipeResults(tags: String){
        RecipeRepository.getRecipes(20,
            tags,
            ::onSuccess,
            ::onError)
    }

    private fun onSuccess(recipes: List<Recipe>) {
        currentRecipes = recipes as MutableList<Recipe>
        recipeAdapter.appendRecipes(recipes)
        attachRecipesOnScrollListener()
    }

    private fun onError() {
        Toast.makeText(this, R.string.error_fetch_recipe, Toast.LENGTH_SHORT).show()
    }

    private fun attachRecipesOnScrollListener() {
        recipeResults.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalItemCount = recipeLayoutManager.itemCount

                val visibleItemCount = recipeLayoutManager.findLastVisibleItemPosition() - recipeLayoutManager.findFirstVisibleItemPosition()

                val firstVisibleItem = recipeLayoutManager.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount/2){
                    recipeResults.removeOnScrollListener(this)
                }
            }
        })
    }
}