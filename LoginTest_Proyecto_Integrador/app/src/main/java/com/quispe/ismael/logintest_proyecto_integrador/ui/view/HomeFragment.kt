package com.quispe.ismael.logintest_proyecto_integrador.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.quispe.ismael.logintest_proyecto_integrador.R
import com.quispe.ismael.logintest_proyecto_integrador.ui.adapter.HorarioAdapter

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val headerTitle = view.findViewById<TextView>(R.id.headerTitle)

        // Obtener la URL de la imagen desde los argumentos
        val googleProfilePicURL = arguments?.getString("google_profile_pic_url")
        Log.d("HomeFragment", "URL de la imagen recibida: $googleProfilePicURL")

        // Configurar el ImageView en el header
        val profileImage = view.findViewById<ImageView>(R.id.profileImage)

        if (!googleProfilePicURL.isNullOrEmpty()) {
            Glide.with(this)
                .load(googleProfilePicURL)
                .placeholder(R.drawable.ic_profile_placeholder)
                .error(R.drawable.ic_profile_placeholder)
                .circleCrop()
                .into(profileImage)
        } else {
            profileImage.setImageResource(R.drawable.ic_profile_placeholder)
        }


        // Configuración dinámica del título del header
        headerTitle.text = "¡Hola, Usuario!" // Cambia según tu lógica

        // Configurar el RecyclerView con datos de ejemplo
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewHome)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Datos de ejemplo
        val items = listOf(
            "Clase 1: 08:00 AM - 10:00 AM",
            "Clase 2: 10:30 AM - 12:30 PM",
            "Clase 3: 01:00 PM - 03:00 PM"
        )

        // Adaptador para el RecyclerView
        val adapter = HorarioAdapter(items)
        recyclerView.adapter = adapter

        return view
    }
}
