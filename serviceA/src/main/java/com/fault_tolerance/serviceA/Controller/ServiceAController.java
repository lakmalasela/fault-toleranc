package com.fault_tolerance.serviceA.Controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/a")
public class ServiceAController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8081/";

    private static final String SERVICE_A = "serviceA";

    @GetMapping
    @CircuitBreaker(name = SERVICE_A,fallbackMethod = "serviceFallback")
    public String serviceA(){
         String baseUrl = BASE_URL;
        return restTemplate.getForObject(baseUrl +"b",String.class);
    }

    public String serviceFallback(Exception e){
        return "This is fallback method is Service A";
    }
}
