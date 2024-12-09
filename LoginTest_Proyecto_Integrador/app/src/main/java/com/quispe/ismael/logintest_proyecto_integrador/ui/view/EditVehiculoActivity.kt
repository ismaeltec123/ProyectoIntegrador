package com.quispe.ismael.logintest_proyecto_integrador.ui.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.quispe.ismael.logintest_proyecto_integrador.R
import com.quispe.ismael.logintest_proyecto_integrador.data.model.VehiculoDTO
import com.quispe.ismael.logintest_proyecto_integrador.data.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditVehiculoActivity : AppCompatActivity() {

    private lateinit var editTextPlaca: EditText
    private lateinit var editTextMarca: EditText
    private lateinit var editTextModelo: EditText
    private lateinit var editTextColor: EditText
    private lateinit var editTextAnio: EditText

    private var vehiculoId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_vehiculo)

        editTextPlaca = findViewById(R.id.editTextPlaca)
        editTextMarca = findViewById(R.id.editTextMarca)
        editTextModelo = findViewById(R.id.editTextModelo)
        editTextColor = findViewById(R.id.editTextColor)
        editTextAnio = findViewById(R.id.editTextAnio)

        vehiculoId = intent.getIntExtra("vehiculoId", -1)
        editTextPlaca.setText(intent.getStringExtra("placa"))
        editTextMarca.setText(intent.getStringExtra("marca"))
        editTextModelo.setText(intent.getStringExtra("modelo"))
        editTextColor.setText(intent.getStringExtra("color"))
        editTextAnio.setText(intent.getIntExtra("anio", 0).toString())

        val buttonUpdate = findViewById<Button>(R.id.buttonUpdateVehiculo)
        buttonUpdate.setOnClickListener {
            updateVehiculo()
        }
    }

    private fun updateVehiculo() {
        if (vehiculoId == -1) {
            Toast.makeText(this, "Vehículo no válido", Toast.LENGTH_SHORT).show()
            return
        }

        val placa = editTextPlaca.text.toString()
        val marca = editTextMarca.text.toString()
        val modelo = editTextModelo.text.toString()
        val color = editTextColor.text.toString()
        val anio = editTextAnio.text.toString().toIntOrNull()

        if (placa.isEmpty() || marca.isEmpty() || modelo.isEmpty() || color.isEmpty() || anio == null) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val vehiculoDTO = VehiculoDTO(
            id = vehiculoId,
            usuarioId = null, // El usuario no se cambia
            placa = placa,
            marca = marca,
            modelo = modelo,
            color = color,
            anio = anio
        )

        RetrofitClient.apiService.updateVehiculo(vehiculoId, vehiculoDTO).enqueue(object : Callback<VehiculoDTO> {
            override fun onResponse(call: Call<VehiculoDTO>, response: Response<VehiculoDTO>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@EditVehiculoActivity, "Vehículo actualizado", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@EditVehiculoActivity, "Error al actualizar vehículo", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<VehiculoDTO>, t: Throwable) {
                Toast.makeText(this@EditVehiculoActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
