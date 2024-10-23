package com.quispe.ismael.logintest_proyecto_integrador

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        // Initialize GoogleSignInClient
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


    }
    private fun signOut() {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(this) {
                // Show a confirmation message
                Toast.makeText(this, "Te has deslogueado", Toast.LENGTH_SHORT).show()

                // Close the DetailsActivity after signing out
                finish()
            }
    }




}

