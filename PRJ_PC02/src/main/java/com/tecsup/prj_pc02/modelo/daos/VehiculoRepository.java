package com.tecsup.prj_pc02.modelo.daos;

import com.tecsup.prj_pc02.modelo.entidades.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {
}
