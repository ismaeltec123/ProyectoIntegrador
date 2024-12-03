package com.tecsup.prj_pc02.modelo.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "vehiculos")
@JsonPropertyOrder({ "id", "usuarioId", "placa", "marca", "modelo", "color", "anio" })
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_vehiculo")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY) // Relación LAZY
    @JoinColumn(name = "fk_id_usuario", nullable = false)
    @JsonIgnore // Ignorar el objeto completo de Usuario en la serialización JSON
    private Usuario usuario;

    @Column(nullable = false, unique = true)
    @NotNull
    @Size(max = 20)
    private String placa;

    @Column(nullable = false)
    @NotNull
    @Size(max = 50)
    private String marca;

    @Column(nullable = false)
    @NotNull
    @Size(max = 50)
    private String modelo;

    @Column(nullable = false)
    @NotNull
    @Size(max = 30)
    private String color;

    @Column(nullable = false)
    @NotNull
    private Integer anio;

    public Vehiculo() {}

    public Vehiculo(Integer id, Usuario usuario, String placa, String marca, String modelo, String color, Integer anio) {
        this.id = id;
        this.usuario = usuario;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.anio = anio;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // Este método devolverá solo el ID del Usuario relacionado
    public Integer getUsuarioId() {
        return this.usuario != null ? this.usuario.getId() : null;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "id=" + id +
                ", usuarioId=" + getUsuarioId() +
                ", placa='" + placa + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", color='" + color + '\'' +
                ", anio=" + anio +
                '}';
    }
}
