package com.quispe.ismael.logintest_proyecto_integrador.data.model

data class VehiculoDTO(
    val id: Int?= null ,
    val usuarioId: Int?,
    val placa: String,
    val marca: String,
    val modelo: String,
    val color: String,
    val anio: Int
)