package com.tecsup.prj_pc02.modelo.dto;

import javax.validation.constraints.NotNull;

public class AsignacionEspacioDTO {

    @NotNull
    private Integer usuarioId;

    @NotNull
    private Integer vehiculoId;

    @NotNull
    private Integer estacionamientoId;

    public AsignacionEspacioDTO() {
    }

    public AsignacionEspacioDTO(Integer usuarioId, Integer vehiculoId, Integer estacionamientoId) {
        this.usuarioId = usuarioId;
        this.vehiculoId = vehiculoId;
        this.estacionamientoId = estacionamientoId;
    }

    // Getters y Setters
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

    @Override
    public String toString() {
        return "AsignacionEspacioDTO{" +
                "usuarioId=" + usuarioId +
                ", vehiculoId=" + vehiculoId +
                ", estacionamientoId=" + estacionamientoId +
                '}';
    }
}
