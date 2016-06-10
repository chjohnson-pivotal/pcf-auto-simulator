package io.pivotal.dealer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableAutoConfiguration
@EnableEurekaClient
@EnableFeignClients

public class DealerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DealerServiceApplication.class, args);
    }
}
