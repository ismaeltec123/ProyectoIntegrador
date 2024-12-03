package com.quispe.ismael.logintest_proyecto_integrador.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.quispe.ismael.logintest_proyecto_integrador.ui.view.TourFragment

class TourAdapter(activity: AppCompatActivity, private val pages: List<TourPage>) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = pages.size

    override fun createFragment(position: Int): Fragment {
        return TourFragment.newInstance(pages[position].title, pages[position].description, pages[position].imageResId)
    }
}

data class TourPage(val title: String, val description: String, val imageResId: Int)
