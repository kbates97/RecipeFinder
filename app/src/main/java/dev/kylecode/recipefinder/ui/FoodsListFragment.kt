package dev.kylecode.recipefinder.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.kylecode.recipefinder.R
import dev.kylecode.recipefinder.data.RecipeAdapter
import dev.kylecode.recipefinder.data.RecipeRepository
import dev.kylecode.recipefinder.model.Recipe
import kotlinx.android.synthetic.main.fragment_foods_list.*

private lateinit var tags: String

class FoodsListFragment : Fragment() {
    private lateinit var recipeResults: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var recipeLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_foods_list, container, false)
        recipeResults = view.findViewById(R.id.recyclerRecipes)
        recipeLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recipeAdapter = RecipeAdapter(mutableListOf(), { recipe -> showRecipeDetail(recipe) }, context)

        recipeResults.layoutManager = recipeLayoutManager
        recipeResults.adapter = recipeAdapter

        val button = view?.findViewById<Button>(R.id.btnSubmit)
        button?.setOnClickListener {
            tags = editTextTags.text.toString()
            getRecipeResults(tags)
        }
        // Inflate the layout for this fragment
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FoodsListFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    private fun getRecipeResults(tags: String){
        RecipeRepository.getRecipes(200,
            tags,
            ::onSuccess,
            ::onError)
    }

    private fun onSuccess(recipes: List<Recipe>) {
        recipeAdapter.setRecipes(recipes)
        attachRecipesOnScrollListener()
    }

    private fun onError() {
        Toast.makeText(context, R.string.error_fetch_recipe, Toast.LENGTH_SHORT).show()
    }

    private fun showRecipeDetail(recipe: Recipe) {
        val intent = Intent(context, RecipeView::class.java).apply {
            putExtra("recipe", recipe)
        }
        startActivity(intent)
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