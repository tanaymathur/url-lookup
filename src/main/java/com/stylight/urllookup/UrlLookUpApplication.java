package com.stylight.urllookup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class UrlLookUpApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlLookUpApplication.class, args);
	}

}
