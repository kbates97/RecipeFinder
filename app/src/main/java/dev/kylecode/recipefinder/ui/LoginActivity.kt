package dev.kylecode.recipefinder.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.FirebaseAuth
import dev.kylecode.recipefinder.R
import kotlinx.android.synthetic.main.activity_login.*

const val RC_SIGN_IN = 1

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnSignIn.setOnClickListener {
            createSignInIntent()
        }
    }

    private fun createSignInIntent(){
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build())

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setIsSmartLockEnabled(true)
                .setLogo(R.drawable.ic_baseline_restaurant_24)
                .build(),
            RC_SIGN_IN
        )
    }

    private fun signOut() {
        AuthUI.getInstance()
            .signOut(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN){
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                val intent = Intent(this, MainActivity::class.java).putExtra("user", user)
                startActivity(intent)
                finish()
            }
            else{
                Log.d("Login", response?.error?.toString())
            }
        }
    }
}