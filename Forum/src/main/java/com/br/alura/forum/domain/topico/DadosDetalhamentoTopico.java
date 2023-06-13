package com.br.alura.forum.domain.topico;

import com.br.alura.forum.domain.resposta.Resposta;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(Long id, String titulo, String mensagem, LocalDateTime data , String nomeAutor, String nomeCurso, Resposta resposta){
    public DadosDetalhamentoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao() ,topico.getAutor().getNome(), topico.getCurso().getNome(), (Resposta) topico.getRespostas());
    }

}
