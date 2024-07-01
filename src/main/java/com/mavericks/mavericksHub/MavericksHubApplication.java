package com.mavericks.mavericksHub;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@SpringBootApplication
public class MavericksHubApplication {
	public static void main(String[] args) {
		SpringApplication.run(MavericksHubApplication.class, args);

		Object[] objects = retrieveObjects().toArray();
		System.out.println((int) objects[0] + (int) objects[3]);
	}
	public static Set<Object> retrieveObjects(){
		return Set.of("Hello", 5, true, "Goat");
	}
}
