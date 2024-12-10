package com.quispe.ismael.logintest_proyecto_integrador.data.model

data class MovimientoDTO(
    val id: Int?,
    val usuario: UsuarioDTO?,
    val vehiculo: VehiculoDTO?,
    val estacionamiento: EstacionamientoDTO?,
    val espacio: EspacioDTO?,
    val fechaHoraEntrada: String?,
    val fechaHoraSalida: String?
)
