package com.tecsup.prj_pc02.modelo.daos;

import com.tecsup.prj_pc02.modelo.entidades.Estacionamiento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EstacionamientoRepository extends CrudRepository<Estacionamiento, Integer> {

    @Query(value = """
        SELECT e.pk_id_estacionamiento AS id, 
               e.nombre AS nombre, 
               e.tipo AS tipo, 
               (e.capacidad - COUNT(CASE WHEN esp.estado = 'ocupado' THEN 1 ELSE NULL END)) AS capacidad
        FROM estacionamientos e
        LEFT JOIN espacios esp ON e.pk_id_estacionamiento = esp.fk_id_estacionamiento
        GROUP BY e.pk_id_estacionamiento, e.nombre, e.tipo, e.capacidad
        HAVING capacidad > 0
        """, nativeQuery = true)
    List<Object[]> findEstacionamientosConCapacidadDisponible();


}
