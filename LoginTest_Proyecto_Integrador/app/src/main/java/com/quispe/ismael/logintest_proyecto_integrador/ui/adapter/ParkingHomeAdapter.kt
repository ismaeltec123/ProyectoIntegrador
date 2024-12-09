package com.quispe.ismael.logintest_proyecto_integrador.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.quispe.ismael.logintest_proyecto_integrador.R
import com.quispe.ismael.logintest_proyecto_integrador.data.model.EstacionamientoDTO

class ParkingHomeAdapter(
    private var estacionamientoList: List<EstacionamientoDTO>,
    private val onEstacionamientoClick: (EstacionamientoDTO) -> Unit
) : RecyclerView.Adapter<ParkingHomeAdapter.ParkingHomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingHomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_parking_home, parent, false)
        return ParkingHomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParkingHomeViewHolder, position: Int) {
        val estacionamiento = estacionamientoList[position]
        holder.bind(estacionamiento)
    }

    override fun getItemCount(): Int = estacionamientoList.size

    fun updateData(newEstacionamientoList: List<EstacionamientoDTO>) {
        estacionamientoList = newEstacionamientoList
        notifyDataSetChanged()
    }

    inner class ParkingHomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreTextView: TextView = itemView.findViewById(R.id.lotNameTextView)
        private val capacidadTextView: TextView = itemView.findViewById(R.id.capacityTextView)
        private val tipoTextView: TextView = itemView.findViewById(R.id.lastUpdatedTextView)

        fun bind(estacionamiento: EstacionamientoDTO) {
            nombreTextView.text = estacionamiento.nombre
            capacidadTextView.text = "${estacionamiento.capacidadDisponible} vehículos disponibles"
            tipoTextView.text = estacionamiento.tipo.capitalize()
            itemView.setOnClickListener {
                onEstacionamientoClick(estacionamiento)
            }
        }
    }
}
