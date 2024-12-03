package com.tecsup.prj_pc02.modelo.daos;

import com.tecsup.prj_pc02.modelo.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Métodos adicionales personalizados si son necesarios
}
