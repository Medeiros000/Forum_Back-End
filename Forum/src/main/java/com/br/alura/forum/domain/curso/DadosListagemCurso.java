package com.br.alura.forum.domain.curso;

public record DadosListagemCurso(String nome, String categoria) {
    public DadosListagemCurso(Curso curso) {
        this(curso.getNome(), curso.getCategoria());
    }
}
