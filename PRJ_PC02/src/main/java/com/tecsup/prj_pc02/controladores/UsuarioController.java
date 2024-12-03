package com.tecsup.prj_pc02.controladores;

import com.tecsup.prj_pc02.modelo.entidades.Usuario;
import com.tecsup.prj_pc02.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) {
        return usuarioService.grabar(usuario);
    }

    @GetMapping("/{id}")
    public Usuario buscar(@PathVariable Integer id) {
        return usuarioService.buscar(id);
    }

    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        return usuarioService.grabar(usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        usuarioService.eliminar(id);
    }
}
