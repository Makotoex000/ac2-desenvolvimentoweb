package com.example.sistema_controle_projetos.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.example.sistema_controle_projetos.models.Projeto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosProjetoDTO {
    private Integer id;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private List<DadosFuncionarioDTO> funcionarios;

    public static DadosProjetoDTO fromEntity(Projeto projeto) {
        return DadosProjetoDTO.builder()
                .id(projeto.getId())
                .descricao(projeto.getDescricao())
                .dataInicio(projeto.getDataInicio())
                .dataFim(projeto.getDataFim())
                .funcionarios(projeto.getFuncionarios().stream()
                        .map(DadosFuncionarioDTO::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}
