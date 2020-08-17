package dev.kylecode.recipefinder.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.Toast
import android.widget.ToggleButton
import androidx.constraintlayout.solver.widgets.WidgetContainer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dev.kylecode.recipefinder.R
import dev.kylecode.recipefinder.data.RecipeAdapter
import dev.kylecode.recipefinder.data.RecipeRepository
import dev.kylecode.recipefinder.model.Recipe
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbarMain)

        val user = FirebaseAuth.getInstance().currentUser

        title = "Hello, " + user?.displayName


        toggleButtonMain.addOnButtonCheckedListener { toggleButton, checkedId, isChecked ->
            when (checkedId){
                R.id.tglBtnFavorites -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentMain, FavoritesListFragment())
                        .commit()
                }
                R.id.tglBtnFoods -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentMain, FoodsListFragment())
                        .commit()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_appbar_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId){
        R.id.menuItemProfile -> {
            AuthUI.getInstance()
                .signOut(this)
            finish();
            startActivity(Intent(this, LoginActivity::class.java))
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}