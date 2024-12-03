package com.quispe.ismael.logintest_proyecto_integrador.ui.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.quispe.ismael.logintest_proyecto_integrador.R
import com.quispe.ismael.logintest_proyecto_integrador.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar ViewBinding
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            // Inicializar NavHostFragment y NavController
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            navController = navHostFragment.navController

            // Configurar BottomNavigationView con NavController
            binding.navView.setupWithNavController(navController)
            Log.d("HomeActivity", "NavController configurado correctamente.")

            // Obtener la URL desde el Intent
            val googleProfilePicURL = intent.getStringExtra("google_profile_pic_url")
            Log.d("HomeActivity", "URL pasada: $googleProfilePicURL")

            // Configurar el gráfico de navegación y agregar el argumento
            val navGraph = navController.navInflater.inflate(R.navigation.home_navigation)
            val homeDestination = navGraph.findNode(R.id.nav_home)

            homeDestination?.addArgument(
                "google_profile_pic_url",
                androidx.navigation.NavArgument.Builder().setDefaultValue(googleProfilePicURL).build()
            )

            // Establecer el gráfico de navegación
            navController.graph = navGraph
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("HomeActivity", "Error al configurar NavController: ${e.message}")
        }
    }
}
