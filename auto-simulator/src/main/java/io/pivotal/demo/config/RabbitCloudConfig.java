package io.pivotal.demo.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("cloud")
@RefreshScope
public class RabbitCloudConfig extends AbstractCloudConfig{
    
    @Bean
    @Primary
    public ConnectionFactory rabbitConnectionFactory() {
      return connectionFactory().rabbitConnectionFactory();
    }
    
}
