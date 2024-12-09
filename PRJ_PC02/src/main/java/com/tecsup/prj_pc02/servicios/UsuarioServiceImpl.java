package com.tecsup.prj_pc02.servicios;

import com.tecsup.prj_pc02.modelo.daos.UsuarioRepository;
import com.tecsup.prj_pc02.modelo.dto.UsuarioDTO;
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
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public void registrarCredencialesOAuth(String email, String oauthProvider, String oauthId, Integer userId) {
        usuarioRepository.registrarCredenciales(email, oauthProvider, oauthId, userId);
    }

    @Override
    @Transactional
    public void actualizarPreferenciaEstacionamiento(Integer idUsuario, Integer idEstacionamiento) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
        usuario.setPreferenciaEstacionamiento(idEstacionamiento);
        usuarioRepository.save(usuario);
    }
    @Transactional
    public void actualizarPreferencia(Integer userId, Integer preferenciaEstacionamiento) {
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));
        usuario.setPreferenciaEstacionamiento(preferenciaEstacionamiento);
        usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Integer id, String dni, String codigoEstudiante, String nombre) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Actualiza solo los campos enviados
        if (dni != null) usuario.setDni(dni);
        if (codigoEstudiante != null) usuario.setCodigoEstudiante(codigoEstudiante);
        if (nombre != null) {
            usuario.setNombre(nombre);
        } else {
            // Usa el nombre actual si no se envía
            usuario.setNombre(usuario.getNombre());
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Usuario actualizarQR(Integer id, String qr) {
        Usuario usuario = buscar(id);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        usuario.setQr(qr);
        return usuarioRepository.save(usuario);
    }



}
