package com.tecsup.prj_pc02.controladores.api;

import com.tecsup.prj_pc02.modelo.dto.PreferenciaDTO;
import com.tecsup.prj_pc02.modelo.dto.QrUpdateDTO;
import com.tecsup.prj_pc02.modelo.dto.UsuarioDTO;
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
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
        Usuario usuario = usuarioService.buscar(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario); // Devuelve la entidad directamente
        }
        return ResponseEntity.notFound().build();
    }


    // Actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(
            @PathVariable Integer id,
            @RequestBody UsuarioDTO usuarioDTO) {
        try {
            Usuario usuarioActualizado = usuarioService.actualizarUsuario(
                    id,
                    usuarioDTO.getDni(),
                    usuarioDTO.getCodigoEstudiante(),
                    usuarioDTO.getNombre() // Puede ser null
            );

            UsuarioDTO respuesta = new UsuarioDTO(
                    usuarioActualizado.getDni(),
                    usuarioActualizado.getCodigoEstudiante(),
                    usuarioActualizado.getNombre()
            );

            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
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
    @PutMapping("/{userId}/preferencia")
    public ResponseEntity<?> actualizarPreferencia(
            @PathVariable Integer userId,
            @RequestBody PreferenciaDTO preferenciaDTO) {
        try {
            usuarioService.actualizarPreferencia(userId, preferenciaDTO.getPreferenciaEstacionamiento());
            return ResponseEntity.ok("Preferencia actualizada correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar la preferencia: " + e.getMessage());
        }
    }
    @PutMapping("/{id}/qr")
    public ResponseEntity<Usuario> actualizarQR(
            @PathVariable Integer id,
            @RequestBody QrUpdateDTO qrUpdateDTO) {
        try {
            Usuario usuarioActualizado = usuarioService.actualizarQR(id, qrUpdateDTO.getQr());
            return ResponseEntity.ok(usuarioActualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
