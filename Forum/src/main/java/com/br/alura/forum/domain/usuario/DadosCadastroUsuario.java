package com.br.alura.forum.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
    @NotBlank
    String usuario_nome,
    @NotBlank
    @Email
    String usuario_email,
    @NotBlank
    String usuario_senha){
}
