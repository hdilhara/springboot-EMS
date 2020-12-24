package com.hdilhara.employeeservice.common;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AppRestTemplate {

    @LoadBalanced
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
