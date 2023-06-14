package com.br.alura.forum.domain.topico;

import java.time.LocalDateTime;

public record DadosListagemTopico(Long topico_id, String topico_titulo, String topico_mensagem, LocalDateTime topico_data_criacao, Enum topico_status, String autor_nome, String curso_nome) {

    public DadosListagemTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(),topico.getDataCriacao(), topico.getStatus() ,topico.getAutor().getNome(), topico.getCurso().getNome());
    }
}
