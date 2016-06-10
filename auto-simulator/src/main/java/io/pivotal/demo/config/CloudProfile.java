package io.pivotal.demo.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("cloud")
public class CloudProfile 
{
	@Bean
	public Cloud cloud()
	{
		return new CloudFactory().getCloud();
	}
	
	@Bean
	public ConnectionFactory connectionFactory()
	{
		return cloud().getSingletonServiceConnector(ConnectionFactory.class, null);
	}
	
    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }
}
