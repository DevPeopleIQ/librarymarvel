package com.marvel.developer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class ConectorConfiguration {
    
	@Bean
	public WebClient getWebClientBuilder(){
		return WebClient.create();
	}
}