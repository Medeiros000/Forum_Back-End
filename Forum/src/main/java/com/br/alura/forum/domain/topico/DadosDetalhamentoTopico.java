package com.br.alura.forum.domain.topico;

import com.br.alura.forum.domain.resposta.DadosDetalhamentoResposta;
import com.br.alura.forum.domain.resposta.Resposta;

import java.time.LocalDateTime;
import java.util.List;

public record DadosDetalhamentoTopico(Long topico_id, String topico_titulo, String topico_mensagem, Enum topico_status, LocalDateTime topico_data, String autor_nome, String curso_nome, List<DadosDetalhamentoResposta> topico_respostas) {
    public DadosDetalhamentoTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getStatus(),
                topico.getDataCriacao(),
                topico.getAutor().getNome(),
                topico.getCurso().getNome(),
                DadosDetalhamentoResposta.fromRespostas(topico.getRespostas())
        );
    }

//    public static DadosDetalhamentoTopico fromTopicoETodasRespostas(Topico topico, List<Resposta> respostas) {
//        List<DadosDetalhamentoResposta> detalhamentoRespostas = DadosDetalhamentoResposta.fromRespostas(respostas);
//
//        return new DadosDetalhamentoTopico(
//                topico.getId(),
//                topico.getTitulo(),
//                topico.getMensagem(),
//                topico.getStatus(),
//                topico.getDataCriacao(),
//                topico.getAutor().getNome(),
//                topico.getCurso().getNome(),
//                detalhamentoRespostas
//        );
//    }
}
