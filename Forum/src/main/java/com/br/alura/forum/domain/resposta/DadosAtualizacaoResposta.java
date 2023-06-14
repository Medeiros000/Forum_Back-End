package com.br.alura.forum.domain.resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DadosAtualizacaoResposta(
        @Positive
        @NotNull
        Long resposta_id,
        @NotBlank
        String mensagem,
        @Positive
        Long autor_id,
        @Positive
        Long topico_id,
        @NotNull
        Boolean solucao

) {
}
