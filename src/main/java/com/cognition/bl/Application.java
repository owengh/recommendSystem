package com.cognition.bl;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cognition.bl")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
