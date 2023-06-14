package com.br.alura.forum.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record DadosCadastroTopico(
        @NotBlank
        String topico_titulo,
        @NotBlank
        String topico_mensagem,
        @Positive
        Long curso_id,
        @Positive
        Long autor_id
) {

}
