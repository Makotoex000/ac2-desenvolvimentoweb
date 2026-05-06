package com.example.sistema_controle_projetos.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.example.sistema_controle_projetos.models.Setor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosSetorDTO {
    private Integer id;
    private String nome;
    private List<DadosFuncionarioDTO> funcionarios;

    public static DadosSetorDTO fromEntity(Setor setor) {
        return DadosSetorDTO.builder()
                .id(setor.getId())
                .nome(setor.getNome())
                .funcionarios(setor.getFuncionarios().stream()
                        .map(DadosFuncionarioDTO::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}
