package com.v1lladev.naves.espaciales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SeriesPeliculasProyectApplication{

	public static void main(String[] args) {
		SpringApplication.run(SeriesPeliculasProyectApplication.class, args);
	}
}
