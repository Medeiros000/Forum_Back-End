package com.br.alura.forum.controller;

import com.br.alura.forum.domain.resposta.DadosAtualizacaoResposta;
import com.br.alura.forum.domain.resposta.DadosCadastroResposta;
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
    private TopicoRepository topicoRepository;

    @Autowired
    private RespostaRepository respostaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarTopico(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder){
        var topico = new Topico(dados);
        topicoRepository.save(topico);
        var uri= uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(DadosDetalhamentoTopico.fromTopicoETodasRespostas(topico, topico.getRespostas()));
    }


    @GetMapping
    @Transactional
    public ResponseEntity <Page<DadosListagemTopico>> listarTopicos(@PageableDefault(sort ={"dataCriacao"}) Pageable paginacao){
        var topicos = topicoRepository.findAll(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoTopico> detalharTopico(@PathVariable Long id){
        var topico = topicoRepository.getReferenceById(id);
        List<Resposta> respostas = respostaRepository.findByTopicoId(id);
        topico.setRespostas(respostas);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> atualizarTopico(@RequestBody @Valid DadosAtualizacaoTopico dados){
        var topico = topicoRepository.getReferenceById(dados.id());
        topico.atualizarInformacoesTopico(dados);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

}
