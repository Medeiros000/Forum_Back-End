package com.br.alura.forum.domain.curso;

public record DadosDetalhamentoCurso(Long curso_id, String curso_nome, String curso_categoria) {
    public DadosDetalhamentoCurso(Curso curso) {
        this(curso.getId(), curso.getNome(), curso.getCategoria());
    }
}
