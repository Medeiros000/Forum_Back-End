package com.br.alura.forum.domain.resposta;

import java.util.ArrayList;
import java.util.List;

public record DadosListagemRespostaPorUsuario(String topico_titulo, String resposta_mensagem, String autor_nome, Boolean resposta_solucao, Long resposta_id, Long autor_id) {
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