package com.deloitte.panservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PanCardProvidersServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PanCardProvidersServiceApplication.class, args);
	}

}
