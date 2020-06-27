package com.example.SpringSecurity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}
	@Profile("demo")
	@Bean
	CommandLineRunner initDatabase(StoreRepository repository) {
		return args -> {
			repository.save(new ProductStore("Samsung J7 Max", "Samsung", new BigDecimal("15000")));
			repository.save(new ProductStore("iphone", "Apple", new BigDecimal("70000")));
			repository.save(new ProductStore("Mi 4i", "Redmi", new BigDecimal("20000")));
		};
	}
}
