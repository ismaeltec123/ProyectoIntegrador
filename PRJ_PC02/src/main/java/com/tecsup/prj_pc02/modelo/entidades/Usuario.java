package com.tecsup.prj_pc02.modelo.entidades;

import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_usuario")
    private Integer id;

    @Column(unique = true, nullable = false)
    @NotNull
    @Size(max = 15)
    private String dni;

    @Column(name = "codigo_estudiante")
    @Size(max = 20)
    private String codigoEstudiante;

    @Column(nullable = false)
    @NotNull
    @Size(max = 100)
    private String nombre;

    @Column(name = "tipo_usuario", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @Column(name = "preferencia_estacionamiento")
    private Integer preferenciaEstacionamiento;

    @Column(unique = true, nullable = false)
    @NotNull
    @Size(max = 255)
    private String qr;

    public Usuario() {
    }

    public Usuario(String dni, String codigoEstudiante, String nombre, TipoUsuario tipoUsuario, Integer preferenciaEstacionamiento, String qr) {
        this.dni = dni;
        this.codigoEstudiante = codigoEstudiante;
        this.nombre = nombre;
        this.tipoUsuario = tipoUsuario;
        this.preferenciaEstacionamiento = preferenciaEstacionamiento;
        this.qr = qr;
    }

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Integer getPreferenciaEstacionamiento() {
        return preferenciaEstacionamiento;
    }

    public void setPreferenciaEstacionamiento(Integer preferenciaEstacionamiento) {
        this.preferenciaEstacionamiento = preferenciaEstacionamiento;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", codigoEstudiante='" + codigoEstudiante + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipoUsuario=" + tipoUsuario +
                ", preferenciaEstacionamiento=" + preferenciaEstacionamiento +
                ", qr='" + qr + '\'' +
                '}';
    }
}
