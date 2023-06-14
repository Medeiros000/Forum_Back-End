package com.br.alura.forum.domain.resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroResposta(
        @NotBlank
        String resposta_mensagem,
        @NotNull
        Long resposta_autor_id
) {
}
