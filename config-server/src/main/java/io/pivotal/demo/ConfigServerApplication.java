package io.pivotal.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

    public static void main(String[] args) {
		new SpringApplicationBuilder(ConfigServerApplication.class).properties(
				"spring.config.name=configserver", "debug=true").run(args);
    }
}
