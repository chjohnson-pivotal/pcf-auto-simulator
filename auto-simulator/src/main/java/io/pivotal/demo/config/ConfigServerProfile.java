package io.pivotal.demo.config;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("configServer")
@RefreshScope
public class ConfigServerProfile 
{
	static final Logger log = Logger.getLogger(ConfigServerProfile.class); 
	
	@Value("${rabbitmq.uri}")
    String rabbitUri;
	
	@Value("${rabbitmq.vhost}")
    String rabbitVhost;	

	@Value("${rabbitmq.username}")
    String rabbitUsername;
	
	@Value("${rabbitmq.password}")
    String rabbitPassword;
	
	@Bean
	public ConnectionFactory connectionFactory()
	{
		log.info("Using config server profile");
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitUri);
        connectionFactory.setVirtualHost(rabbitVhost);
        connectionFactory.setUsername(rabbitUsername);
        connectionFactory.setPassword(rabbitPassword);
        
        return connectionFactory;
	}
	
    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }
}
