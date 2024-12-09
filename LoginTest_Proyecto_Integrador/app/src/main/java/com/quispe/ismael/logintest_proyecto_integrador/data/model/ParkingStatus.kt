package com.quispe.ismael.logintest_proyecto_integrador.data.model

data class ParkingStatus(
    val lotName: String,
    val capacity: Int, // Porcentaje de ocupación
    val lastUpdated: String // Información sobre la última actualización
)
