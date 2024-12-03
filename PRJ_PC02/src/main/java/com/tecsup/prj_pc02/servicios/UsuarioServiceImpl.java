package com.tecsup.prj_pc02.servicios;

import com.tecsup.prj_pc02.modelo.daos.UsuarioRepository;
import com.tecsup.prj_pc02.modelo.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public Usuario grabar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscar(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }
}
