package com.aplicacion.turnos

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.button_login_google
import java.util.Arrays

class Login : AppCompatActivity() {

    companion object{
        private const val RC_SIGN_IN = 423
    }
    //15YMvmKZ1TiV+jC8bhGSAmHC9VI=

    //facebook
    //private lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        googleLogin()
        //facebookLogin()
    }

    fun googleLogin(){
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build())

        button_login_google.setOnClickListener{
            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .setIsSmartLockEnabled(false)
                    .build(),
                RC_SIGN_IN)
        }
    }

    /*fun facebookLogin(){
        callbackManager = CallbackManager.Factory.create();
        button_login_facebook.setReadPermissions("alejandropagani6@hotmail.com")

        button_login_facebook.setOnClickListener{
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        }

        // Callback registration
        button_login_facebook.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) { // App code
                //para cuando se tenga el servidor, se envia este accestoken al servidor con los datos del usuario
                goMainScreen()
            }

            override fun onCancel() { // App code
                Toast.makeText(applicationContext,"Ocurrio un error en facebook", Toast.LENGTH_LONG).show()
            }

            override fun onError(exception: FacebookException) { // App code
                Toast.makeText(applicationContext,"Ocurrio un error en facebook", Toast.LENGTH_LONG).show()
            }
        })
    }*/

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //GOOGLE
        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                Toast.makeText(this,"Bienvenido ${user?.displayName}", Toast.LENGTH_LONG).show()
                goMainScreen()
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
                Toast.makeText(this,"Ocurrio un error ${response?.error?.errorCode}", Toast.LENGTH_LONG).show()
            }
        }

        //FACEBOOK
       //callbackManager.onActivityResult(requestCode, resultCode, data)

    }

    fun goMainScreen(){
        startActivity(Intent(this, Principal::class.java))
        finish()
    }
}
