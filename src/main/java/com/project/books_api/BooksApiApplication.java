package com.project.books_api;

import com.project.books_api.infrastructure.gutendex.GutendexClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ConfigurationPropertiesScan
public class BooksApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(BooksApiApplication.class, args);
	}
}
