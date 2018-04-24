package com.cognition.bl;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.cognition.bl.dao.UserMapper;

@SpringBootApplication
@MapperScan(basePackages = {"com.cognition.bl.dao.*"},markerInterface=UserMapper.class)
@ComponentScan(value="com.cognition.bl.dao.*")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
