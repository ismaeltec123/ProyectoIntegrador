package com.quispe.ismael.logintest_proyecto_integrador.ui.view

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.quispe.ismael.logintest_proyecto_integrador.R
import com.quispe.ismael.logintest_proyecto_integrador.data.database.SharedPreferencesRepository
import com.quispe.ismael.logintest_proyecto_integrador.data.model.Usuario
import com.quispe.ismael.logintest_proyecto_integrador.data.model.UsuarioDTO
import com.quispe.ismael.logintest_proyecto_integrador.data.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditResourceActivity : AppCompatActivity() {

    private lateinit var sharedPreferencesRepository: SharedPreferencesRepository
    private lateinit var editDni: EditText
    private lateinit var editCodigoEstudiante: EditText
    private lateinit var textNombre: TextView
    private lateinit var textTipoUsuario: TextView
    private lateinit var textPreferenciaEstacionamiento: TextView
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_resource)

        sharedPreferencesRepository = SharedPreferencesRepository(this)

        // Referencias a las vistas
        editDni = findViewById(R.id.edit_dni)
        editCodigoEstudiante = findViewById(R.id.edit_codigo_estudiante)
        textNombre = findViewById(R.id.text_nombre)
        textTipoUsuario = findViewById(R.id.text_tipo_usuario)
        textPreferenciaEstacionamiento = findViewById(R.id.text_preferencia_estacionamiento)
        saveButton = findViewById(R.id.save_button)

        fetchUserData()

        saveButton.setOnClickListener {
            saveChanges()
        }
    }

    private fun fetchUserData() {
        val userId = sharedPreferencesRepository.obtenerIdUsuarioDesdePreferencias()
        RetrofitClient.apiService.getUsuario(userId).enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.isSuccessful) {
                    val usuario = response.body()
                    if (usuario != null) {
                        // Rellenar los campos con los datos del usuario
                        editDni.setText(usuario.dni)
                        editCodigoEstudiante.setText(usuario.codigoEstudiante ?: "")
                        textNombre.text = "Nombre: ${usuario.nombre}"
                        textTipoUsuario.text = "Tipo Usuario: ${usuario.tipo_usuario}"
                        textPreferenciaEstacionamiento.text =
                            "Preferencia Estacionamiento: ${usuario.preferenciaEstacionamiento ?: "N/A"}"
                    }
                } else {
                    Toast.makeText(this@EditResourceActivity, "Error al cargar datos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                Toast.makeText(this@EditResourceActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun saveChanges() {
        val userId = sharedPreferencesRepository.obtenerIdUsuarioDesdePreferencias()
        val updatedUser = UsuarioDTO(
            dni = editDni.text.toString(),
            codigoEstudiante = editCodigoEstudiante.text.toString()
        )

        RetrofitClient.apiService.updateUsuario(userId, updatedUser).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@EditResourceActivity, "Datos actualizados", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@EditResourceActivity, "Error al actualizar datos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@EditResourceActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
