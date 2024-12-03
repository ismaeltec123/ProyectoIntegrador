package com.tecsup.prj_pc02.servicios;

import com.tecsup.prj_pc02.modelo.entidades.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario grabar(Usuario usuario);
    void eliminar(Integer id);
    Usuario buscar(Integer id);
    List<Usuario> listar();
}
