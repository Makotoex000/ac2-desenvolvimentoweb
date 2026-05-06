package com.example.sistema_controle_projetos.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.sistema_controle_projetos.models.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {

    @Query("SELECT p FROM Projeto p LEFT JOIN FETCH p.funcionarios WHERE p.id = :id")
    Optional<Projeto> findByIdWithFuncionarios(@Param("id") Integer id);

    @Query("SELECT DISTINCT p FROM Projeto p LEFT JOIN FETCH p.funcionarios")
    List<Projeto> findAllWithFuncionarios();

    List<Projeto> findByDataInicioBetween(LocalDate dataInicio, LocalDate dataFim);
}
