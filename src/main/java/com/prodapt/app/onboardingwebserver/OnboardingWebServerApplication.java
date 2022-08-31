
package com.prodapt.app.onboardingwebserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OnboardingWebServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnboardingWebServerApplication.class, args);
	}
	
}
