package com.quispe.ismael.logintest_proyecto_integrador.data.model

data class Estacionamiento(
    val pk_id_estacionamiento: Int?,
    val nombre: String,
    val tipo: TipoEstacionamiento,
    val capacidad: Int
)

enum class TipoEstacionamiento {
    trabajadores,
    general
}