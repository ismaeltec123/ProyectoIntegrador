package com.tecsup.prj_pc02.controladores.api;

import com.tecsup.prj_pc02.modelo.dto.AsignacionMovimientoDTO;
import com.tecsup.prj_pc02.modelo.dto.LiberacionMovimientoDTO;
import com.tecsup.prj_pc02.modelo.dto.MovimientoResponseDTO;
import com.tecsup.prj_pc02.modelo.entidades.Movimiento;
import com.tecsup.prj_pc02.servicios.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoApiController {

    @Autowired
    private MovimientoService movimientoService;

    /**
     * Crear un nuevo movimiento al asignar un espacio.
     *
     * @param dto Objeto DTO con los datos necesarios para la asignación.
     * @return Movimiento creado.
     */
    @PostMapping("/asignar")
    public ResponseEntity<Movimiento> asignarMovimiento(@RequestBody AsignacionMovimientoDTO dto) {
        try {
            Movimiento nuevoMovimiento = movimientoService.crearMovimiento(dto);
            return ResponseEntity.ok(nuevoMovimiento);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Cerrar un movimiento al liberar un espacio.
     *
     * @param dto Objeto DTO con los datos necesarios para la liberación.
     * @return Movimiento actualizado con la fecha de salida.
     */
    @PutMapping("/liberar")
    public ResponseEntity<?> liberarMovimiento(@RequestBody LiberacionMovimientoDTO dto) {
        try {
            Movimiento movimiento = movimientoService.liberarMovimiento(dto);
            if (movimiento == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movimiento no encontrado");
            }
            return ResponseEntity.ok("Movimiento liberado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al liberar el movimiento: " + e.getMessage());
        }
    }
}
