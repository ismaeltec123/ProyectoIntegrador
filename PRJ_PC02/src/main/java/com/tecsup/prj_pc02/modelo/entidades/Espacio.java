package com.tecsup.prj_pc02.modelo.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.tecsup.prj_pc02.modelo.entidades.EstadoEspacio;

@Entity
@Table(name = "espacios")
@JsonPropertyOrder({ "id", "estacionamientoId", "codigoEspacio", "estado" })
public class Espacio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_espacio")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY) // Relación LAZY
    @JoinColumn(name = "fk_id_estacionamiento", nullable = false)
    @JsonIgnore // Ignorar el objeto completo de Estacionamiento en la serialización JSON
    private Estacionamiento estacionamiento;


    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codigo_espacio")
    private String codigoEspacio;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private EstadoEspacio estado; // Enum para "disponible" u "ocupado"

    public Espacio() {
    }

    public Espacio(Integer id, Estacionamiento estacionamiento, String codigoEspacio, EstadoEspacio estado) {
        this.id = id;
        this.estacionamiento = estacionamiento;
        this.codigoEspacio = codigoEspacio;
        this.estado = estado;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEstacionamiento(Estacionamiento estacionamiento) {
        this.estacionamiento = estacionamiento;
    }

    // Este método devolverá solo el ID del Estacionamiento relacionado
    public Integer getEstacionamientoId() {
        return this.estacionamiento != null ? this.estacionamiento.getId() : null;
    }

    public String getCodigoEspacio() {
        return codigoEspacio;
    }

    public void setCodigoEspacio(String codigoEspacio) {
        this.codigoEspacio = codigoEspacio;
    }

    public EstadoEspacio getEstado() {
        return estado;
    }

    public void setEstado(EstadoEspacio estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Espacio{" +
                "id=" + id +
                ", estacionamiento=" + estacionamiento +
                ", codigoEspacio='" + codigoEspacio + '\'' +
                ", estado=" + estado +
                '}';
    }
}
