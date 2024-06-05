package com.dev.inktown;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class DevInktownApplication {

	public static void main(String[] args) {
		log.severe("starting 123");
		SpringApplication.run(DevInktownApplication.class, args);
	}
}
