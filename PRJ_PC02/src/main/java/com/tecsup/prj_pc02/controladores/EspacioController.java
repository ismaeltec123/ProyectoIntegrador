package com.tecsup.prj_pc02.controladores;

import com.tecsup.prj_pc02.modelo.entidades.Espacio;
import com.tecsup.prj_pc02.servicios.EspacioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/espacios")
public class EspacioController {

    @Autowired
    private EspacioService espacioService;

    // Listar todos los espacios
    @GetMapping
    public List<Espacio> listarEspacios() {
        return espacioService.listar();
    }

    // Crear un nuevo espacio
    @PostMapping
    public Espacio crearEspacio(@RequestBody Espacio espacio) {
        return espacioService.grabar(espacio);
    }

    // Buscar espacio por ID
    @GetMapping("/{id}")
    public Espacio obtenerEspacio(@PathVariable Integer id) {
        return espacioService.buscar(id);
    }

    // Actualizar espacio
    @PutMapping("/{id}")
    public Espacio actualizarEspacio(@PathVariable Integer id, @RequestBody Espacio espacio) {
        espacio.setId(id);
        return espacioService.grabar(espacio);
    }

    // Eliminar espacio
    @DeleteMapping("/{id}")
    public void eliminarEspacio(@PathVariable Integer id) {
        espacioService.eliminar(id);
    }
}
