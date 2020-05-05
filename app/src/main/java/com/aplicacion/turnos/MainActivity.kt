package com.aplicacion.turnos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private val authUser: FirebaseAuth by lazy { FirebaseAuth.getInstance()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goTo()
    }

    fun goTo(){
        if (authUser.currentUser != null){
            startActivity(Intent(this, Principal::class.java))
            finish()
        }else{
            startActivity(Intent(this, Login::class.java))
            finish()
        }
    }
}

//TODO Link tutorial para firebase: https://www.youtube.com/watch?v=b841-mekN28&list=PL6TCTYi3NaCtFH1HI5GXVKs6cBGDLa5aS&index=4&t=0s