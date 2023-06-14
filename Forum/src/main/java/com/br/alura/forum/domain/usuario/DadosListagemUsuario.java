package com.br.alura.forum.domain.usuario;

public record DadosListagemUsuario(Long usuario_id, String usuario_nome, String usuario_email) {

        public DadosListagemUsuario(Usuario usuario) {
            this(
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getEmail()
            );
        }
}
