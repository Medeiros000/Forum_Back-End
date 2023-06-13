package com.br.alura.forum.domain.resposta;

import com.br.alura.forum.domain.topico.DadosCadastroTopico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record DadosDetalhamentoResposta(Long id, String mensagem, String topicoNome, String nomeAutor, Long idAutor, Boolean solucao) {
    public static List<DadosDetalhamentoResposta> fromRespostas(List<Resposta> respostas) {
        List<DadosDetalhamentoResposta> detalhamentos = new ArrayList<>();

        for (Resposta resposta : respostas) {
            detalhamentos.add(new DadosDetalhamentoResposta(
                    resposta.getId(),
                    resposta.getMensagem(),
                    resposta.getTopico().getTitulo(),
                    resposta.getAutor().getNome(),
                    resposta.getAutor().getId(),
                    resposta.getSolucao()
            ));
        }

        return detalhamentos;
    }

    public DadosCadastroTopico idTopico() {
        return new DadosCadastroTopico(null, null, null, null);
    }

    public LocalDateTime dataCriacao() {
        return LocalDateTime.now();
    }
}

