package com.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


/**
 * @author Manohar Perumal
 * 
 *This is restTemplate
 *
 */
@Configuration
public class RestTemplateConfig {
	
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
