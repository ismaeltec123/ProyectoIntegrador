package com.tecsup.prj_pc02.modelo.daos;



import com.tecsup.prj_pc02.modelo.entidades.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {
    Movimiento findByEspacioIdAndFechaHoraSalidaIsNull(Integer espacioId);
}
