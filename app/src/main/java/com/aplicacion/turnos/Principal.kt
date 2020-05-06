package com.aplicacion.turnos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.facebook.AccessToken
import com.facebook.login.LoginManager
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_principal.button_logout_google
import kotlinx.android.synthetic.main.activity_principal.progressBar

class Principal : AppCompatActivity() {

    private val authUser: FirebaseAuth by lazy { FirebaseAuth.getInstance()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        signOut()
    }

    fun signOut(){
        button_logout_google.setOnClickListener{
            progressBar.visibility = View.VISIBLE
            button_logout_google.isEnabled = false

            if (loggedWithGoogle()){
                AuthUI.getInstance().signOut(this).addOnSuccessListener {
                    startActivity(Intent(this, Login::class.java))
                    Toast.makeText(this, "Hasta pronto", Toast.LENGTH_LONG).show()
                    finish()
                }.addOnFailureListener{
                    Toast.makeText(this, "Ocurrio un error ${ it.message}", Toast.LENGTH_LONG).show()
                }
            }else{
                if (loggedWithFacebook()){
                    LoginManager.getInstance().logOut()
                    startActivity(Intent(this, Login::class.java))
                    Toast.makeText(this, "Hasta pronto", Toast.LENGTH_LONG).show()
                    finish()
                }
            }

            progressBar.visibility = View.GONE
            button_logout_google.isEnabled = true
        }
    }

    fun loggedWithGoogle() = authUser.currentUser != null

    fun loggedWithFacebook() = AccessToken.getCurrentAccessToken() != null
}
