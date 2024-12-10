package com.tecsup.prj_pc02.modelo.dto;

import java.time.LocalDateTime;

public class MovimientoResponseDTO {
    private Integer id;
    private Integer usuarioId;
    private Integer vehiculoId;
    private Integer estacionamientoId;
    private Integer espacioId;
    private LocalDateTime fechaHoraEntrada;
    private LocalDateTime fechaHoraSalida;

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Integer vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public Integer getEstacionamientoId() {
        return estacionamientoId;
    }

    public void setEstacionamientoId(Integer estacionamientoId) {
        this.estacionamientoId = estacionamientoId;
    }

    public Integer getEspacioId() {
        return espacioId;
    }

    public void setEspacioId(Integer espacioId) {
        this.espacioId = espacioId;
    }

    public LocalDateTime getFechaHoraEntrada() {
        return fechaHoraEntrada;
    }

    public void setFechaHoraEntrada(LocalDateTime fechaHoraEntrada) {
        this.fechaHoraEntrada = fechaHoraEntrada;
    }

    public LocalDateTime getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }
}

