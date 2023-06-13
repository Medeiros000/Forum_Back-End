package com.br.alura.forum.domain.resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespostaService {
    private final RespostaRepository respostaRepository;

    @Autowired
    public RespostaService(RespostaRepository respostaRepository) {
        this.respostaRepository = respostaRepository;
    }

    public List<Resposta> obterTodasRespostas(Long topico_id) {
        return respostaRepository.findByTopicoId(topico_id);
    }
}
