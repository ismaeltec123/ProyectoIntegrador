package com.tecsup.prj_pc02.modelo.daos;

import com.tecsup.prj_pc02.modelo.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT c.usuario FROM CredencialOAuth c WHERE c.email = :email")
    Usuario findByEmail(@Param("email") String email);

    @Modifying
    @Query(value = "INSERT INTO credenciales_oauth (email, oauth_provider, oauth_id, fk_id_usuario) VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
    void registrarCredenciales(String email, String oauthProvider, String oauthId, Integer userId);
}
