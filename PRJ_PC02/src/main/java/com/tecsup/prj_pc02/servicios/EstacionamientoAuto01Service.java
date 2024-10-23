package com.tecsup.prj_pc02.servicios;

import com.tecsup.prj_pc02.modelo.entidades.EstacionamientoAuto01;

import java.util.List;

public interface EstacionamientoAuto01Service {
    public List<EstacionamientoAuto01> listar();

    public EstacionamientoAuto01 grabar(EstacionamientoAuto01 estacionamientoAuto01);

    public EstacionamientoAuto01 buscar(Integer id);

    public void eliminar(Integer id);
}


