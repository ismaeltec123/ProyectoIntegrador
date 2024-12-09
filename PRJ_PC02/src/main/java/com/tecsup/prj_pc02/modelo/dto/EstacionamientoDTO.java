package com.tecsup.prj_pc02.modelo.dto;


public class EstacionamientoDTO {

    private Integer id;
    private String nombre;
    private String tipo;
    private Integer capacidadDisponible;

    // Constructor
    public EstacionamientoDTO(Integer id, String nombre, String tipo, Integer capacidadDisponible) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidadDisponible = capacidadDisponible;
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

    public Integer getCapacidadDisponible() {
        return capacidadDisponible;
    }

    public void setCapacidadDisponible(Integer capacidadDisponible) {
        this.capacidadDisponible = capacidadDisponible;
    }

    @Override
    public String toString() {
        return "EstacionamientoDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", capacidadDisponible=" + capacidadDisponible +
                '}';
    }
}

