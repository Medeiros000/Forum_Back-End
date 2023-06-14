package com.br.alura.forum.domain.resposta;

import java.util.ArrayList;
import java.util.List;

public record DadosDetalhamentoResposta(Long resposta_id, String resposta_mensagem, String topico_titulo, String autor_nome, Long autor_id, Boolean resposta_solucao) {
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


