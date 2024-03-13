package com.br.alura.forum.service;

import com.br.alura.forum.model.Curso;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class CursoServiceTest {

    @Autowired
    private CursoService cursoService;

    @Test
    public void testSalvarCurso() {
        cursoService.salvarCurso(new Curso(0L,"Matemática", "Matemática"));
    }

    @Test
    public void testListarCursos() {
        cursoService.listarCursos(Pageable.unpaged());
    }

    @Test
    public void testBuscarCursoPorId() {
        cursoService.buscarCursoPorId(1L);
    }

    @Test
    public void testBuscarCursoPorNome() {
        cursoService.buscarCursoPorNome("Matemática");
    }

    @Test
    public void testExcluirCurso() {
        Curso curso = (Curso) cursoService.buscarCursoPorNome("Matemática").get();
        cursoService.excluirCurso(curso.getId());
    }

}
