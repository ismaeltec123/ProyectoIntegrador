package com.tecsup.prj_pc02.dto;

public class EspacioDTO {
    private Integer id;
    private String codigoEspacio;
    private String estado;
    private Integer estacionamientoId; // Clave foránea como ID en lugar de la entidad completa

    // Constructor vacío
    public EspacioDTO() {
    }

    // Constructor con argumentos
    public EspacioDTO(Integer id, String codigoEspacio, String estado, Integer estacionamientoId) {
        this.id = id;
        this.codigoEspacio = codigoEspacio;
        this.estado = estado;
        this.estacionamientoId = estacionamientoId;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoEspacio() {
        return codigoEspacio;
    }

    public void setCodigoEspacio(String codigoEspacio) {
        this.codigoEspacio = codigoEspacio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getEstacionamientoId() {
        return estacionamientoId;
    }

    public void setEstacionamientoId(Integer estacionamientoId) {
        this.estacionamientoId = estacionamientoId;
    }
}
