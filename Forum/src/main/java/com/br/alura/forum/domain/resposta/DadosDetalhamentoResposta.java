package com.br.alura.forum.domain.resposta;

import java.util.ArrayList;
import java.util.List;

public record DadosDetalhamentoResposta(Long id, String mensagem, String topicoNome, String nomeAutor, Long idAutor, Boolean solucao) {
    public static List<DadosDetalhamentoResposta> fromRespostas(List<Resposta> respostas) {
        List<DadosDetalhamentoResposta> detalhamentoRespostas = new ArrayList<>();

        for (Resposta resposta : respostas) {
            DadosDetalhamentoResposta detalhamentoResposta = new DadosDetalhamentoResposta(
                    resposta.getId(),
                    resposta.getMensagem(),
                    resposta.getTopico().getTitulo(),
                    resposta.getAutor().getNome(),
                    resposta.getAutor().getId(),
                    resposta.getSolucao()
            );

            detalhamentoRespostas.add(detalhamentoResposta);
        }

        return detalhamentoRespostas;
    }
}


