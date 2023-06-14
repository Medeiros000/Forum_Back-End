package com.br.alura.forum.domain.curso;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCurso(
        @NotNull
        Long curso_id,
        String curso_nome,
        String curso_categoria
) {
}
