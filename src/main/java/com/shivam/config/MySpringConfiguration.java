package com.shivam.config;

import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shivam.Entity.Url;

@Configuration
public class MySpringConfiguration {

	@Bean
	public HashMap<String,Url> hm(){
		return new HashMap<String, Url>();
	}
	
}
