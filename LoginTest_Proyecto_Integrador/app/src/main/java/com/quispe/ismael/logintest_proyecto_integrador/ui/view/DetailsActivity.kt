package com.quispe.ismael.logintest_proyecto_integrador.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.quispe.ismael.logintest_proyecto_integrador.R

class DetailsActivity : AppCompatActivity() {
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val googleId = intent.getStringExtra("google_id")
        val googleFirstName = intent.getStringExtra("google_first_name")
        val googleLastName = intent.getStringExtra("google_last_name")
        val googleEmail = intent.getStringExtra("google_email")
        val googleProfilePicURL = intent.getStringExtra("google_profile_pic_url")
        val googleAccessToken = intent.getStringExtra("google_id_token")

        // Referencia al ImageView para la foto de perfil
        val profileImageView = findViewById<ImageView>(R.id.profile_image)

        // Carga la imagen usando Glide
        if (!googleProfilePicURL.isNullOrEmpty()) {
            Glide.with(this)
                .load(googleProfilePicURL) // URL de la imagen
                .placeholder(R.drawable.ic_profile) // Imagen de carga
                .error(R.drawable.ic_profile) // Imagen de error
                .circleCrop() // Recorta la imagen en forma de círculo
                .into(profileImageView) // Carga la imagen en el ImageView
        }


        val googleIdTextView = findViewById<TextView>(R.id.google_id_textview)
        googleIdTextView.text = googleId

        val googleFirstNameTextView = findViewById<TextView>(R.id.google_first_name_textview)
        googleFirstNameTextView.text = googleFirstName

        val googleLastNameTextView = findViewById<TextView>(R.id.google_last_name_textview)
        googleLastNameTextView.text = googleLastName

        val googleEmailTextView = findViewById<TextView>(R.id.google_email_textview)
        googleEmailTextView.text = googleEmail





        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)


        val googleSignOutButton = findViewById<Button>(R.id.google_signout_btn)
        googleSignOutButton.setOnClickListener {
            signOut()
        }

        val CrudButton = findViewById<Button>(R.id.crud_btn)
        CrudButton.setOnClickListener {
            val intent = Intent(this, ParkingListActivity::class.java)
            startActivity(intent)  // Start the activity
        }

        val GotoMainButton = findViewById<Button>(R.id.GotoMain)
        GotoMainButton.setOnClickListener {
            // Log para verificar el valor de googleProfilePicURL
            Log.d("DetailsActivity", "URL obtenida de OAuth: $googleProfilePicURL")

            // Asegúrate de pasar la URL correctamente
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("google_profile_pic_url", googleProfilePicURL)
            startActivity(intent)
        }


    }
    private fun signOut() {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(this) {

                Toast.makeText(this, "Te has deslogueado", Toast.LENGTH_SHORT).show()


                finish()
            }
    }




}

