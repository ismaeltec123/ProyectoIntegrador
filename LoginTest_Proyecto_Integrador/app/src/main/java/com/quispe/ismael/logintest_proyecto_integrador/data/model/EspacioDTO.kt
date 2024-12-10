package com.quispe.ismael.logintest_proyecto_integrador.data.model

data class EspacioDTO(
    val id: Int?, // ID del espacio, puede ser null cuando se crea un nuevo espacio
    val estacionamientoId: Int, // ID del estacionamiento relacionado
    val codigoEspacio: String, // Código del espacio
    val estado: String // Estado del espacio: "disponible" o "ocupado"
)