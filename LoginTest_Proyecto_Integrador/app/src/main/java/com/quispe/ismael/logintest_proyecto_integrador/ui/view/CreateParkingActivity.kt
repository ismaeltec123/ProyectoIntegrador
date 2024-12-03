package com.quispe.ismael.logintest_proyecto_integrador.ui.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.quispe.ismael.logintest_proyecto_integrador.R
import com.quispe.ismael.logintest_proyecto_integrador.data.model.EstacionamientoAuto01
import com.quispe.ismael.logintest_proyecto_integrador.data.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateParkingActivity : AppCompatActivity() {

    private lateinit var idSlotEditText: EditText
    private lateinit var placaEditText: EditText
    private lateinit var ubicacionIdEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_parking)

        idSlotEditText = findViewById(R.id.idSlotEditText)
        placaEditText = findViewById(R.id.placaEditText)
        ubicacionIdEditText = findViewById(R.id.ubicacionIdEditText)
        saveButton = findViewById(R.id.saveButton)

        saveButton.setOnClickListener {
            val idSlot = idSlotEditText.text.toString()
            val placa = placaEditText.text.toString()
            val ubicacionId = ubicacionIdEditText.text.toString().toInt()

            val newParking = EstacionamientoAuto01(null, idSlot, placa, ubicacionId)

            RetrofitClient.apiService.createParkingSlot(newParking).enqueue(object : Callback<EstacionamientoAuto01> {
                override fun onResponse(call: Call<EstacionamientoAuto01>, response: Response<EstacionamientoAuto01>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@CreateParkingActivity, "Estacionamiento creado", Toast.LENGTH_SHORT).show()
                        setResult(RESULT_OK)

                        finish() // Regresa a la pantalla anterior
                    } else {
                        Toast.makeText(this@CreateParkingActivity, "Error al crear estacionamiento", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<EstacionamientoAuto01>, t: Throwable) {
                    Toast.makeText(this@CreateParkingActivity, "Error API", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
