package io.pivotal.demo.config;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.launchdarkly.client.LDClient;

@Configuration
@RefreshScope
public class FeatureFlagConfig {

    @Bean
    public LDClient ldClient() {
    	return new LDClient("sdk-1edf53b6-e8e8-46ff-9641-aa5a8feaa227");
    }
}
