package com.tecsup.prj_pc02.modelo.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class LiberacionMovimientoDTO {

    @NotNull
    private Integer id_movimiento;

    @NotNull
    private LocalDateTime fecha_hora_salida;

    // Getters y Setters
    public Integer getId_movimiento() {
        return id_movimiento;
    }

    public void setId_movimiento(Integer id_movimiento) {
        this.id_movimiento = id_movimiento;
    }

    public LocalDateTime getFecha_hora_salida() {
        return fecha_hora_salida;
    }

    public void setFecha_hora_salida(LocalDateTime fecha_hora_salida) {
        this.fecha_hora_salida = fecha_hora_salida;
    }
}

