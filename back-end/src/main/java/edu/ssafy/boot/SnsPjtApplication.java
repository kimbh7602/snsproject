package edu.ssafy.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SnsPjtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnsPjtApplication.class, args);
	}

}
