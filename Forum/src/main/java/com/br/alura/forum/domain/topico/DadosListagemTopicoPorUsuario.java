package com.br.alura.forum.domain.topico;

import java.util.ArrayList;
import java.util.List;

public record DadosListagemTopicoPorUsuario(String categoria_nome, String topico_titulo, String topico_mensagem, Enum topico_status, String autor_nome, Long topico_id, Long autor_id) {
    public static List<DadosListagemTopicoPorUsuario> fromTopicos(List<Topico> topicos) {
        List<DadosListagemTopicoPorUsuario> listagemTopicos = new ArrayList<>();
        for (Topico topico : topicos) {
            DadosListagemTopicoPorUsuario listagemTopico = new DadosListagemTopicoPorUsuario(
                    topico.getCurso().getNome(),
                    topico.getTitulo(),
                    topico.getMensagem(),
                    topico.getStatus(),
                    topico.getAutor().getNome(),
                    topico.getId(),
                    topico.getAutor().getId()
            );
            listagemTopicos.add(listagemTopico);
        }
        return listagemTopicos;
    }
}

