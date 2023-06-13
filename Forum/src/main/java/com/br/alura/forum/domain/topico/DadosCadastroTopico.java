package com.br.alura.forum.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotBlank
        Long idCurso,
        @NotBlank
        Long idAutor
) {
}
