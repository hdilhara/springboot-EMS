package com.hdilhara.clientservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@SpringBootApplication
@EnableEurekaClient
//@EnableOAuth2Sso
public class ClientServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientServerApplication.class, args);
	}

}
