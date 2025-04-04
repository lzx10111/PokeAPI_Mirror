package com.example.PokemonAPI;

import com.example.PokemonAPI.model.dto.Pokemon;
import com.example.PokemonAPI.service.PokemonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

@SpringBootApplication
public class PokemonApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(PokemonApiApplication.class, args);
//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PokemonApiApplication.class);
//		PokemonService service = context.getBean(PokemonService.class);
//		List<Pokemon> list = service.getPokemons(10);
//
//		for (Pokemon pokemon : list) {
//			System.out.println(pokemon);
//		}
	}

}
