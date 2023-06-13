package com.br.alura.forum.domain.topico;

import java.time.LocalDateTime;

public record DadosListagemTopico(Long id, String titulo, String mensagem, LocalDateTime data_criacao, Enum status, String nomeAutor, String nomeCurso) {

    public DadosListagemTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(),topico.getDataCriacao(), topico.getStatus() ,topico.getAutor().getNome(), topico.getCurso().getNome());
    }
}
