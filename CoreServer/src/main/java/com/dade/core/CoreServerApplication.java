package com.dade.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CoreServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreServerApplication.class, args);
	}
}
