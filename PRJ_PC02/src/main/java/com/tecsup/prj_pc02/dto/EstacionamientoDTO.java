package com.tecsup.prj_pc02.dto;

import java.util.List;

public class EstacionamientoDTO {
    private Integer id;
    private String nombre;
    private String tipo;
    private Integer capacidad;
    private List<EspacioDTO> espacios; // Lista de DTOs en lugar de entidades completas

    // Constructor vacío
    public EstacionamientoDTO() {
    }

    // Constructor con argumentos
    public EstacionamientoDTO(Integer id, String nombre, String tipo, Integer capacidad, List<EspacioDTO> espacios) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.espacios = espacios;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public List<EspacioDTO> getEspacios() {
        return espacios;
    }

    public void setEspacios(List<EspacioDTO> espacios) {
        this.espacios = espacios;
    }
}

