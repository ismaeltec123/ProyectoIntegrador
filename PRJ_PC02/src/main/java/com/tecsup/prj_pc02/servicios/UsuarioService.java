package com.tecsup.prj_pc02.servicios;

import com.tecsup.prj_pc02.modelo.dto.UsuarioDTO;
import com.tecsup.prj_pc02.modelo.entidades.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario grabar(Usuario usuario);
    void eliminar(Integer id);
    Usuario buscar(Integer id);
    List<Usuario> listar();
    Usuario buscarPorEmail(String email);
    void registrarCredencialesOAuth(String email, String oauthProvider, String oauthId, Integer userId);
    void actualizarPreferenciaEstacionamiento(Integer idUsuario, Integer idEstacionamiento);
    void actualizarPreferencia(Integer userId, Integer preferenciaEstacionamiento);

    Usuario actualizarUsuario(Integer id, String dni, String codigoEstudiante, String nombre);
    Usuario actualizarQR(Integer id, String qr);

}
