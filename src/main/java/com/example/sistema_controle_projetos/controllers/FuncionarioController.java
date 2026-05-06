package com.example.sistema_controle_projetos.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.sistema_controle_projetos.dto.DadosProjetoDTO;
import com.example.sistema_controle_projetos.dto.FuncionarioDTO;
import com.example.sistema_controle_projetos.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody FuncionarioDTO funcionarioDTO) {
        funcionarioService.adicionar(funcionarioDTO);
    }

    @GetMapping("/{idFuncionario}/projetos")
    public List<DadosProjetoDTO> buscarProjetos(@PathVariable Integer idFuncionario) {
        return funcionarioService.buscarProjetos(idFuncionario);
    }
}
