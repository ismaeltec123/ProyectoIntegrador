package com.quispe.ismael.logintest_proyecto_integrador.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.quispe.ismael.logintest_proyecto_integrador.R
import com.quispe.ismael.logintest_proyecto_integrador.data.model.VehiculoDTO

class VehiculoAdapter(
    private var vehiculos: List<VehiculoDTO>,
    private val onAction: (VehiculoDTO, String) -> Unit
) : RecyclerView.Adapter<VehiculoAdapter.VehiculoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiculoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vehiculo, parent, false)
        return VehiculoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VehiculoViewHolder, position: Int) {
        val vehiculo = vehiculos[position]
        holder.bind(vehiculo)
    }

    override fun getItemCount(): Int = vehiculos.size

    fun updateData(newVehiculos: List<VehiculoDTO>) {
        vehiculos = newVehiculos
        notifyDataSetChanged()
    }

    inner class VehiculoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtPlaca: TextView = itemView.findViewById(R.id.txtPlaca)
        private val txtModelo: TextView = itemView.findViewById(R.id.txtModelo)
        private val btnEdit: Button = itemView.findViewById(R.id.btnEdit)
        private val btnDelete: Button = itemView.findViewById(R.id.btnDelete)

        fun bind(vehiculo: VehiculoDTO) {
            txtPlaca.text = vehiculo.placa
            txtModelo.text = vehiculo.modelo

            btnEdit.setOnClickListener { onAction(vehiculo, "edit") }
            btnDelete.setOnClickListener { onAction(vehiculo, "delete") }
        }
    }
}
