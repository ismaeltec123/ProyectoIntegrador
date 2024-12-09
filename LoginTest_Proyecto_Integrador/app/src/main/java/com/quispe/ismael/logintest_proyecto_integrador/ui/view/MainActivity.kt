package com.quispe.ismael.logintest_proyecto_integrador.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.quispe.ismael.logintest_proyecto_integrador.R
import com.quispe.ismael.logintest_proyecto_integrador.data.database.SharedPreferencesRepository
import com.quispe.ismael.logintest_proyecto_integrador.data.model.OAuthRequest
import com.quispe.ismael.logintest_proyecto_integrador.data.model.Usuario
import com.quispe.ismael.logintest_proyecto_integrador.data.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("186040582626-s62hbke94jnhnt84gm4e6lhs5tjshtdd.apps.googleusercontent.com")
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val googleLoginButton = findViewById<Button>(R.id.google_login_btn)
        googleLoginButton.setOnClickListener {
            signIn()
        }

        val googleSignOutButton = findViewById<Button>(R.id.google_signout_btn)
        googleSignOutButton.setOnClickListener {
            signOut()
            Toast.makeText(this, "Te has deslogueado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun signOut() {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(this) {
                Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val googleEmail = account?.email ?: ""
            val googleId = account?.id ?: ""
            val googleFirstName = account?.givenName ?: ""
            val googleLastName = account?.familyName ?: ""
            val googleProfilePicURL = account?.photoUrl.toString()

            // Registrar usuario en el backend
            registerOAuthInBackend(googleEmail, "Google", googleId, googleFirstName, googleLastName, googleProfilePicURL)

        } catch (e: ApiException) {
            Log.e("OAuth Error", "Código de error: ${e.statusCode}")
            Toast.makeText(this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
        }
    }

    private fun registerOAuthInBackend(
        email: String,
        oauthProvider: String,
        oauthId: String,
        firstName: String,
        lastName: String,
        profilePicURL: String
    ) {
        val apiService = RetrofitClient.apiService
        val fullName = "$firstName $lastName"
        val oAuthRequest = OAuthRequest(email, oauthProvider, oauthId,fullName)

        apiService.registerOAuth(oAuthRequest).enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.isSuccessful) {
                    val usuario = response.body()
                    if (usuario != null) {
                        Log.i("Registro Exitoso", "Usuario registrado: $usuario")

                        // Guardar el ID del usuario en SharedPreferences
                        val sharedPreferencesRepository = SharedPreferencesRepository(this@MainActivity)
                        sharedPreferencesRepository.guardarIdUsuarioEnPreferencias(usuario.id)
                        Log.d("MainActivity", "ID de usuario guardado en SharedPreferences: ${usuario.id}")



                        navigateToDetailsActivity(
                            usuario,
                            firstName,
                            lastName,
                            profilePicURL
                        )
                    } else {
                        Log.e("Registro Error", "Respuesta vacía del servidor")

                        Toast.makeText(this@MainActivity, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e("Registro Error", "Código HTTP: ${response.code()}")
                    Toast.makeText(this@MainActivity, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                Log.e("Registro Error", "Fallo en la conexión: ${t.message}")
                Toast.makeText(this@MainActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun navigateToDetailsActivity(
        usuario: Usuario,
        firstName: String,
        lastName: String,
        profilePicURL: String
    ) {
        val myIntent = Intent(this, DetailsActivity::class.java)
        myIntent.putExtra("google_id", usuario.id.toString())
        myIntent.putExtra("google_first_name", firstName)
        myIntent.putExtra("google_last_name", lastName)
        myIntent.putExtra("google_email", usuario.dni) // Adaptación
        myIntent.putExtra("google_profile_pic_url", profilePicURL)
        startActivity(myIntent)
        finish()
    }

    companion object {
        const val RC_SIGN_IN = 9001
    }
}
