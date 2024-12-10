package com.quispe.ismael.logintest_proyecto_integrador.data.model

data class AsignacionMovimientoDTO(
    val fk_id_usuario: Int,
    val fk_id_vehiculo: Int,
    val fk_id_estacionamiento: Int,
    val fk_id_espacio: Int,
    val fecha_hora_entrada: String
)
