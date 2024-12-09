package com.quispe.ismael.logintest_proyecto_integrador.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.quispe.ismael.logintest_proyecto_integrador.databinding.FragmentParkingHomeBinding
import com.quispe.ismael.logintest_proyecto_integrador.data.model.EstacionamientoDTO
import com.quispe.ismael.logintest_proyecto_integrador.data.model.PreferenciaDTO
import com.quispe.ismael.logintest_proyecto_integrador.data.database.SharedPreferencesRepository
import com.quispe.ismael.logintest_proyecto_integrador.data.network.RetrofitClient
import com.quispe.ismael.logintest_proyecto_integrador.ui.adapter.ParkingHomeAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ParkingHomeFragment : Fragment() {

    private var _binding: FragmentParkingHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var parkingHomeAdapter: ParkingHomeAdapter
    private lateinit var sharedPreferencesRepository: SharedPreferencesRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentParkingHomeBinding.inflate(inflater, container, false)

        // Inicializar SharedPreferences
        sharedPreferencesRepository = SharedPreferencesRepository(requireContext())

        // Configurar RecyclerView
        parkingHomeAdapter = ParkingHomeAdapter(emptyList()) { estacionamiento ->
            actualizarPreferencia(estacionamiento.id)
        }
        binding.parkingRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.parkingRecyclerView.adapter = parkingHomeAdapter

        // Configurar botón de refresh
        binding.refreshButton.setOnClickListener {
            fetchEstacionamientos()
        }
        binding.buttonEditResource.setOnClickListener {
            val intent = Intent(requireContext(), EditResourceActivity::class.java)
            startActivity(intent)
        }

        // Nuevo botón para ir a VehiculoActivity
        binding.buttonVehiculo.setOnClickListener {
            val intent = Intent(requireContext(), VehiculoActivity::class.java)
            startActivity(intent)
        }

        // Cargar datos al iniciar el fragmento
        fetchEstacionamientos()

        return binding.root
    }

    private fun fetchEstacionamientos() {
        RetrofitClient.apiService.getEstacionamientosDisponibles().enqueue(object : Callback<List<EstacionamientoDTO>> {
            override fun onResponse(
                call: Call<List<EstacionamientoDTO>>,
                response: Response<List<EstacionamientoDTO>>
            ) {
                if (response.isSuccessful) {
                    val estacionamientos = response.body()
                    if (estacionamientos != null) {
                        parkingHomeAdapter.updateData(estacionamientos)
                    } else {
                        Toast.makeText(requireContext(), "No hay datos disponibles", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Error al obtener los datos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<EstacionamientoDTO>>, t: Throwable) {
                Toast.makeText(requireContext(), "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun actualizarPreferencia(estacionamientoId: Int) {
        val userId = sharedPreferencesRepository.obtenerIdUsuarioDesdePreferencias()
        Log.d("ParkingHomeFragment", "ID de usuario recuperado de SharedPreferences: $userId")
        if (userId == -1) {
            Toast.makeText(requireContext(), "Usuario no autenticado", Toast.LENGTH_SHORT).show()
            return
        }

        val preferenciaDTO = PreferenciaDTO(preferenciaEstacionamiento = estacionamientoId)

        RetrofitClient.apiService.actualizarPreferencia(userId, preferenciaDTO).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(requireContext(), "Preferencia actualizada", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Error al actualizar preferencia", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(requireContext(), "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
