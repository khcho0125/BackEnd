package com.firstsocketio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class FirstSocketioApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstSocketioApplication.class, args);
	}

}
