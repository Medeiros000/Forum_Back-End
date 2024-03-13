package com.br.alura.forum.controller;

import com.br.alura.forum.model.Curso;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebMvcTest(CursoController.class)
public class CursoControllerTest {

    @Autowired
    private CursoController cursoController;

    @Test
    public void buscarCursoTest(){
//        Curso curso = cursoController.buscarCurso(1L);
        System.out.println(cursoController.buscarCurso(1L));

    }
}