package com.example.sgcmusuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "Usuarios API", version = "2.0", description = "Usuarios Microservicio" ))
public class SgcmUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgcmUsuariosApplication.class, args);
	}

}
