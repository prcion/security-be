package com.findork.chiriezerie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ChiriezerieApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChiriezerieApplication.class, args);
	}

}
