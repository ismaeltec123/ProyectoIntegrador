package com.tecsup.prj_pc02.modelo.daos;

import com.tecsup.prj_pc02.modelo.entidades.Estacionamiento;
import org.springframework.data.repository.CrudRepository;



public interface EstacionamientoRepository extends CrudRepository<Estacionamiento, Integer> {
}