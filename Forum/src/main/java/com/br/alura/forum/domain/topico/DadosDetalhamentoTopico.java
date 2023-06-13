package com.br.alura.forum.domain.topico;

public record DadosDetalhamentoTopico(Long id, String titulo, String mensagem, String nomeAutor, String nomeCurso) {
    public DadosDetalhamentoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getAutor().getNome(), topico.getCurso().getNome());
    }
}
