package com.quispe.ismael.logintest_proyecto_integrador.data.model

data class EstacionamientoAuto01(
    val idEstacionamientoAuto01: Int?,
    val idslot: String,
    val placa: String,
    val ubicacionId: Int?
)

data class Ubicacion(
    val ubicacion_id: Int?,
    val nombre: String
)
