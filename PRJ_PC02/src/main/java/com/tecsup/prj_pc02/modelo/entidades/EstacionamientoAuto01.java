package com.tecsup.prj_pc02.modelo.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "EstacionamientoAuto01")
public class EstacionamientoAuto01 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEstacionamientoAuto01")
    private Integer idEstacionamientoAuto01;

    @Column
    @NotNull
    @Size(min = 1, max = 45)
    private String idslot;

    @Column
    @NotNull
    @Size(min = 1, max = 45)
    private String placa; // Cambiado de 'capacidad' a 'placa'

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ubicacion_id")
    @JsonIgnore
    private Ubicacion ubicacion;

    // Constructor vacío
    public EstacionamientoAuto01() {}

    // Constructor con argumentos
    public EstacionamientoAuto01(String idslot, String placa, Ubicacion ubicacion) {
        this.idslot = idslot;
        this.placa = placa;
        this.ubicacion = ubicacion;
    }

    // Getters y Setters
    public Integer getIdEstacionamientoAuto01() {
        return idEstacionamientoAuto01;
    }

    public void setIdEstacionamientoAuto01(Integer idEstacionamientoAuto01) {
        this.idEstacionamientoAuto01 = idEstacionamientoAuto01;
    }

    public String getIdslot() {
        return idslot;
    }

    public void setIdslot(String idslot) {
        this.idslot = idslot;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getUbicacionId() {
        return this.ubicacion != null ? this.ubicacion.getId() : null;
    }


    @Override
    public String toString() {
        return "EstacionamientoAuto01{" +
                "idEstacionamientoAuto01=" + idEstacionamientoAuto01 +
                ", idslot='" + idslot + '\'' +
                ", placa='" + placa + '\'' +
                ", ubicacion=" + ubicacion +
                '}';
    }
}
