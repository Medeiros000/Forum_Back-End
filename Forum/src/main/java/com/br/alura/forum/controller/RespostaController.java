//package com.br.alura.forum.controller;
//
//import com.br.alura.forum.domain.resposta.Resposta;
//import com.br.alura.forum.domain.resposta.RespostaService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//
//public class RespostaController {
//    private final RespostaService respostaService;
//
//    @Autowired
//    public RespostaController(RespostaService respostaService) {
//        this.respostaService = respostaService;
//    }
//
////    @GetMapping("/respostas")
////    public List<Resposta> obterTodasRespostas() {
////        return respostaService.obterTodasRespostas();
////    }
//}