package com.quispe.ismael.logintest_proyecto_integrador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ParkingAdapter(
    private var parkingList: List<EstacionamientoAuto01>,
    private val onDelete: (EstacionamientoAuto01) -> Unit
) : RecyclerView.Adapter<ParkingAdapter.ParkingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_parking, parent, false)
        return ParkingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParkingViewHolder, position: Int) {
        val parkingSlot = parkingList[position]
        holder.bind(parkingSlot, onDelete)
    }

    override fun getItemCount() = parkingList.size

    fun updateData(newParkingList: List<EstacionamientoAuto01>) {
        parkingList = newParkingList
        notifyDataSetChanged()
    }

    class ParkingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val idSlotTextView: TextView = itemView.findViewById(R.id.idSlot)
        private val placaTextView: TextView = itemView.findViewById(R.id.placa)
        private val ubicacionTextView: TextView = itemView.findViewById(R.id.ubicacion)
        private val deleteButton: Button = itemView.findViewById(R.id.buttonDelete)

        fun bind(parkingSlot: EstacionamientoAuto01, onDelete: (EstacionamientoAuto01) -> Unit) {
            idSlotTextView.text = parkingSlot.idslot
            placaTextView.text = parkingSlot.placa

            // Check if Ubicacion is null before accessing its fields
            if (parkingSlot.ubicacionId != null) {
                ubicacionTextView.text = parkingSlot.ubicacionId.toString()
            } else {
                ubicacionTextView.text = "Ubicacion desconocida"
            }

            deleteButton.setOnClickListener { onDelete(parkingSlot) }
        }
    }
}
