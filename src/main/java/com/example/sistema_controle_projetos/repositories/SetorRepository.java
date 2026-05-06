package com.example.sistema_controle_projetos.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.sistema_controle_projetos.models.Setor;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Integer> {

    @Query("SELECT s FROM Setor s LEFT JOIN FETCH s.funcionarios")
    List<Setor> findAllWithFuncionarios();

    @Query("SELECT s FROM Setor s LEFT JOIN FETCH s.funcionarios WHERE s.id = :id")
    Optional<Setor> findByIdWithFuncionarios(@Param("id") Integer id);
}
