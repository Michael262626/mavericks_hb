package com.mavericks.mavericksHub;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MavericksHubApplication {
	public static void main(String[] args){
		SpringApplication.run(MavericksHubApplication.class, args);
	}
	static {
		System.out.println("Hello");
	}
}
