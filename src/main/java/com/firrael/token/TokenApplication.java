package com.firrael.token;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EntityScan("com.firrael.base")
public class TokenApplication {
	public static void main(String[] args) {
		SpringApplication.run(TokenApplication.class, args);
	}
}
