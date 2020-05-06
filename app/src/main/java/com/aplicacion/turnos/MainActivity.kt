package com.aplicacion.turnos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.AccessToken
import com.google.firebase.auth.FirebaseAuth
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

class MainActivity : AppCompatActivity() {

    private val authUser: FirebaseAuth by lazy { FirebaseAuth.getInstance()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        goTo()
    }

    fun goTo(){
        if (loggedWithGoogle()) {
            //||loggedWithFacebook()){
            startActivity(Intent(this, Principal::class.java))
            finish()
        }else{
            startActivity(Intent(this, Login::class.java))
            finish()
        }
    }

    fun loggedWithGoogle() = authUser.currentUser != null

    //fun loggedWithFacebook() = AccessToken.getCurrentAccessToken() != null
}

//GOOGLE
//TODO Link tutorial para firebase: https://www.youtube.com/watch?v=b841-mekN28&list=PL6TCTYi3NaCtFH1HI5GXVKs6cBGDLa5aS&index=4&t=0s

//FACEBOOK
//TODO link para configurar consola de desarrollador de facebook: https://www.youtube.com/watch?v=Jims30XAzjI
//TODO Aurtenticar con facebook: https://www.youtube.com/watch?v=1HgM_vc-rSc