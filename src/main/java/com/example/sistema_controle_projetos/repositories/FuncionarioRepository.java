package com.example.sistema_controle_projetos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.sistema_controle_projetos.models.Funcionario;
import com.example.sistema_controle_projetos.models.Projeto;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    @Query("SELECT DISTINCT p FROM Projeto p LEFT JOIN FETCH p.funcionarios f WHERE f.id = :funcionarioId")
    List<Projeto> findProjetosByFuncionarioId(@Param("funcionarioId") Integer funcionarioId);
}
