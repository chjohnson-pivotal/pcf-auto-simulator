package io.pivotal.places;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

import feign.Logger;

@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableFeignClients
public class PlacesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlacesServiceApplication.class, args);
    }
        
/*
    @Bean
    public AlwaysSampler defaultSampler() {
      return new AlwaysSampler();
    }
*/       
       
}
