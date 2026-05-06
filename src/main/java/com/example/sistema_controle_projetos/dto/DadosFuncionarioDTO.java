package com.example.sistema_controle_projetos.dto;

import com.example.sistema_controle_projetos.models.Funcionario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosFuncionarioDTO {
    private Integer id;
    private String nome;

    public static DadosFuncionarioDTO fromEntity(Funcionario funcionario) {
        return DadosFuncionarioDTO.builder()
                .id(funcionario.getId())
                .nome(funcionario.getNome())
                .build();
    }
}
