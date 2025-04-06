package com.example.Artalia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegisterServer {
	public static void main(String[] args) {
		SpringApplication.run(ServiceRegisterServer.class, args);
	}

}
