package com.aplicacion.turnos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.activity_principal.button_logout_google
import kotlinx.android.synthetic.main.activity_principal.progressBar

class Principal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        signOut()
    }

    fun signOut(){
        button_logout_google.setOnClickListener{
            progressBar.visibility = View.VISIBLE
            button_logout_google.isEnabled = false
            AuthUI.getInstance().signOut(this).addOnSuccessListener {
                startActivity(Intent(this, Login::class.java))
                Toast.makeText(this, "Hasta pronto", Toast.LENGTH_LONG).show()
                finish()
            }.addOnFailureListener{
                progressBar.visibility = View.GONE
                button_logout_google.isEnabled = true
                Toast.makeText(this, "Ocurrio un error ${ it.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
