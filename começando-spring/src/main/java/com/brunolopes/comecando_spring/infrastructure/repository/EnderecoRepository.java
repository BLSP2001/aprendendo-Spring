package com.brunolopes.comecando_spring.infrastructure.repository;

import com.brunolopes.comecando_spring.infrastructure.entity.Endereco;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
