package com.tecsup.prj_pc02.modelo.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import com.tecsup.prj_pc02.modelo.entidades.Espacio;
@Entity
@Table(name = "estacionamientos")
public class Estacionamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_estacionamiento")
    private Integer id;

    @Column
    @NotNull
    @Size(min = 1, max = 50)
    private String nombre;

    @Column
    @NotNull
    @Size(min = 1, max = 45)
    private String tipo; // Puede ser "general" o "trabajadores"

    @Column
    @NotNull
    private Integer capacidad;

    public Estacionamiento() {
    }

    public Estacionamiento(Integer id, String nombre, String tipo, Integer capacidad) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidad = capacidad;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estacionamiento")
    private List<Espacio> espacios;

    @Override
    public String toString() {
        return "Estacionamiento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", capacidad=" + capacidad +
                '}';
    }
}
