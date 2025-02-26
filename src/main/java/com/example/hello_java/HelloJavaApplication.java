package com.example.hello_java;

import org.slf4j.Logger;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.hello_java.service.impl.UserServiceImpl;

@SpringBootApplication
public class HelloJavaApplication {

	static Logger logger = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);

	public static void main(String[] args) {
		// SpringApplication.run(HelloJavaApplication.class, args);
		SpringApplication app=new SpringApplication(HelloJavaApplication.class);
		app.setBannerMode(Banner.Mode.OFF); // 关闭banner
		app.run(args);
	}

}
