package com.quispe.ismael.logintest_proyecto_integrador.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.quispe.ismael.logintest_proyecto_integrador.R
import com.quispe.ismael.logintest_proyecto_integrador.ui.adapter.TourAdapter
import com.quispe.ismael.logintest_proyecto_integrador.ui.adapter.TourPage
import com.quispe.ismael.logintest_proyecto_integrador.data.database.SharedPreferencesRepository

class TourActivity : AppCompatActivity() {
    private lateinit var sharedPreferencesRepository: SharedPreferencesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tour)

        // Inicializar SharedPreferencesRepository
        sharedPreferencesRepository = SharedPreferencesRepository()
        sharedPreferencesRepository.initSharedPreferences(this)

        val tourPages = listOf(
            TourPage("Accede al Carnet Virtual", "Para acceder haz clic en tu foto de perfil...", R.drawable.tour1),
            TourPage("Personaliza el Menú", "Reordena los iconos a tu gusto y accede rápidamente...", R.drawable.tour2)
        )

        val viewPager = findViewById<ViewPager2>(R.id.viewPagerTour)
        viewPager.adapter = TourAdapter(this, tourPages)

        val btnFinish = findViewById<Button>(R.id.btnFinishTour)
        btnFinish.setOnClickListener {
            sharedPreferencesRepository.setTourCompleted(true)

            // Redirigir al ParkingListActivity
            val intent = Intent(this, ParkingListActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
