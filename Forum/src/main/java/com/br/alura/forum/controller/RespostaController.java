package com.br.alura.forum.controller;

import com.br.alura.forum.domain.resposta.*;
import com.br.alura.forum.domain.topico.DadosDetalhamentoTopico;
import com.br.alura.forum.domain.topico.StatusTopico;
import com.br.alura.forum.domain.topico.TopicoRepository;
import com.br.alura.forum.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respostas")
public class RespostaController {

    private final RespostaRepository respostaRepository;
    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    public RespostaController(TopicoRepository topicoRepository, RespostaRepository respostaRepository, UsuarioRepository usuarioRepository) {
        this.topicoRepository = topicoRepository;
        this.respostaRepository = respostaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/usuario/{id}")
    @Transactional
    public ResponseEntity<?> listarRespostasPorUsuario(@PathVariable Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        var resposta = respostaRepository.findAllByAutorId(usuario.getId());
        return ResponseEntity.ok().body(DadosListagemRespostaPorUsuario.fromRespostas(resposta)) ;
    }

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<?> cadastrarResposta(@PathVariable Long id, @RequestBody @Valid DadosCadastroResposta dados){
        var topico = topicoRepository.getReferenceById(id);
        if (topico.getStatus().equals(StatusTopico.FECHADO)){
            return ResponseEntity.badRequest().body("Não é possível responder um tópico Fechado!!!");
        }
        var resposta = new Resposta(dados, topico);
        respostaRepository.save(resposta);
        return ResponseEntity.noContent().build();
    }
    @PutMapping
    @Transactional
    public ResponseEntity<?> atualizarResposta(@RequestBody @Valid DadosAtualizacaoResposta dados){
        var resposta = respostaRepository.getReferenceById(dados.resposta_id());
        var topico = topicoRepository.getReferenceById(resposta.getTopico().getId());
        if (topico.getStatus().equals(StatusTopico.FECHADO)){
            return ResponseEntity.badRequest().body("Não é possível atualizar uma resposta de um tópico Fechado!!!");
        }
        if (dados.resposta_solucao().equals(true)){
            topico.setStatus(StatusTopico.SOLUCIONADO);
            topicoRepository.save(topico);
        } else {
            topico.setStatus(StatusTopico.NAO_SOLUCIONADO);
            topicoRepository.save(topico);
        }
        resposta.atualizarInformacoesResposta(dados, topico);
        return ResponseEntity.ok().body(new DadosDetalhamentoTopico(topico));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluirResposta(@PathVariable Long id){
        var resposta = respostaRepository.getReferenceById(id);
        respostaRepository.delete(resposta);
        return ResponseEntity.ok().body("Resposta "+ id + " excluída com sucesso!!!");
    }
}
