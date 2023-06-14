package com.br.alura.forum.domain.usuario;

public record DadosDetalhamentoUsuario(Long usuario_id, String usuario_nome, String usuario_email) {
    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }
}
