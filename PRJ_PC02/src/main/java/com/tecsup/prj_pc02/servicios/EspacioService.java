package com.tecsup.prj_pc02.servicios;

import com.tecsup.prj_pc02.modelo.dto.AsignacionEspacioDTO;
import com.tecsup.prj_pc02.modelo.entidades.Espacio;

import java.util.List;
import java.util.Optional;

public interface EspacioService {
    Espacio grabar(Espacio espacio);

    void eliminar(Integer id);

    Espacio buscar(Integer id);

    List<Espacio> listar();


    Espacio asignarEspacio(AsignacionEspacioDTO dto);
    Espacio liberarEspacio(Integer espacioId);
    Optional<Espacio> buscarEspacioPorId(Integer id);
}
