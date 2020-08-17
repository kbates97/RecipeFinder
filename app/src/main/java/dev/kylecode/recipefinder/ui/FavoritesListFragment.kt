package dev.kylecode.recipefinder.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dev.kylecode.recipefinder.R
import dev.kylecode.recipefinder.data.RecipeAdapter
import dev.kylecode.recipefinder.model.Recipe


class FavoritesListFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_favorites_list, container, false)
        recipeResults = view.findViewById(R.id.recyclerRecipes)
        recipeLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recipeAdapter = RecipeAdapter(mutableListOf(), { recipe -> showRecipeDetail(recipe) }, context)

        recipeResults.layoutManager = recipeLayoutManager
        recipeResults.adapter = recipeAdapter

        val recipes: MutableList<Recipe> = mutableListOf()

        Firebase.firestore.collection("favorites").document(FirebaseAuth.getInstance().currentUser?.uid.toString()).collection("foods").get()
            .addOnSuccessListener { documents ->
                if (documents != null && !documents.isEmpty)
                    documents.forEach{ document ->
                        Firebase.firestore.collection("foods").document(document.id).get()
                            .addOnSuccessListener { doc ->
                                addRecipe(doc.data, recipes)
                            }
                            .addOnFailureListener { exception ->
                                Log.d("Favorites Q2", "get failed with ", exception)
                            }
                    }
            }
            .addOnCompleteListener {
                recipeAdapter.setRecipes(recipes)
                attachRecipesOnScrollListener()
            }
            .addOnFailureListener { exception ->
                Log.d("Favorites Q1", "get failed with ", exception)
            }


        return view
    }

    private fun addRecipe(map: MutableMap<String, Any>?, recipes: MutableList<Recipe>){
        val recipe = Recipe.from(map)
        recipes.add(recipe)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoritesListFragment().apply {
                arguments = Bundle().apply {
                }
            }
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