package com.tecsup.prj_pc02.modelo.entidades;

import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "EstacionamientoMoto01")
public class EstacionamientoMoto01 {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEstacionamientoMoto01;

    @Column
    @NotNull
    @Size(min = 1, max = 45)
    private String idslot;

    @Column
    @NotNull
    @Size(min = 1, max = 45)
    private String placa;

    @ManyToOne
    @JoinColumn(name = "ubicacion_id", referencedColumnName = "id_ubicacion")
    private Ubicacion ubicacion;  // Relación con la tabla Ubicacion

    public EstacionamientoMoto01() {
    }

    public EstacionamientoMoto01(int idEstacionamientoMoto01, String idslot, String placa, Ubicacion ubicacion) {
        this.idEstacionamientoMoto01 = idEstacionamientoMoto01;
        this.idslot = idslot;
        this.placa = placa;
        this.ubicacion = ubicacion;
    }

    public int getIdEstacionamientoMoto01() {
        return idEstacionamientoMoto01;
    }

    public void setIdEstacionamientoMoto01(int idEstacionamientoMoto01) {
        this.idEstacionamientoMoto01 = idEstacionamientoMoto01;
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

    @Override
    public String toString() {
        return "EstacionamientoMoto01{" +
                "idEstacionamientoMoto01=" + idEstacionamientoMoto01 +
                ", idslot='" + idslot + '\'' +
                ", placa='" + placa+ '\'' +
                ", ubicacion=" + (ubicacion != null ? ubicacion.getNombre() : "null") +
                '}';
    }
}
