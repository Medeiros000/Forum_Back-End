package com.br.alura.forum.controller;

import com.br.alura.forum.domain.curso.*;
import com.br.alura.forum.domain.usuario.DadosListagemUsuario;
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
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarCurso(@RequestBody @Valid DadosCadastroCurso dados, UriComponentsBuilder uriBuilder){
        var curso = new Curso(dados);
        cursoRepository.save(curso);
        var uri= uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoCurso(curso));
    }

    @GetMapping
    @Transactional
    public ResponseEntity <Page<DadosListagemCurso>> listarCursos(@PageableDefault(size = 10, sort ={"nome"}) Pageable paginacao){
        var cursos = cursoRepository.findAll(paginacao).map(DadosListagemCurso::new);
        return ResponseEntity.ok(cursos);
    }
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoCurso> detalharCurso(@PathVariable Long id){
        var curso = cursoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoCurso(curso));
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizarCurso(@RequestBody @Valid DadosAtualizacaoCurso dados){
        var curso = cursoRepository.getReferenceById(dados.id());
        curso.atualizarInformacoesCurso(dados);
        return ResponseEntity.ok(new DadosDetalhamentoCurso(curso));
    }

}
