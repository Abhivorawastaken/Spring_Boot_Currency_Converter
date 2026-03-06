package com.example.CurrencyConverter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@EnableCaching
@SpringBootApplication
public class CurrencyConverterApplication /*implements CommandLineRunner*/ {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConverterApplication.class, args);
	}

	@Bean
	public CommandLineRunner checkProfile(Environment env) {
		return args -> {
			System.out.println("Active Profiles:");
			for (String profile : env.getActiveProfiles()) {
				System.out.println(profile);
			}
		};
	}
}
