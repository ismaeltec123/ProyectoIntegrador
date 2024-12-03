package com.tecsup.prj_pc02.controladores.api;

import com.tecsup.prj_pc02.modelo.entidades.Estacionamiento;
import com.tecsup.prj_pc02.servicios.EstacionamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estacionamientos")
public class EstacionamientoApiController {

    @Autowired
    private EstacionamientoService estacionamientoService;

    // Listar todos los estacionamientos
    @GetMapping
    public List<Estacionamiento> listar() {
        return estacionamientoService.listar();
    }

    // Crear un nuevo estacionamiento
    @PostMapping
    public Estacionamiento crear(@RequestBody Estacionamiento estacionamiento) {
        return estacionamientoService.grabar(estacionamiento);
    }

    // Buscar un estacionamiento por ID
    @GetMapping("/{id}")
    public Estacionamiento buscar(@PathVariable Integer id) {
        return estacionamientoService.buscar(id);
    }

    // Actualizar un estacionamiento
    @PutMapping("/{id}")
    public Estacionamiento actualizar(@PathVariable Integer id, @RequestBody Estacionamiento estacionamientoActualizado) {
        Estacionamiento estacionamiento = estacionamientoService.buscar(id);

        if (estacionamiento != null) {
            estacionamiento.setNombre(estacionamientoActualizado.getNombre());
            estacionamiento.setTipo(estacionamientoActualizado.getTipo());
            estacionamiento.setCapacidad(estacionamientoActualizado.getCapacidad());
            return estacionamientoService.grabar(estacionamiento);
        }

        return null;
    }

    // Eliminar un estacionamiento por ID
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        estacionamientoService.eliminar(id);
    }
}
