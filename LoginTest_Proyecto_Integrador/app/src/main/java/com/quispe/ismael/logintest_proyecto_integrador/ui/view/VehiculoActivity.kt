package com.quispe.ismael.logintest_proyecto_integrador.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.quispe.ismael.logintest_proyecto_integrador.R
import com.quispe.ismael.logintest_proyecto_integrador.data.database.SharedPreferencesRepository
import com.quispe.ismael.logintest_proyecto_integrador.data.model.VehiculoDTO
import com.quispe.ismael.logintest_proyecto_integrador.data.network.RetrofitClient
import com.quispe.ismael.logintest_proyecto_integrador.ui.adapter.VehiculoAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VehiculoActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var vehiculoAdapter: VehiculoAdapter
    private lateinit var sharedPreferencesRepository: SharedPreferencesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehiculo)

        sharedPreferencesRepository = SharedPreferencesRepository(this)

        // Inicializa el RecyclerView
        recyclerView = findViewById(R.id.recyclerViewVehiculos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        vehiculoAdapter = VehiculoAdapter(emptyList()) { vehiculo, action ->
            handleVehiculoAction(vehiculo, action)
        }
        recyclerView.adapter = vehiculoAdapter

        // Configura el botón para agregar un vehículo
        val btnAddVehiculo = findViewById<Button>(R.id.btnAddVehiculo)
        btnAddVehiculo.setOnClickListener {
            val intent = Intent(this, CreateVehiculoActivity::class.java)
            startActivity(intent)
        }

        fetchVehiculos()
    }

    private fun fetchVehiculos() {
        val userId = sharedPreferencesRepository.obtenerIdUsuarioDesdePreferencias()

        if (userId == -1) {
            Toast.makeText(this, "Usuario no autenticado", Toast.LENGTH_SHORT).show()
            return
        }

        RetrofitClient.apiService.getVehiculosPorUsuario(userId).enqueue(object : Callback<List<VehiculoDTO>> {
            override fun onResponse(
                call: Call<List<VehiculoDTO>>,
                response: Response<List<VehiculoDTO>>
            ) {
                if (response.isSuccessful) {
                    val vehiculos = response.body()
                    if (vehiculos != null) {
                        vehiculoAdapter.updateData(vehiculos)
                    } else {
                        Toast.makeText(this@VehiculoActivity, "No hay vehículos disponibles", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@VehiculoActivity, "Error al obtener vehículos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<VehiculoDTO>>, t: Throwable) {
                Log.e("VehiculoActivity", "Error de conexión: ${t.message}")
                Toast.makeText(this@VehiculoActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun handleVehiculoAction(vehiculo: VehiculoDTO, action: String) {
        when (action) {
            "edit" -> {
                val intent = Intent(this, EditVehiculoActivity::class.java)
                intent.putExtra("vehiculoId", vehiculo.id)
                intent.putExtra("placa", vehiculo.placa)
                intent.putExtra("marca", vehiculo.marca)
                intent.putExtra("modelo", vehiculo.modelo)
                intent.putExtra("color", vehiculo.color)
                intent.putExtra("anio", vehiculo.anio)
                startActivity(intent)
            }
            "delete" -> {
                deleteVehiculo(vehiculo.id)
            }
            else -> Log.w("VehiculoActivity", "Acción desconocida: $action")
        }
    }

    private fun deleteVehiculo(vehiculoId: Int?) {
        if (vehiculoId == null) {
            Toast.makeText(this, "ID del vehículo no válido", Toast.LENGTH_SHORT).show()
            return
        }

        RetrofitClient.apiService.eliminarVehiculo(vehiculoId).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@VehiculoActivity, "Vehículo eliminado", Toast.LENGTH_SHORT).show()
                    fetchVehiculos() // Actualiza la lista después de eliminar
                } else {
                    Toast.makeText(this@VehiculoActivity, "Error al eliminar el vehículo", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("VehiculoActivity", "Error al eliminar vehículo: ${t.message}")
                Toast.makeText(this@VehiculoActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
