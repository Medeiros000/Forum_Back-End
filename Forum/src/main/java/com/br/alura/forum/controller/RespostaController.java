package com.br.alura.forum.controller;

import com.br.alura.forum.domain.resposta.DadosCadastroResposta;
import com.br.alura.forum.domain.resposta.Resposta;
import com.br.alura.forum.domain.resposta.RespostaRepository;
import com.br.alura.forum.domain.topico.TopicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/respostas")
public class RespostaController {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

//    @PostMapping
//    @Transactional
//    public ResponseEntity cadastrarResposta(@RequestBody @Valid DadosCadastroResposta dados){
//        var topico = topicoRepository.getReferenceById(dados.idTopico());
//        var resposta = new Resposta(dados, topico);
//        respostaRepository.save(resposta);
//        return ResponseEntity.noContent().build();
//
//    }


}
