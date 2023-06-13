package com.br.alura.forum.controller;

import com.br.alura.forum.domain.curso.DadosListagemCurso;
import com.br.alura.forum.domain.resposta.DadosDetalhamentoResposta;
import com.br.alura.forum.domain.resposta.Resposta;
import com.br.alura.forum.domain.resposta.RespostaService;
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

    private final RespostaService respostaService;

    public TopicoController(RespostaService respostaService) {
        this.respostaService = respostaService;
    }


    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder){
        var topico = new Topico(dados);
        repository.save(topico);
        var uri= uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
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
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @PostMapping("/{id}/respostas")
    @Transactional
    public ResponseEntity cadastrarResposta(@PathVariable Long id, @RequestBody @Valid DadosDetalhamentoResposta dados, UriComponentsBuilder uriBuilder){
        var topico = repository.getReferenceById(id);
        var resposta = new Resposta(dados);
        topico.adicionarResposta(resposta);
        var uri= uriBuilder.path("/topicos/{id}/respostas/{id}").buildAndExpand(topico.getId(), resposta.getId()).toUri();
        return ResponseEntity.created(uri).body(resposta);
    }
}
