package com.br.alura.forum.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DadosAtualizacaoTopico(
        @NotNull
        Long id,
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotNull
        Long idCurso,
        @Positive
        Long idAutor
) {
}
