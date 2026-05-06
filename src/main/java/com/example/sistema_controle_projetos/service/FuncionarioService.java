package com.example.sistema_controle_projetos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.sistema_controle_projetos.dto.DadosProjetoDTO;
import com.example.sistema_controle_projetos.dto.FuncionarioDTO;
import com.example.sistema_controle_projetos.models.Funcionario;
import com.example.sistema_controle_projetos.models.Setor;
import com.example.sistema_controle_projetos.repositories.FuncionarioRepository;
import com.example.sistema_controle_projetos.repositories.SetorRepository;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final SetorRepository setorRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, SetorRepository setorRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.setorRepository = setorRepository;
    }

    public void adicionar(FuncionarioDTO dto) {
        if (dto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados do funcionário são obrigatórios");
        }
        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome do funcionário é obrigatório");
        }

        Funcionario funcionario = Funcionario.builder()
                .nome(dto.getNome().trim())
                .build();

        if (dto.getSetorId() != null) {
            Setor setor = setorRepository.findById(dto.getSetorId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Setor não encontrado"));
            funcionario.setSetor(setor);
        }

        funcionarioRepository.save(funcionario);
    }

    public List<DadosProjetoDTO> buscarProjetos(Integer idFuncionario) {
        funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado"));

        return funcionarioRepository.findProjetosByFuncionarioId(idFuncionario).stream()
                .map(DadosProjetoDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
