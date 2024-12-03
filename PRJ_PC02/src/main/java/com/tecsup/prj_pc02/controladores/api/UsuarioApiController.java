package com.tecsup.prj_pc02.controladores.api;

import com.tecsup.prj_pc02.modelo.entidades.Usuario;
import com.tecsup.prj_pc02.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioApiController {

    @Autowired
    private UsuarioService usuarioService;

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listar();
        return ResponseEntity.ok(usuarios);
    }

    // Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.grabar(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Integer id) {
        Usuario usuario = usuarioService.buscar(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    // Actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        Usuario usuarioExistente = usuarioService.buscar(id);
        if (usuarioExistente == null) {
            return ResponseEntity.notFound().build();
        }
        usuario.setId(id);
        Usuario usuarioActualizado = usuarioService.grabar(usuario);
        return ResponseEntity.ok(usuarioActualizado);
    }

    // Eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer id) {
        Usuario usuarioExistente = usuarioService.buscar(id);
        if (usuarioExistente == null) {
            return ResponseEntity.notFound().build();
        }
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
