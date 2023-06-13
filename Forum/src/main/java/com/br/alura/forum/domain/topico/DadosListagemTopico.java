package com.br.alura.forum.domain.topico;

public record DadosListagemTopico(String titulo, String mensagem, String nomeAutor, String nomeCurso, Long id) {

    public DadosListagemTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensagem(), topico.getAutor().getNome(), topico.getCurso().getNome(), topico.getId());
    }
}
