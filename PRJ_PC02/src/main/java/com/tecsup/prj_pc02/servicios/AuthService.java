package com.tecsup.prj_pc02.servicios;

import com.tecsup.prj_pc02.modelo.daos.CredencialOAuthRepository;
import com.tecsup.prj_pc02.modelo.entidades.CredencialOAuth;
import com.tecsup.prj_pc02.modelo.entidades.TipoUsuario;
import com.tecsup.prj_pc02.modelo.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CredencialOAuthRepository credencialOAuthRepository;

    public Usuario registrarOAuth(String email, String oauthProvider, String oauthId,String nombre) {
        // Verificar si ya existe una credencial para este email
        CredencialOAuth credencialExistente = credencialOAuthRepository.findByEmail(email);

        if (credencialExistente != null) {
            // Si ya existe, retornar el usuario asociado
            return credencialExistente.getUsuario();
        }

        // Registrar un nuevo usuario porque no existe
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre); // Personaliza según necesidad
        usuario.setDni("TEMP-"+ System.currentTimeMillis() % 100000 ); // Generar un DNI temporal único
        usuario.setTipoUsuario(TipoUsuario.externo); // Tipo de usuario por defecto
        usuario.setQr("QR-" + System.currentTimeMillis()); // Generar QR temporal

        // Guardar el usuario en la base de datos
        Usuario usuarioGuardado = usuarioService.grabar(usuario);

        // Registrar las credenciales OAuth
        CredencialOAuth credencial = new CredencialOAuth();
        credencial.setEmail(email);
        credencial.setOauthProvider(oauthProvider);
        credencial.setOauthId(oauthId);
        credencial.setUsuario(usuarioGuardado);

        // Guardar la credencial en la base de datos
        credencialOAuthRepository.save(credencial);

        // Retornar el nuevo usuario registrado
        return usuarioGuardado;
    }
}



