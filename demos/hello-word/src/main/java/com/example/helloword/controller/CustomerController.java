package com.example.helloword.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Controller 里面的方法都以 json 格式输出，不用再写什么 jackjson 配置的了！
public class CustomerController {
    @RequestMapping("/hello")
    public String index() {
        return "Hello Spring Boot";
    }
}
