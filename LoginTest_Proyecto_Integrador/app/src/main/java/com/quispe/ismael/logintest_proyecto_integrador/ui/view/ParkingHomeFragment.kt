package com.quispe.ismael.logintest_proyecto_integrador.ui.view

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import com.quispe.ismael.logintest_proyecto_integrador.data.database.SharedPreferencesRepository
import com.quispe.ismael.logintest_proyecto_integrador.data.model.*
import com.quispe.ismael.logintest_proyecto_integrador.data.network.RetrofitClient
import com.quispe.ismael.logintest_proyecto_integrador.databinding.FragmentParkingHomeBinding
import com.quispe.ismael.logintest_proyecto_integrador.ui.adapter.ParkingHomeAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.util.Date
import java.util.Locale

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

        sharedPreferencesRepository = SharedPreferencesRepository(requireContext())

        // Configurar RecyclerView
        parkingHomeAdapter = ParkingHomeAdapter(emptyList()) { estacionamiento ->
            actualizarPreferencia(estacionamiento.id)
        }
        binding.parkingRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.parkingRecyclerView.adapter = parkingHomeAdapter

        // Configurar botón de refresh
        binding.refreshButton.setOnClickListener { fetchEstacionamientos() }

        // Configurar botón para editar recursos
        binding.buttonEditResource.setOnClickListener {
            startActivity(Intent(requireContext(), EditResourceActivity::class.java))
        }

        // Configurar botón Vehículo
        binding.buttonVehiculo.setOnClickListener {
            startActivity(Intent(requireContext(), VehiculoActivity::class.java))
        }
        /*
        binding.button_edit_resource.setOnClickListener {
            startActivity(Intent(requireContext(), EditResourceActivity::class.java))
        }
        */
        // Generar código QR
        generateQRCode(binding.qrImageView)
        binding.qrImageView.setOnClickListener {
            showVehicleSelectionDialog()
        }

        // Cargar datos iniciales
        fetchEstacionamientos()
        verificarEstadoInicial()

        return binding.root
    }

    private fun verificarEstadoInicial() {
        val movimientoId = sharedPreferencesRepository.getMovimientoId()
        binding.statusIndicator.text = if (movimientoId != -1) "Movimiento en curso" else "Sin asignación"
    }

    private fun fetchEstacionamientos() {
        RetrofitClient.apiService.getEstacionamientosDisponibles().enqueue(object : Callback<List<EstacionamientoDTO>> {
            override fun onResponse(call: Call<List<EstacionamientoDTO>>, response: Response<List<EstacionamientoDTO>>) {
                if (response.isSuccessful) {
                    parkingHomeAdapter.updateData(response.body() ?: emptyList())
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
        sharedPreferencesRepository.guardarPreferenciaEstacionamiento(estacionamientoId)

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

    private fun generateQRCode(imageView: ImageView) {
        val userId = sharedPreferencesRepository.obtenerIdUsuarioDesdePreferencias()
        val qrData = "UsuarioID:$userId"
        val writer = QRCodeWriter()

        try {
            val bitMatrix = writer.encode(qrData, BarcodeFormat.QR_CODE, 512, 512)
            val bitmap = Bitmap.createBitmap(bitMatrix.width, bitMatrix.height, Bitmap.Config.RGB_565)
            for (x in 0 until bitMatrix.width) {
                for (y in 0 until bitMatrix.height) {
                    bitmap.setPixel(x, y, if (bitMatrix.get(x, y)) android.graphics.Color.BLACK else android.graphics.Color.WHITE)
                }
            }
            imageView.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            Log.e("QRCode", "Error al generar QR", e)
        }
    }

    private fun showVehicleSelectionDialog() {
        val userId = sharedPreferencesRepository.obtenerIdUsuarioDesdePreferencias()

        if (userId == -1) {
            Toast.makeText(requireContext(), "Usuario no autenticado", Toast.LENGTH_SHORT).show()
            return
        }

        RetrofitClient.apiService.getVehiculosPorUsuario(userId).enqueue(object : Callback<List<VehiculoDTO>> {
            override fun onResponse(call: Call<List<VehiculoDTO>>, response: Response<List<VehiculoDTO>>) {
                if (response.isSuccessful) {
                    val vehiculos = response.body() ?: emptyList()
                    if (vehiculos.isNotEmpty()) {
                        val vehiculoNames = vehiculos.map { it.placa }.toTypedArray()
                        AlertDialog.Builder(requireContext())
                            .setTitle("Selecciona un vehículo")
                            .setItems(vehiculoNames) { _, which ->
                                performAssignOrRelease(vehiculos[which])
                            }
                            .show()
                    } else {
                        Toast.makeText(requireContext(), "No tienes vehículos registrados", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Error al obtener los vehículos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<VehiculoDTO>>, t: Throwable) {
                Toast.makeText(requireContext(), "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun performAssignOrRelease(selectedVehiculo: VehiculoDTO) {
        val userId = sharedPreferencesRepository.obtenerIdUsuarioDesdePreferencias()
        val estacionamientoId = sharedPreferencesRepository.getPreferenciaEstacionamiento()
        val movimientoId = sharedPreferencesRepository.getMovimientoId()
        val espacioId = sharedPreferencesRepository.getPreferenciaEstacionamiento()
        if (movimientoId != -1) {
            binding.statusIndicator.text = "Liberando espacio..."
            liberarEspacio(espacioId)
        } else {
            binding.statusIndicator.text = "Asignando espacio..."
            asignarEspacio(sharedPreferencesRepository.obtenerIdUsuarioDesdePreferencias(), espacioId, selectedVehiculo)
        }
    }


    private fun asignarEspacio(userId: Int, estacionamientoId: Int, selectedVehiculo: VehiculoDTO) {
        val espacioId = sharedPreferencesRepository.getPreferenciaEstacionamiento()
        if (espacioId == -1) {
            Toast.makeText(requireContext(), "Selecciona un estacionamiento antes de asignar", Toast.LENGTH_SHORT).show()
            return
        }
        val fechaHoraActual = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).format(Date())
        // Crea el objeto para el registro de movimiento
        val movimiento = AsignacionMovimientoDTO(
            fk_id_usuario = userId,
            fk_id_vehiculo = selectedVehiculo.id!!,
            fk_id_estacionamiento = estacionamientoId,
            fk_id_espacio = espacioId,
            fecha_hora_entrada = fechaHoraActual // Hora actual
        )

        // Llama al endpoint para registrar el movimiento
        RetrofitClient.apiService.registrarMovimiento(movimiento).enqueue(object : Callback<MovimientoDTO> {
            override fun onResponse(call: Call<MovimientoDTO>, response: Response<MovimientoDTO>) {
                if (response.isSuccessful) {
                    val movimientoRegistrado = response.body()
                    movimientoRegistrado?.let {
                        sharedPreferencesRepository.guardarMovimientoId(it.id!!)
                        binding.statusIndicator.text = "Espacio asignado y movimiento registrado"
                        Toast.makeText(requireContext(), "Movimiento registrado correctamente", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Error al registrar movimiento", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MovimientoDTO>, t: Throwable) {
                Toast.makeText(requireContext(), "Error de conexión al registrar movimiento", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun liberarEspacio(espacioId: Int) {
        val movimientoId = sharedPreferencesRepository.getMovimientoId()
        if (movimientoId == -1) {
            Toast.makeText(requireContext(), "No hay un movimiento registrado para este espacio", Toast.LENGTH_SHORT).show()
            return
        }
        val fechaHoraActual = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).format(Date())
        // Crea el objeto para actualizar el movimiento
        val liberacionMovimiento = LiberacionMovimientoDTO(
            id_movimiento = movimientoId,
            fecha_hora_salida = fechaHoraActual // Hora actual
        )

        // Llama al endpoint para liberar el movimiento
        RetrofitClient.apiService.liberarMovimiento(liberacionMovimiento).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    sharedPreferencesRepository.liberarEspacio()
                    sharedPreferencesRepository.liberarMovimientoId()
                    binding.statusIndicator.text = "Espacio liberado y movimiento actualizado"
                    Toast.makeText(requireContext(), "Movimiento actualizado correctamente", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Error al liberar el movimiento", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(requireContext(), "Error de conexión al liberar movimiento", Toast.LENGTH_SHORT).show()
            }
        })


        }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
