package com.br.alura.forum.domain.curso;

public record DadosListagemCurso(String curso_nome, String curso_categoria, Long curso_id) {
    public DadosListagemCurso(Curso curso) {
        this(curso.getNome(), curso.getCategoria(), curso.getId());
    }
}
