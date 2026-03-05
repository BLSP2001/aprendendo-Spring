package com.brunolopes.comecando_spring.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.brunolopes.comecando_spring.infrastructure.entity.Telefone;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {


}
