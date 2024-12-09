package com.tecsup.prj_pc02.controladores.api;

import com.tecsup.prj_pc02.modelo.entidades.Usuario;
import com.tecsup.prj_pc02.modelo.entidades.Vehiculo;
import com.tecsup.prj_pc02.servicios.UsuarioService;
import com.tecsup.prj_pc02.servicios.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoApiController {

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private UsuarioService usuarioService;

    // Listar todos los vehículos
    @GetMapping
    public List<Vehiculo> listar() {
        return vehiculoService.listar();
    }

    // Crear un vehículo con usuario vinculado
    @PostMapping
    public Vehiculo crearVehiculo(@RequestBody Map<String, Object> request) {
        // Validar que el ID del usuario está presente
        if (!request.containsKey("usuarioId")) {
            throw new IllegalArgumentException("El ID del usuario es obligatorio.");
        }

        Integer usuarioId = (Integer) request.get("usuarioId");
        Usuario usuario = usuarioService.buscar(usuarioId);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no encontrado con el ID proporcionado.");
        }

        // Crear un nuevo vehículo
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPlaca((String) request.get("placa"));
        vehiculo.setMarca((String) request.get("marca"));
        vehiculo.setModelo((String) request.get("modelo"));
        vehiculo.setColor((String) request.get("color"));
        vehiculo.setAnio((Integer) request.get("anio"));
        vehiculo.setUsuario(usuario);

        return vehiculoService.grabar(vehiculo);
    }

    // Buscar un vehículo por ID
    @GetMapping("/{id}")
    public Vehiculo buscar(@PathVariable Integer id) {
        return vehiculoService.buscar(id);
    }

    // Actualizar un vehículo
    @PutMapping("/{id}")
    public Vehiculo actualizar(@PathVariable Integer id, @RequestBody Map<String, Object> request) {
        Vehiculo vehiculoExistente = vehiculoService.buscar(id);

        if (vehiculoExistente == null) {
            throw new IllegalArgumentException("Vehículo no encontrado con el ID proporcionado.");
        }

        if (request.containsKey("usuarioId")) {
            Integer usuarioId = (Integer) request.get("usuarioId");
            Usuario usuario = usuarioService.buscar(usuarioId);
            if (usuario == null) {
                throw new IllegalArgumentException("Usuario no encontrado con el ID proporcionado.");
            }
            vehiculoExistente.setUsuario(usuario);
        }

        if (request.containsKey("placa")) {
            vehiculoExistente.setPlaca((String) request.get("placa"));
        }
        if (request.containsKey("marca")) {
            vehiculoExistente.setMarca((String) request.get("marca"));
        }
        if (request.containsKey("modelo")) {
            vehiculoExistente.setModelo((String) request.get("modelo"));
        }
        if (request.containsKey("color")) {
            vehiculoExistente.setColor((String) request.get("color"));
        }
        if (request.containsKey("anio")) {
            vehiculoExistente.setAnio((Integer) request.get("anio"));
        }

        return vehiculoService.grabar(vehiculoExistente);
    }

    // Eliminar un vehículo por ID
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        Vehiculo vehiculo = vehiculoService.buscar(id);
        if (vehiculo == null) {
            throw new IllegalArgumentException("Vehículo no encontrado con el ID proporcionado.");
        }
        vehiculoService.eliminar(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Vehiculo> listarPorUsuario(@PathVariable Integer usuarioId) {
        Usuario usuario = usuarioService.buscar(usuarioId);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no encontrado con el ID proporcionado.");
        }
        return vehiculoService.listarPorUsuario(usuario);
    }
}
