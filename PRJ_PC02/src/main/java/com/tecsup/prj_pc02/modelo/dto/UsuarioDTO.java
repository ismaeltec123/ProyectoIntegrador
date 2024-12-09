package com.tecsup.prj_pc02.modelo.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UsuarioDTO {

    @NotNull
    @Size(max = 15)
    private String dni;

    @Size(max = 20)
    private String codigoEstudiante;

    @Size(max = 100)
    private String nombre;

    // Constructor vacío
    public UsuarioDTO() {}

    // Constructor completo
    public UsuarioDTO(String dni, String codigoEstudiante, String nombre) {
        this.dni = dni;
        this.codigoEstudiante = codigoEstudiante;
        this.nombre = nombre;
    }

    // Getters y Setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(String codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
