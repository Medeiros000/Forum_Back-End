package com.br.alura.forum.controller;

import com.br.alura.forum.domain.resposta.Resposta;
import com.br.alura.forum.domain.resposta.RespostaRepository;
import com.br.alura.forum.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private RespostaRepository respostaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder){
        var topico = new Topico(dados);
        repository.save(topico);
        var uri= uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(DadosDetalhamentoTopico.fromTopicoETodasRespostas(topico, topico.getRespostas()));
    }
    @GetMapping
    @Transactional
    public ResponseEntity <Page<DadosListagemTopico>> listarTopicos(@PageableDefault(size = 10, sort ={"titulo"}) Pageable paginacao){
        var topicos = repository.findAll(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoTopico> detalharTopico(@PathVariable Long id){
        var topico = repository.getReferenceById(id);
        List<Resposta> respostas = respostaRepository.findByTopicoId(id);
        topico.setRespostas(respostas);
        System.out.println(topico);
        return ResponseEntity.ok(DadosDetalhamentoTopico.fromTopicoETodasRespostas(topico, respostas));
    }


}
