package com.example.sistema_controle_projetos.controllers;

import org.springframework.http.HttpStatus;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.sistema_controle_projetos.dto.DadosProjetoDTO;
import com.example.sistema_controle_projetos.dto.ProjetoDTO;
import com.example.sistema_controle_projetos.service.ProjetoService;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    private final ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody ProjetoDTO projetoDTO) {
        projetoService.adicionar(projetoDTO);
    }

    @GetMapping
    public List<DadosProjetoDTO> listarProjetos() {
        return projetoService.listarProjetos();
    }

    @GetMapping("/{id}")
    public DadosProjetoDTO buscarProjetoPorId(@PathVariable Integer id) {
        return projetoService.buscarProjetoPorId(id);
    }

    @PostMapping("/{idProjeto}/funcionarios/{idFuncionario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vincularFuncionario(@PathVariable Integer idProjeto, @PathVariable Integer idFuncionario) {
        projetoService.vincularFuncionario(idProjeto, idFuncionario);
    }
}
