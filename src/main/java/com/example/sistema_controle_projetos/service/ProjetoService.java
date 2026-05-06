package com.example.sistema_controle_projetos.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.sistema_controle_projetos.dto.DadosProjetoDTO;
import com.example.sistema_controle_projetos.dto.ProjetoDTO;
import com.example.sistema_controle_projetos.models.Funcionario;
import com.example.sistema_controle_projetos.models.Projeto;
import com.example.sistema_controle_projetos.repositories.FuncionarioRepository;
import com.example.sistema_controle_projetos.repositories.ProjetoRepository;

@Service
public class ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final FuncionarioRepository funcionarioRepository;

    public ProjetoService(ProjetoRepository projetoRepository, FuncionarioRepository funcionarioRepository) {
        this.projetoRepository = projetoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public void adicionar(ProjetoDTO dto) {
        if (dto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados do projeto são obrigatórios");
        }
        if (dto.getDescricao() == null || dto.getDescricao().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Descrição do projeto é obrigatória");
        }
        if (dto.getDataInicio() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de início do projeto é obrigatória");
        }
        LocalDate dataInicio = dto.getDataInicio();
        LocalDate dataFim = dto.getDataFim();
        if (dataFim != null && dataFim.isBefore(dataInicio)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de término não pode ser anterior à data de início");
        }

        Projeto projeto = Projeto.builder()
                .descricao(dto.getDescricao().trim())
                .dataInicio(dataInicio)
                .dataFim(dataFim)
                .build();

        projetoRepository.save(projeto);
    }

    public List<DadosProjetoDTO> listarProjetos() {
        return projetoRepository.findAll().stream()
                .map(DadosProjetoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public DadosProjetoDTO buscarProjetoPorId(Integer id) {
        Projeto projeto = projetoRepository.findByIdWithFuncionarios(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto não encontrado"));
        return DadosProjetoDTO.fromEntity(projeto);
    }

    @Transactional
    public void vincularFuncionario(Integer idProjeto, Integer idFuncionario) {
        Projeto projeto = projetoRepository.findByIdWithFuncionarios(idProjeto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto não encontrado"));

        Funcionario funcionario = funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado"));

        if (!projeto.getFuncionarios().contains(funcionario)) {
            projeto.getFuncionarios().add(funcionario);
        }
        if (!funcionario.getProjetos().contains(projeto)) {
            funcionario.getProjetos().add(projeto);
        }

        projetoRepository.save(projeto);
    }
}
