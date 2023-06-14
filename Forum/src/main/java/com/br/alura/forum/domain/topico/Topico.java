package com.br.alura.forum.domain.topico;

import com.br.alura.forum.domain.resposta.Resposta;
import com.br.alura.forum.domain.curso.Curso;
import com.br.alura.forum.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
		this.titulo = dados.topico_titulo();
		this.mensagem = dados.topico_mensagem();
		dataCriacao = LocalDateTime.now();
		this.curso = new Curso(dados.curso_id());
		this.autor = new Usuario(dados.autor_id());
	}

    public void atualizarInformacoesTopico(DadosAtualizacaoTopico dados) {
		this.titulo = dados.topico_titulo();
		this.mensagem = dados.topico_mensagem();
		this.status = dados.topico_status();
		this.curso = new Curso(dados.curso_id());
    }

}
