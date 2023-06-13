package com.br.alura.forum.domain.curso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String categoria;

	public Curso(DadosCadastroCurso dados) {
		this.nome = dados.nome();
		this.categoria = dados.categoria();
	}

	public Curso(Long aLong) {
		this.id = aLong;
	}

	public void atualizarInformacoesCurso(DadosAtualizacaoCurso dados) {
		if (dados.nome() != null){
			this.nome = dados.nome();
		}
		if (dados.categoria() != null){
			this.categoria = dados.categoria();
		}

	}
}
