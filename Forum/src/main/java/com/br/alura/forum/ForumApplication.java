package com.br.alura.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}
	// Configurando o Access-Control-Allow-Origin
	 @Bean
	 public WebMvcConfigurer corsConfigurer() {
	     return new WebMvcConfigurer() {
	         @Override
	         public void addCorsMappings(CorsRegistry registry) {
	             registry.addMapping("/**").allowedOrigins("https://forum-jr-production.up.railway.app/");
	         }
	     };
	 }

}
