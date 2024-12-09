package com.tecsup.prj_pc02.controladores.api;

import com.tecsup.prj_pc02.modelo.entidades.Usuario;
import com.tecsup.prj_pc02.servicios.AuthService;
import com.tecsup.prj_pc02.modelo.dto.OAuthRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oauth")
public class OAuthController {

    @Autowired
    private AuthService authService;

    /**
     * Registra un usuario mediante credenciales de OAuth.
     *
     * @param oAuthRequest Objeto que contiene los datos de OAuth (email, proveedor, oauthId)
     * @return ResponseEntity con el usuario registrado
     */
    @PostMapping("/register")
    public ResponseEntity<?> registrarOAuth(@RequestBody OAuthRequestDTO oAuthRequest) {
        try {
            Usuario usuario = authService.registrarOAuth(
                    oAuthRequest.getEmail(),
                    oAuthRequest.getOauthProvider(),
                    oAuthRequest.getOauthId(),
                    oAuthRequest.getNombre()
            );
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar el usuario: " + e.getMessage());
        }
    }


}
