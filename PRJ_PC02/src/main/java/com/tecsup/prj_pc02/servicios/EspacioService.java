package com.tecsup.prj_pc02.servicios;

import com.tecsup.prj_pc02.modelo.entidades.Espacio;

import java.util.List;

public interface EspacioService {
    Espacio grabar(Espacio espacio);

    void eliminar(Integer id);

    Espacio buscar(Integer id);

    List<Espacio> listar();
}
