package com.borntocode.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.borntocode")
@SpringBootApplication
public class ApplicationMain {

	public static void main(String[] args) {
		System.out.println("Main Start");
		SpringApplication.run(ApplicationMain.class, args);
		System.out.println("Main End");
	}

}
