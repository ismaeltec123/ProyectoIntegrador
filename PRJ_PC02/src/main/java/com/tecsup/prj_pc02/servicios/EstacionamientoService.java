package com.tecsup.prj_pc02.servicios;

import com.tecsup.prj_pc02.modelo.entidades.Estacionamiento;

import java.util.List;

public interface EstacionamientoService {
    Estacionamiento grabar(Estacionamiento estacionamiento);

    void eliminar(Integer id);

    Estacionamiento buscar(Integer id);

    List<Estacionamiento> listar();
}