package com.firrael.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.firrael.base")
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(com.firrael.test.TestApplication.class, args);
    }
}
