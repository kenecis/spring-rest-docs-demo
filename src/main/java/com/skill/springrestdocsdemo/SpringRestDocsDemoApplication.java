package com.skill.springrestdocsdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.skill"})
@ComponentScan("com.skill")
public class SpringRestDocsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestDocsDemoApplication.class, args);
	}

}
