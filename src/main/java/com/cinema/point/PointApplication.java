package com.cinema.point;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableGlobalMethodSecurity(
		prePostEnabled = true,
		securedEnabled = true,
		jsr250Enabled = true)
public class PointApplication {

	public static void main(String[] args) {
		SpringApplication.run(PointApplication.class, args);
	}

}
