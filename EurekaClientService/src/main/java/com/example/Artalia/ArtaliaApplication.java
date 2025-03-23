package com.example.Artalia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ArtaliaApplication {
	public static void main(String[] args) {
		SpringApplication.run(ArtaliaApplication.class, args);
	}

}
