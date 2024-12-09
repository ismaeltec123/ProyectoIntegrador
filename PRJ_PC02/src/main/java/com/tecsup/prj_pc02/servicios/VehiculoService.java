package com.tecsup.prj_pc02.servicios;

import com.tecsup.prj_pc02.modelo.entidades.Usuario;
import com.tecsup.prj_pc02.modelo.entidades.Vehiculo;

import java.util.List;

public interface VehiculoService {
    Vehiculo grabar(Vehiculo vehiculo);
    void eliminar(Integer id);
    Vehiculo buscar(Integer id);
    List<Vehiculo> listar();

    List<Vehiculo> listarPorUsuario(Usuario usuario);
}
