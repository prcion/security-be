package com.servustech.skeleton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SkeletonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkeletonApplication.class, args);
	}

}
