package io.pivotal.demo.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("foo")
public class DefaultProfile 
{
		
	@Bean
	public ConnectionFactory connectionFactory()
	{
		//https://gdgsrgtb:owAHjylyus7r5c_3VXwhz969cmf-0P4K@wildboar.rmq.cloudamqp.com/api/		
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("wildboar.rmq.cloudamqp.com");
//        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("gdgsrgtb");
        connectionFactory.setUsername("gdgsrgtb");
        connectionFactory.setPassword("owAHjylyus7r5c_3VXwhz969cmf-0P4K");
        
        return connectionFactory;
	}
	
    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }
}
