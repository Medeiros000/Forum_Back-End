package com.br.alura.forum.domain.topico;

import com.br.alura.forum.domain.resposta.DadosDetalhamentoResposta;
import com.br.alura.forum.domain.resposta.Resposta;

import java.time.LocalDateTime;
import java.util.List;

public record DadosDetalhamentoTopico(Long id, String titulo, String mensagem, LocalDateTime data, String nomeAutor, String nomeCurso, List<DadosDetalhamentoResposta> respostas) {
    public static DadosDetalhamentoTopico fromTopicoETodasRespostas(Topico topico, List<Resposta> respostas) {
        List<DadosDetalhamentoResposta> detalhamentoRespostas = DadosDetalhamentoResposta.fromRespostas(respostas);

        return new DadosDetalhamentoTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getAutor().getNome(),
                topico.getCurso().getNome(),
                detalhamentoRespostas
        );
    }
}
