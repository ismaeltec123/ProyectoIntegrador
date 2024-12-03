package com.quispe.ismael.logintest_proyecto_integrador.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.quispe.ismael.logintest_proyecto_integrador.R

class TourFragment : Fragment() {

    companion object {
        fun newInstance(title: String, description: String, imageResId: Int) = TourFragment().apply {
            arguments = Bundle().apply {
                putString("title", title)
                putString("description", description)
                putInt("imageResId", imageResId)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tour, container, false)

        val title = arguments?.getString("title")
        val description = arguments?.getString("description")
        val imageResId = arguments?.getInt("imageResId")

        view.findViewById<TextView>(R.id.tvTitle).text = title
        view.findViewById<TextView>(R.id.tvDescription).text = description
        view.findViewById<ImageView>(R.id.ivTourImage).setImageResource(imageResId ?: 0)

        return view
    }
}
