package com.example.showTimeService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
/*import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.openfeign.EnableFeignClients;*/

@SpringBootApplication
@EnableFeignClients

public class ShowTimeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShowTimeServiceApplication.class, args);
	}

}
