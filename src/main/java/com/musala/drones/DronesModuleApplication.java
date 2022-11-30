package com.musala.drones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DronesModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(DronesModuleApplication.class, args);
	}

}
