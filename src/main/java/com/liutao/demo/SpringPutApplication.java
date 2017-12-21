package com.liutao.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringPutApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringPutApplication.class, args);
	}
}
