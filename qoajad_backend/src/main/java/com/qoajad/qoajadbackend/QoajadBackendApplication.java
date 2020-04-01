package com.qoajad.qoajadbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QoajadBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(QoajadBackendApplication.class, args);
	}

}
