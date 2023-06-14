package com.br.alura.forum.controller;

import com.br.alura.forum.domain.resposta.DadosCadastroResposta;
import com.br.alura.forum.domain.resposta.Resposta;
import com.br.alura.forum.domain.resposta.RespostaRepository;
import com.br.alura.forum.domain.topico.*;
import com.br.alura.forum.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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

    private final TopicoRepository topicoRepository;
    private final RespostaRepository respostaRepository;
    private final UsuarioRepository usuarioRepository;
    public TopicoController(TopicoRepository topicoRepository, RespostaRepository respostaRepository, UsuarioRepository usuarioRepository) {
        this.topicoRepository = topicoRepository;
        this.respostaRepository = respostaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarTopico(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder){
        var topico = new Topico(dados);
        topicoRepository.save(topico);
        var uri= uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }
    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<?> cadastrarResposta(@PathVariable Long id, @RequestBody @Valid DadosCadastroResposta dados){
        var topico = topicoRepository.getReferenceById(id);
        if (topico.getStatus().equals(StatusTopico.FECHADO)){
            return ResponseEntity.badRequest().body("Não é possível responder um tópico Fechado!!!");
        }
        topico.setStatus(StatusTopico.NAO_SOLUCIONADO);
        topicoRepository.save(topico);
        var resposta = new Resposta(dados, topico);
        respostaRepository.save(resposta);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
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
    @GetMapping("/usuario/{id}")
    @Transactional
    public ResponseEntity<?> listarTopicosPorUsuario(@PathVariable Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        var topico = topicoRepository.findAllByAutorId(usuario.getId());
        return ResponseEntity.ok().body(DadosListagemTopicoPorUsuario.fromTopicos(topico)) ;
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> editarTopico(@PathVariable Long id,@RequestBody @Valid DadosAtualizacaoTopico dados){
        var topico = topicoRepository.getReferenceById(id);
        topico.atualizarInformacoesTopico(dados);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> removerTopico(@PathVariable Long id){
        topicoRepository.deleteById(id);
        return ResponseEntity.ok().body("Topico " + id + " removido com sucesso!!!");
    }
}
