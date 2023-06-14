package com.br.alura.forum.domain.resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DadosAtualizacaoResposta(
        @Positive
        @NotNull
        Long resposta_id,
        @NotBlank
        String resposta_mensagem,
        @NotNull
        Boolean resposta_solucao

) {
}
