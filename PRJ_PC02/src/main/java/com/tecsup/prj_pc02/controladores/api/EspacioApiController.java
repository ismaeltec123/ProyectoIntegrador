package com.tecsup.prj_pc02.controladores.api;

import com.tecsup.prj_pc02.modelo.dto.AsignacionEspacioDTO;
import com.tecsup.prj_pc02.modelo.entidades.Espacio;
import com.tecsup.prj_pc02.modelo.entidades.Estacionamiento;
import com.tecsup.prj_pc02.modelo.entidades.EstadoEspacio;
import com.tecsup.prj_pc02.servicios.EspacioService;
import com.tecsup.prj_pc02.servicios.EstacionamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/espacios")
public class EspacioApiController {

    @Autowired
    private EspacioService espacioService;

    @Autowired
    private EstacionamientoService estacionamientoService;

    // Listar todos los espacios
    @GetMapping
    public List<Espacio> listar() {
        return espacioService.listar();
    }



    // Crear espacio con estacionamiento vinculado
    @PostMapping
    public Espacio crearEspacio(@RequestBody Map<String, Object> request) {
        // Validar que el ID del estacionamiento está presente
        if (!request.containsKey("estacionamientoId")) {
            throw new IllegalArgumentException("El ID del estacionamiento es obligatorio.");
        }

        Integer estacionamientoId = (Integer) request.get("estacionamientoId");
        Estacionamiento estacionamiento = estacionamientoService.buscar(estacionamientoId);

        if (estacionamiento == null) {
            throw new IllegalArgumentException("Estacionamiento no encontrado con el ID proporcionado.");
        }

        // Crear un nuevo espacio
        Espacio espacio = new Espacio();
        espacio.setCodigoEspacio((String) request.get("codigoEspacio"));
        espacio.setEstado(EstadoEspacio.valueOf((String) request.get("estado")));
        espacio.setEstacionamiento(estacionamiento);

        return espacioService.grabar(espacio);
    }

    // Buscar un espacio por ID
    @GetMapping("/{id}")
    public Espacio buscar(@PathVariable Integer id) {
        return espacioService.buscar(id);
    }

    // Actualizar un espacio
    @PutMapping("/{id}")
    public Espacio actualizar(@PathVariable Integer id, @RequestBody Espacio espacioActualizado) {
        Espacio espacio = espacioService.buscar(id);

        if (espacio != null) {
            espacio.setId(espacioActualizado.getId());
            espacio.setEstado(espacioActualizado.getEstado());
            return espacioService.grabar(espacio);
        }

        return null;
    }

    // Eliminar un espacio por ID
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        espacioService.eliminar(id);
    }

    @PostMapping("/asignar")
    public ResponseEntity<Espacio> asignarEspacio(@RequestBody AsignacionEspacioDTO dto) {
        Espacio espacioAsignado = espacioService.asignarEspacio(dto);
        return ResponseEntity.ok(espacioAsignado);
    }

    @PutMapping("/liberar/{id}")
    public ResponseEntity<Espacio> liberarEspacio(@PathVariable Integer id) {
        Espacio espacioLiberado = espacioService.liberarEspacio(id);
        return ResponseEntity.ok(espacioLiberado);
    }
}
