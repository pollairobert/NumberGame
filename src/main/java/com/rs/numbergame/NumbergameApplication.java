package com.rs.numbergame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point to spring app
 */
@SpringBootApplication
public class NumbergameApplication {

	public static void main(String[] args) {
		SpringApplication.run(NumbergameApplication.class, args);
		System.out.println("Welcome Gamer");
	}
}
