package com.quispe.ismael.logintest_proyecto_integrador.data.model

data class Usuario(
    val id: Int,
    val dni: String,
    val nombre: String,
    val codigoEstudiante: String,
    val tipo_usuario: tipoUsuario,
    val qr: String,
    var preferenciaEstacionamiento: Int?
)
enum class tipoUsuario {
    profesor,
    alumno,
    trabajador,
    externo,
}