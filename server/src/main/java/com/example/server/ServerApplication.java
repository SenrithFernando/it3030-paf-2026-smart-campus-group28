package com.example.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
=======
import org.springframework.boot.SpringApplication;
>>>>>>> 9b141c6bb6daedd5c421cd5613fe0411fdddf7a9
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableJpaAuditing
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}
