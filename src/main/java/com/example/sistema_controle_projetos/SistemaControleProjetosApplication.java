package com.example.sistema_controle_projetos;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.sistema_controle_projetos.models.Funcionario;
import com.example.sistema_controle_projetos.models.Projeto;
import com.example.sistema_controle_projetos.models.Setor;
import com.example.sistema_controle_projetos.repositories.FuncionarioRepository;
import com.example.sistema_controle_projetos.repositories.ProjetoRepository;
import com.example.sistema_controle_projetos.repositories.SetorRepository;

@SpringBootApplication
public class SistemaControleProjetosApplication {

	@Bean
	public CommandLineRunner init(
			@Autowired SetorRepository setorRepository,
			@Autowired ProjetoRepository projetoRepository,
			@Autowired FuncionarioRepository funcionarioRepository) {

		return args -> {
			System.out.println("*** INSERINDO SETORES ***");

			Setor setor1 = setorRepository.save(new Setor(null, "Financeiro", null));
			Setor setor2 = setorRepository.save(new Setor(null, "Recursos Humanos", null));
			Setor setor3 = setorRepository.save(new Setor(null, "Desenvolvimento", null));

			System.out.println("*** INSERINDO PROJETOS ***");
			Projeto projeto1 = projetoRepository.save(new Projeto(null, "Projeto Alpha", java.time.LocalDate.of(2026, 6, 1), java.time.LocalDate.of(2026, 8, 30), new ArrayList<>()));
			Projeto projeto2 = projetoRepository.save(new Projeto(null, "Projeto Beta", java.time.LocalDate.of(2026, 7, 10), java.time.LocalDate.of(2026, 9, 20), new ArrayList<>()));
			Projeto projeto3 = projetoRepository.save(new Projeto(null, "Projeto Gamma", java.time.LocalDate.of(2026, 8, 15), java.time.LocalDate.of(2026, 11, 30), new ArrayList<>()));

			System.out.println("*** INSERINDO FUNCIONÁRIOS ***");
			Funcionario funcionario1 = funcionarioRepository.save(new Funcionario(null, "Ana Silva", setor1, new ArrayList<>()));
			Funcionario funcionario2 = funcionarioRepository.save(new Funcionario(null, "Bruno Costa", setor2, new ArrayList<>()));
			Funcionario funcionario3 = funcionarioRepository.save(new Funcionario(null, "Carla Souza", setor3, new ArrayList<>()));

			System.out.println("*** VINCULANDO FUNCIONÁRIOS A PROJETOS ***");
			projeto1.getFuncionarios().add(funcionario1);
			projeto1.getFuncionarios().add(funcionario3);
			projeto2.getFuncionarios().add(funcionario2);
			projeto3.getFuncionarios().add(funcionario3);

			funcionario1.getProjetos().add(projeto1);
			funcionario2.getProjetos().add(projeto2);
			funcionario3.getProjetos().add(projeto1);
			funcionario3.getProjetos().add(projeto3);

			projetoRepository.save(projeto1);
			projetoRepository.save(projeto2);
			projetoRepository.save(projeto3);

			System.out.println("*** DADOS INICIAIS INSERIDOS ***");
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SistemaControleProjetosApplication.class, args);
	}

}
