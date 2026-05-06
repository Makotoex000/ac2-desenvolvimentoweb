package com.example.sistema_controle_projetos.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.sistema_controle_projetos.dto.DadosSetorDTO;
import com.example.sistema_controle_projetos.dto.SetorDTO;
import com.example.sistema_controle_projetos.models.Setor;
import com.example.sistema_controle_projetos.repositories.SetorRepository;

@Service
public class SetorService {

    private final SetorRepository setorRepository;

    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    public void adicionar(SetorDTO dto) {
        if (dto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados do setor são obrigatórios");
        }
        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome do setor é obrigatório");
        }

        Setor setor = Setor.builder()
                .nome(dto.getNome().trim())
                .build();

        setorRepository.save(setor);
    }

    public DadosSetorDTO buscarSetorPorId(Integer idSetor) {
        Setor setor = setorRepository.findByIdWithFuncionarios(idSetor)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Setor não encontrado"));
        return DadosSetorDTO.fromEntity(setor);
    }
}
