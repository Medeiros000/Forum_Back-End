package com.br.alura.forum.controller;

import com.br.alura.forum.domain.usuario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder){
        var usuario = new Usuario(dados);
        usuarioRepository.save(usuario);
        var uri= uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemUsuario>> listar(@PageableDefault(sort ={"nome"}) Pageable paginacao){
        var page = usuarioRepository.findAllByAtivoTrue(paginacao).map(DadosListagemUsuario::new);
        return ResponseEntity.ok(page);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoUsuario> detalhar(@PathVariable Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> atualizar(@RequestBody @Valid DadosAtualizacaoUsuario dados){
        var usuario = usuarioRepository.getReferenceById(dados.usuario_id());
        usuario.atualizarInformacoesUsuario(dados);
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.excluir();
        return ResponseEntity.ok().body("Usuário excluído com sucesso!!!");
    }
}
