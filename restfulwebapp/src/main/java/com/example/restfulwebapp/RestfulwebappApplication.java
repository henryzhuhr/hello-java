package com.example.restfulwebapp;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.restfulwebapp.controller.ProfileProperties;

@SpringBootApplication
@EnableConfigurationProperties(ProfileProperties.class)
public class RestfulwebappApplication implements InitializingBean {
	private final ProfileProperties profile_properties;

	public RestfulwebappApplication(ProfileProperties profile_properties) {
		this.profile_properties = profile_properties;
	}

	public static void main(String[] args) {
		SpringApplication.run(RestfulwebappApplication.class, args);
	}

	@Override
	public void afterPropertiesSet() {
		System.out.println(this.profile_properties.ToString());
	}

}
