package com.firrael.find;

import com.firrael.base.SimpleUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class FindApplication {
	public static void main(String[] args) {
		SpringApplication.run(FindApplication.class, args);
	}

	@Bean
	SimpleUserRepository getSimpleUserRepository() {
		return new SimpleUserRepository();
	}
}
