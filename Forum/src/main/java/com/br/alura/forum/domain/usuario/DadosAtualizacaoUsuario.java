package com.br.alura.forum.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUsuario(
        @NotNull
        Long usuario_id,
        @NotBlank
        String usuario_nome,
        @NotBlank
        String usuario_email,
        @NotBlank
        String usuario_senha
) {
}
