package com.tecsup.prj_pc02.controladores.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/oauth")
public class OAuthApiController {

    @GetMapping("/callback")
    public ResponseEntity<String> handleOAuthCallback(@RequestParam String code) {
        // Aquí manejas el código de OAuth devuelto por Google
        return ResponseEntity.ok("Callback recibido con código: " + code);
    }
}
