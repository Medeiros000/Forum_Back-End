package com.br.alura.forum.domain.resposta;

import com.br.alura.forum.domain.topico.Topico;
import com.br.alura.forum.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respostas")
@Entity(name = "Resposta")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Resposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String mensagem;
	@ManyToOne
	@JoinColumn(name = "topico_id")
	private Topico topico;
	private LocalDateTime dataCriacao = LocalDateTime.now();
	@ManyToOne
	private Usuario autor;
	private Boolean solucao = false;

	public Resposta(DadosCadastroResposta dados, Topico topico) {
		this.mensagem = dados.resposta_mensagem();
		this.autor = new Usuario(dados.resposta_autor_id());
		this.topico = topico;
	}

	public void atualizarInformacoesResposta(DadosAtualizacaoResposta dados, Topico topico) {
		this.id = dados.resposta_id();
		this.mensagem = dados.resposta_mensagem();
		this.topico = topico;
		this.solucao = dados.resposta_solucao();
	}
}
