package com.tecsup.prj_pc02.modelo.dto;

import java.time.LocalDateTime;

public class AsignacionMovimientoDTO {
    private Integer fk_id_usuario;
    private Integer fk_id_vehiculo;
    private Integer fk_id_estacionamiento;
    private Integer fk_id_espacio;
    private LocalDateTime fecha_hora_entrada;

    // Getters y Setters
    public Integer getFk_id_usuario() {
        return fk_id_usuario;
    }

    public void setFk_id_usuario(Integer fk_id_usuario) {
        this.fk_id_usuario = fk_id_usuario;
    }

    public Integer getFk_id_vehiculo() {
        return fk_id_vehiculo;
    }

    public void setFk_id_vehiculo(Integer fk_id_vehiculo) {
        this.fk_id_vehiculo = fk_id_vehiculo;
    }

    public Integer getFk_id_estacionamiento() {
        return fk_id_estacionamiento;
    }

    public void setFk_id_estacionamiento(Integer fk_id_estacionamiento) {
        this.fk_id_estacionamiento = fk_id_estacionamiento;
    }

    public Integer getFk_id_espacio() {
        return fk_id_espacio;
    }

    public void setFk_id_espacio(Integer fk_id_espacio) {
        this.fk_id_espacio = fk_id_espacio;
    }

    public LocalDateTime getFecha_hora_entrada() {
        return fecha_hora_entrada;
    }

    public void setFecha_hora_entrada(LocalDateTime fecha_hora_entrada) {
        this.fecha_hora_entrada = fecha_hora_entrada;
    }
}
