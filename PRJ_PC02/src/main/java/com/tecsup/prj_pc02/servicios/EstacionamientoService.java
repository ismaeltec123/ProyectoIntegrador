package com.tecsup.prj_pc02.servicios;

import com.tecsup.prj_pc02.modelo.dto.EstacionamientoDTO;
import com.tecsup.prj_pc02.modelo.entidades.Estacionamiento;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstacionamientoService {
    Estacionamiento grabar(Estacionamiento estacionamiento);

    void eliminar(Integer id);

    Estacionamiento buscar(Integer id);

    List<Estacionamiento> listar();

    List<EstacionamientoDTO> listarConCapacidadDisponible();


}