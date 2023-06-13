package com.br.alura.forum.domain.topico;

import com.br.alura.forum.domain.resposta.Resposta;
import com.br.alura.forum.domain.curso.Curso;
import com.br.alura.forum.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Topico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String titulo;
	@Column(unique = true, length = 1000)
	private String mensagem;
	private LocalDateTime dataCriacao = LocalDateTime.now();
	@Enumerated(EnumType.STRING)
	private StatusTopico status = StatusTopico.NAO_RESPONDIDO;
	@ManyToOne
	@JoinColumn(name = "autor_id")
	private Usuario autor;
	@ManyToOne
	private Curso curso;
	@OneToMany(mappedBy = "topico")
	private List<Resposta> respostas = new ArrayList<>();

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}

	public Topico(DadosCadastroTopico dados) {
		this.titulo = dados.titulo();
		this.mensagem = dados.mensagem();
		dataCriacao = LocalDateTime.now();
		this.curso = new Curso(dados.idCurso());
		this.autor = new Usuario(dados.idAutor());
	}

	@Override
	public String toString() {
		return "Topico{" +
				"id=" + id +
				", titulo='" + titulo + '\'' +
				", mensagem='" + mensagem + '\'' +
				", dataCriacao=" + dataCriacao +
				", status=" + status +
				", autor=" + autor +
				", curso=" + curso +
				", respostas=" + respostas +
				'}';
	}
}
