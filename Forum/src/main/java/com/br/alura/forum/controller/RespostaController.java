package com.br.alura.forum.controller;

import com.br.alura.forum.domain.resposta.DadosAtualizacaoResposta;
import com.br.alura.forum.domain.resposta.DadosCadastroResposta;
import com.br.alura.forum.domain.resposta.Resposta;
import com.br.alura.forum.domain.resposta.RespostaRepository;
import com.br.alura.forum.domain.topico.TopicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respostas")
public class RespostaController {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<?> cadastrarResposta(@PathVariable Long id, @RequestBody @Valid DadosCadastroResposta dados){
        var topico = topicoRepository.getReferenceById(id);
        var resposta = new Resposta(dados, topico);
        respostaRepository.save(resposta);
        return ResponseEntity.noContent().build();
    }
    @PutMapping
    @Transactional
    public ResponseEntity<?> atualizarResposta(@RequestBody @Valid DadosAtualizacaoResposta dados){
        var resposta = respostaRepository.getReferenceById(dados.resposta_id());
        var topico = topicoRepository.getReferenceById(dados.topico_id());
        resposta.atualizarInformacoesResposta(dados, topico);
        return ResponseEntity.ok().build();
    }
}
