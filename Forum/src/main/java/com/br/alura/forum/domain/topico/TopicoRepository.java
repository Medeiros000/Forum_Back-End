package com.br.alura.forum.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    List<Topico> findAllByAutorId(Long autor_id);
}
