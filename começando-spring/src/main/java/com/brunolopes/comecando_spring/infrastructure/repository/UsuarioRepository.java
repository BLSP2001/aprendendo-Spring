package com.brunolopes.comecando_spring.infrastructure.repository;

import com.brunolopes.comecando_spring.infrastructure.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    // Optional = tratar melhor os retornos quando forem nulos
    Optional<Usuario> findByEmail(String email);

    @Transactional//garante integridade às operações no banco de dados
    @Modifying
    void deleteByEmail(String email);
}
