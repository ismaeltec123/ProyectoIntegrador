package com.tecsup.prj_pc02.controladores;

import com.tecsup.prj_pc02.modelo.entidades.Estacionamiento;
import com.tecsup.prj_pc02.servicios.EstacionamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estacionamientos")
public class EstacionamientoController {

    @Autowired
    private EstacionamientoService estacionamientoService;

    // Listar todos los estacionamientos
    @GetMapping
    public List<Estacionamiento> listarEstacionamientos() {
        return estacionamientoService.listar();
    }

    // Crear un nuevo estacionamiento
    @PostMapping
    public Estacionamiento crearEstacionamiento(@RequestBody Estacionamiento estacionamiento) {
        return estacionamientoService.grabar(estacionamiento);
    }

    // Buscar estacionamiento por ID
    @GetMapping("/{id}")
    public Estacionamiento obtenerEstacionamiento(@PathVariable Integer id) {
        return estacionamientoService.buscar(id);
    }

    // Actualizar estacionamiento
    @PutMapping("/{id}")
    public Estacionamiento actualizarEstacionamiento(@PathVariable Integer id, @RequestBody Estacionamiento estacionamiento) {
        estacionamiento.setId(id);
        return estacionamientoService.grabar(estacionamiento);
    }

    // Eliminar estacionamiento
    @DeleteMapping("/{id}")
    public void eliminarEstacionamiento(@PathVariable Integer id) {
        estacionamientoService.eliminar(id);
    }
}
