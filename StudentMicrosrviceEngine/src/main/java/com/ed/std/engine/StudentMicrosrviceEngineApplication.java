package com.ed.std.engine;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;



@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "AuditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
			title = "Student microservice REST API Documentation",
			description = "Student microservice REST API Documentation",
			version = "v1",
				contact = @Contact
				(
						name = "Md Asif Shamim",
						email = "asifshmim0403@gmail.com",
						url = "https://linkedin.com/in/md-asif-shamim/"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://tomcat.apache.org/"
				)
		)
)
public class StudentMicrosrviceEngineApplication {

	public static void main(String[] args) {
		
		System.err.println("boot project starting.....");
		SpringApplication.run(StudentMicrosrviceEngineApplication.class, args);
		System.err.println("boot project started successfully!!!");
	}
	

}
