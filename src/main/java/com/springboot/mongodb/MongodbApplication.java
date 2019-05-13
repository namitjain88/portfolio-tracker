package com.springboot.mongodb;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.springboot.mongodb", "com.springboot.mvc" })
public class MongodbApplication {

	public static void main(String[] args) {

		// Setting JVM time zone to IST
		TimeZone.setDefault(TimeZone.getTimeZone("IST"));

		SpringApplication.run(MongodbApplication.class, args);
		System.out.println("SpringApplication.run has been executed");
	}
}
