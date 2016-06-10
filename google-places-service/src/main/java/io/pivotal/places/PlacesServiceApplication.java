package io.pivotal.places;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@SpringBootApplication
@EnableAutoConfiguration
@EnableEurekaClient
@EnableFeignClients
@Configuration
public class PlacesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlacesServiceApplication.class, args);
    }
    
    @Bean
    Logger.Level feignLoggerLevel() {
System.out.println("SETTING LOGGER LEVEL TO FULL!!!!!!");    	
        return Logger.Level.FULL;
    }    
}
