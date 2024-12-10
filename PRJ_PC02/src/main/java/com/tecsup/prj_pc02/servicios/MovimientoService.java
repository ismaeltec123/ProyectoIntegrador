package com.tecsup.prj_pc02.servicios;

import com.tecsup.prj_pc02.modelo.daos.MovimientoRepository;
import com.tecsup.prj_pc02.modelo.dto.AsignacionMovimientoDTO;
import com.tecsup.prj_pc02.modelo.dto.LiberacionMovimientoDTO;
import com.tecsup.prj_pc02.modelo.entidades.Espacio;
import com.tecsup.prj_pc02.modelo.entidades.Estacionamiento;
import com.tecsup.prj_pc02.modelo.entidades.Movimiento;
import com.tecsup.prj_pc02.modelo.entidades.Usuario;
import com.tecsup.prj_pc02.modelo.entidades.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private EstacionamientoService estacionamientoService;

    @Autowired
    private EspacioService espacioService;

    /**
     * Crear un nuevo movimiento al asignar un espacio.
     *
     * @param dto DTO con los datos necesarios para el movimiento.
     * @return Movimiento creado.
     */
    public Movimiento crearMovimiento(AsignacionMovimientoDTO dto) {
        Usuario usuario = usuarioService.buscar(dto.getFk_id_usuario());
        Vehiculo vehiculo = vehiculoService.buscar(dto.getFk_id_vehiculo());
        Estacionamiento estacionamiento = estacionamientoService.buscar(dto.getFk_id_estacionamiento());
        Espacio espacio = espacioService.buscar(dto.getFk_id_espacio());

        Movimiento movimiento = new Movimiento();
        movimiento.setUsuario(usuario);
        movimiento.setVehiculo(vehiculo);
        movimiento.setEstacionamiento(estacionamiento);
        movimiento.setEspacio(espacio);
        movimiento.setFechaHoraEntrada(dto.getFecha_hora_entrada());

        // Guardar el nuevo movimiento en la base de datos
        return movimientoRepository.save(movimiento);
    }

    /**
     * Obtener un movimiento activo asociado a un espacio.
     *
     * @param espacioId ID del espacio.
     * @return Movimiento activo, si existe.
     */
    public Optional<Movimiento> obtenerMovimientoPorEspacioYActivo(Integer espacioId) {
        return Optional.ofNullable(movimientoRepository.findByEspacioIdAndFechaHoraSalidaIsNull(espacioId));
    }

    /**
     * Liberar un movimiento al registrar la fecha de salida.
     *
     * @param dto DTO con los datos necesarios para liberar el movimiento.
     * @return Movimiento actualizado con la fecha de salida.
     */
    public Movimiento liberarMovimiento(LiberacionMovimientoDTO dto) {
        Optional<Movimiento> movimientoOpt = movimientoRepository.findById(dto.getId_movimiento());
        if (movimientoOpt.isEmpty()) {
            throw new IllegalArgumentException("Movimiento no encontrado");
        }

        Movimiento movimiento = movimientoOpt.get();
        movimiento.setFechaHoraSalida(dto.getFecha_hora_salida());
        return movimientoRepository.save(movimiento);
    }
}
