package com.tecsup.prj_pc02.modelo.entidades;

import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ubicacion")
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ubicacion")
    private Integer id;

    @Column
    @NotNull
    @Size(min = 1, max = 45)
    private String nombre;

    public Ubicacion() {
    }

    public Ubicacion(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

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

    @Override
    public String toString() {
        return "Ubicacion{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
