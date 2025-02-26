package com.example.hello_java;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloJavaApplication {

	public static void main(String[] args) {
		// SpringApplication.run(HelloJavaApplication.class, args);
		SpringApplication app=new SpringApplication(HelloJavaApplication.class);
		app.setBannerMode(Banner.Mode.OFF); // 关闭banner
		app.run(args);
	}

}
