package com.tecsup.prj_pc02.modelo.daos;

import com.tecsup.prj_pc02.modelo.entidades.CredencialOAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CredencialOAuthRepository extends JpaRepository<CredencialOAuth, Integer> {

    @Query("SELECT c FROM CredencialOAuth c WHERE c.email = :email")
    CredencialOAuth findByEmail(@Param("email") String email);
}
