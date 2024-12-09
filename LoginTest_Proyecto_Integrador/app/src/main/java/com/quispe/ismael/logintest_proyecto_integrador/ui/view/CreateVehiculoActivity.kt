package com.quispe.ismael.logintest_proyecto_integrador.ui.view

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.quispe.ismael.logintest_proyecto_integrador.R
import com.quispe.ismael.logintest_proyecto_integrador.data.database.SharedPreferencesRepository
import com.quispe.ismael.logintest_proyecto_integrador.data.model.VehiculoDTO
import com.quispe.ismael.logintest_proyecto_integrador.data.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateVehiculoActivity : AppCompatActivity() {

    private lateinit var editTextPlaca: EditText
    private lateinit var editTextMarca: EditText
    private lateinit var editTextModelo: EditText
    private lateinit var editTextColor: EditText
    private lateinit var editTextAnio: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_vehiculo)

        editTextPlaca = findViewById(R.id.editTextPlaca)
        editTextMarca = findViewById(R.id.editTextMarca)
        editTextModelo = findViewById(R.id.editTextModelo)
        editTextColor = findViewById(R.id.editTextColor)
        editTextAnio = findViewById(R.id.editTextAnio)

        val buttonCreate = findViewById<Button>(R.id.buttonCreateVehiculo)
        buttonCreate.setOnClickListener {
            createVehiculo()
        }
    }

    private fun createVehiculo() {
        val sharedPreferencesRepository = SharedPreferencesRepository(this)
        val userId = sharedPreferencesRepository.obtenerIdUsuarioDesdePreferencias()

        Log.d("CreateVehiculoActivity", "User ID obtenido de SharedPreferences: $userId")

        if (userId == -1) {
            Toast.makeText(this, "Usuario no autenticado", Toast.LENGTH_SHORT).show()
            return
        }

        val placa = editTextPlaca.text.toString()
        val marca = editTextMarca.text.toString()
        val modelo = editTextModelo.text.toString()
        val color = editTextColor.text.toString()
        val anio = editTextAnio.text.toString().toIntOrNull()
        Log.d("CreateVehiculoActivity", "Datos del vehículo ingresados: Placa=$placa, Marca=$marca, Modelo=$modelo, Color=$color, Año=$anio")
        if (placa.isEmpty() || marca.isEmpty() || modelo.isEmpty() || color.isEmpty() || anio == null) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val vehiculoDTO = VehiculoDTO(

            usuarioId = userId,
            placa = placa,
            marca = marca,
            modelo = modelo,
            color = color,
            anio = anio
        )

        Log.d("CreateVehiculoActivity", "Vehículo DTO creado: $vehiculoDTO")
        RetrofitClient.apiService.createVehiculo(vehiculoDTO).enqueue(object : Callback<VehiculoDTO> {
            override fun onResponse(call: Call<VehiculoDTO>, response: Response<VehiculoDTO>) {
                Log.d("CreateVehiculoActivity", "Respuesta del servidor: ${response.code()}, ${response.message()}")
                if (response.isSuccessful) {
                    Log.i("CreateVehiculoActivity", "Vehículo creado exitosamente: ${response.body()}")
                    Toast.makeText(this@CreateVehiculoActivity, "Vehículo creado", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Log.e("CreateVehiculoActivity", "Error al crear vehículo. Código: ${response.code()}")
                    Toast.makeText(this@CreateVehiculoActivity, "Error al crear vehículo", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<VehiculoDTO>, t: Throwable) {
                Toast.makeText(this@CreateVehiculoActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
