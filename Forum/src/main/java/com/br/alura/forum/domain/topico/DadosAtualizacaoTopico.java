package com.br.alura.forum.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DadosAtualizacaoTopico(

        @NotBlank
        String topico_titulo,
        @NotBlank
        String topico_mensagem,
        @NotNull
        StatusTopico topico_status,
        @NotNull
        Long curso_id,
        @Positive
        Long autor_id
) {
}
