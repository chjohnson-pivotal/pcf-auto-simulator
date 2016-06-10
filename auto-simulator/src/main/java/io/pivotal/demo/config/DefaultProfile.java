package io.pivotal.demo.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("default")
public class DefaultProfile 
{
		
	@Bean
	public ConnectionFactory connectionFactory()
	{
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("tiger.cloudamqp.com");
        connectionFactory.setVirtualHost("dzmmizcc");
        connectionFactory.setUsername("dzmmizcc");
        connectionFactory.setPassword("v-tY2b5xjrjqgkC3TJ9rbbHgyKOlZDSM");
        
        return connectionFactory;
	}
	
    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }
}
