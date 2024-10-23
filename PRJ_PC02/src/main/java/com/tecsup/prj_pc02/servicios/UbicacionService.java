package com.tecsup.prj_pc02.servicios;

import com.tecsup.prj_pc02.modelo.entidades.Ubicacion;
import java.util.List;

public interface UbicacionService {

    List<Ubicacion> listar();

    Ubicacion buscar(Integer id);

    void grabar(Ubicacion ubicacion);

    void eliminar(Integer id);
}
