package com.tecsup.prj_pc02.servicios;

import com.tecsup.prj_pc02.modelo.entidades.EstacionamientoMoto01;
import java.util.List;

public interface EstacionamientoMoto01Service {
    public List<EstacionamientoMoto01> listar();

    public void grabar(EstacionamientoMoto01 estacionamientoMoto01);

    public EstacionamientoMoto01 buscar(Integer id);

    public void eliminar(Integer id);
}
