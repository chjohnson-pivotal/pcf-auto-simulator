package io.pivotal.gas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableAutoConfiguration
@EnableEurekaClient
@EnableFeignClients

public class GasPriceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GasPriceServiceApplication.class, args);
    }
}
