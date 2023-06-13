package com.br.alura.forum.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record DadosCadastroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @Positive
        Long idCurso,
        @Positive
        Long idAutor
) {

}
