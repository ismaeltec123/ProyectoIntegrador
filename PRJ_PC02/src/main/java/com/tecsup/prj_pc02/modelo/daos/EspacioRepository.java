package com.tecsup.prj_pc02.modelo.daos;

import com.tecsup.prj_pc02.modelo.entidades.Espacio;
import com.tecsup.prj_pc02.modelo.entidades.Estacionamiento;
import com.tecsup.prj_pc02.modelo.entidades.EstadoEspacio;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface EspacioRepository extends CrudRepository<Espacio, Integer> {
    List<Espacio> findByEstacionamientoAndEstado(Estacionamiento estacionamiento, EstadoEspacio estado);
}
