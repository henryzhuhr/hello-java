package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.hutool.core.util.StrUtil;
import de.codecentric.boot.admin.server.config.EnableAdminServer;

@Configuration
@EnableAdminServer // 开启 Spring Boot Admin 服务端功能
@EnableAutoConfiguration // 启用 Spring Boot 的自动配置机制
@SpringBootApplication
@RestController // 使得该类中的方法可以响应 HTTP 请求
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/hello")
	public String sayHello(@RequestParam(required = false, name = "who") String who) {
		if (StrUtil.isBlank(who)) {
			who = "World";
		}
		return StrUtil.format("Hello, {}!", who);
	}
}
